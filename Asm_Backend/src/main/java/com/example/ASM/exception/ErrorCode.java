package com.example.ASM.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),

    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email already exists", HttpStatus.BAD_REQUEST),

    SELECTED_PRODUCT_NOT_EXISTED(1004, "Selected product does not exist", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1005, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1006, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(1007, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1008, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "You do not have permission", HttpStatus.FORBIDDEN),

    INVALID_DOB(1010, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    PASSWORD_EXISTED(1011, "Password already exists", HttpStatus.BAD_REQUEST),

    PERMISSION_NOT_FOUND(1012, "Permission not found", HttpStatus.BAD_REQUEST),
    CART_ITEM_EXISTED(1013, "Cart item already exists", HttpStatus.BAD_REQUEST),
    ORDER_NOT_EXISTED(1014, "Order does not exist", HttpStatus.BAD_REQUEST),

    IMAGE_NOT_EXISTED(1015, "Image does not exist", HttpStatus.BAD_REQUEST),
    UPLOAD_FILE_FAIL(1016, "Failed to upload file", HttpStatus.BAD_REQUEST),
    REMOVE_FILE_FAIL(1017, "Failed to remove file", HttpStatus.BAD_REQUEST),

    MISSING_INPUT(1018, "Missing input", HttpStatus.BAD_REQUEST),

    CATEGORIES_NOT_EXISTED(1019, "Categories do not exist", HttpStatus.BAD_REQUEST),
    CATEGORIES_EXISTED(1020, "Categories already exist", HttpStatus.BAD_REQUEST),
    CATEGORIES_NAME_EXISTED(1021, "Category name already exists", HttpStatus.CONFLICT),

    PRODUCT_NOT_EXISTED(1022, "Product does not exist", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1023, "Product already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_EXISTED(1024, "Product name already exists", HttpStatus.CONFLICT),

    PRODUCT_TYPE_NOT_EXISTED(1025, "Product type does not exist", HttpStatus.BAD_REQUEST),
    PRODUCT_TYPE_EXISTED(1026, "Product type already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_TYPE_NAME_EXISTED(1027, "Product type name already exists", HttpStatus.CONFLICT),

    SPECIFICATION_TYPE_NOT_EXISTED(1028, "Specification type does not exist", HttpStatus.BAD_REQUEST),
    SPECIFICATION_TYPE_EXISTED(1029, "Specification type already exists", HttpStatus.BAD_REQUEST),
    SPECIFICATION_TYPE_NAME_EXISTED(1030, "Specification type name already exists", HttpStatus.CONFLICT),

    CART_NOT_EXISTED(1031, "Cart does not exist", HttpStatus.BAD_REQUEST),
    CART_EXISTED(1032, "Cart already exists", HttpStatus.BAD_REQUEST),
    CART_NAME_EXISTED(1033, "Cart name already exists", HttpStatus.CONFLICT),

    CART_DETAIL_NOT_EXISTED(1034, "Cart detail does not exist", HttpStatus.BAD_REQUEST),
    CART_DETAIL_EXISTED(1035, "Cart detail already exists", HttpStatus.BAD_REQUEST),
    CART_DETAIL_NAME_EXISTED(1036, "Cart detail name already exists", HttpStatus.CONFLICT),

    ORDER_DETAIL_NOT_EXISTED(1037, "Order detail does not exist", HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_EXISTED(1038, "Order detail already exists", HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_NAME_EXISTED(1039, "Order detail name already exists", HttpStatus.CONFLICT),

    // **Các mã lỗi cho ORDER_DETAIL và PRODUCT**
    ORDER_DETAIL_NOT_FOUND(1040, "Order detail not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1041, "Product not found", HttpStatus.BAD_REQUEST),

    // **Cập nhật các mã lỗi cho Address**
    ADDRESS_NOT_EXISTED(1042, "Address does not exist", HttpStatus.BAD_REQUEST),

    // **Mã lỗi mới cho trạng thái đơn hàng**
    ORDER_STATUS_NOT_EXISTED(1043, "Order status does not exist", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
