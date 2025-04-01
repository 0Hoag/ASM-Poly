package com.example.ASM.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ASM.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalException {
    private static final String MIN_ATTRIBUTES = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handRuntimeException(Exception exception) {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZE_EXCEPTION;
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlRuntimeException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> hanlMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Tạo một map để lưu trữ các lỗi validation
        Map<String, String> errors = new HashMap<>();

        // Thu thập tất cả các lỗi validation
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Tạo response với mã lỗi validation
        ErrorCode errorCode = ErrorCode.INVALID_KEY; // Hoặc một mã lỗi validation phù hợp
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage("Lỗi validation");
        apiResponse.setResult(errors); // Thêm chi tiết lỗi vào kết quả

        return ResponseEntity.badRequest().body(apiResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValues = String.valueOf(attributes.get(MIN_ATTRIBUTES));
        return message.replace("{" + MIN_ATTRIBUTES + "}", minValues);
    }
}
