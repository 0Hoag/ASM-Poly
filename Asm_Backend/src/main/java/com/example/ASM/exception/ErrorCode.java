package com.example.ASM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZE_EXCEPTION(9999, "UNCATEGORIZE_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    MISSING_INPUT(8888, "Missing input", HttpStatus.BAD_REQUEST),
    CIRCULAR_REFERENCE_NOT_ALLOWED(7777, "Circular reference not allow", HttpStatus.BAD_REQUEST),
    ENTITY_HASH_MANY_CHILDREN(6666, "Entity hash many children", HttpStatus.BAD_REQUEST),

    INVALID_KEY(1001, "INVALID MESSAGE KEY", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1002, "USER EXITED", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(1002, "EMAIL EXITED", HttpStatus.BAD_REQUEST),
    SELECTED_PRODUCT_NOT_EXISTED(1003, "SELECTED_PRODUCT_NOT_EXISTED", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1004, "USERNAME MUST AT LEES THAN {min} CHARACTER", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "PASSWORD NOT BE AT LEES THAN {min} CHARACTER", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "USER_NOT_EXISTED", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZATION(1008, "YOU DO NOT HAVE PERMISSION", HttpStatus.FORBIDDEN),
    INVALID_DOB(1009, "YOU AGE MUST BE AT least {min}", HttpStatus.BAD_REQUEST),
    PASSWORD_EXISTED(1010, "PASSWORD_EXISTED", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(1011, "PASSWORD INCORRECT", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(1011, "PERMISSION_NOT_FOUND", HttpStatus.BAD_REQUEST),
    CART_ITEM_EXISTED(1012, "CART_ITEM_EXISTED", HttpStatus.BAD_REQUEST),
    ORDERS_NOT_EXISTED(1013, "ORDERS_NOT_EXISTED", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_EXISTED(1014, "Image not existed", HttpStatus.BAD_REQUEST),

    UPLOAD_FILE_FAIL(1015, "Upload file to fail!", HttpStatus.BAD_REQUEST),
    REMOVE_FILE_FAIL(1016, "Remove file to fail!", HttpStatus.BAD_REQUEST),

    CATEGORIES_NOT_EXISTED(1017, "Categories not existed", HttpStatus.BAD_REQUEST),
    CATEGORIES_EXISTED(1018, "Categories existed", HttpStatus.BAD_REQUEST),
    CATEGORIES_NAME_EXISTED(1019, "Categories name existed", HttpStatus.CONFLICT),

    PRODUCT_NOT_EXISTED(1020, "Product not existed", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1021, "Product existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_EXISTED(1022, "Product name existed", HttpStatus.CONFLICT),

    PRODUCT_TYPE_NOT_EXISTED(1023, "Product type not existed", HttpStatus.BAD_REQUEST),
    PRODUCT_TYPE_EXISTED(1024, "Product type existed", HttpStatus.BAD_REQUEST),
    PRODUCT_TYPE_NAME_EXISTED(1025, "Product type name existed", HttpStatus.CONFLICT),

    SPECIFICATION_TYPE_NOT_EXISTED(1026, "Specification type not existed", HttpStatus.BAD_REQUEST),
    SPECIFICATION_TYPE_EXISTED(1027, "Specification type existed", HttpStatus.BAD_REQUEST),
    SPECIFICATION_TYPE_NAME_EXISTED(1028, "Specification type name existed", HttpStatus.CONFLICT),

    CART_NOT_EXISTED(1029, "Cart not existed", HttpStatus.BAD_REQUEST),
    CART_EXISTED(1030, "Cart existed", HttpStatus.BAD_REQUEST),
    CART_NAME_EXISTED(1031, "Cart name existed", HttpStatus.CONFLICT),

    CART_DETAIL_NOT_EXISTED(1032, "Cart detail not existed", HttpStatus.BAD_REQUEST),
    CART_DETAIL_EXISTED(1033, "Cart detail existed", HttpStatus.BAD_REQUEST),
    CART_DETAIL_NAME_EXISTED(1034, "Cart detail name existed", HttpStatus.CONFLICT),

    ORDERS_STATUS_NOT_EXISTED(1035, "Order status not existed", HttpStatus.BAD_REQUEST),
    ORDERS_STATUS_EXISTED(1036, "Order status existed", HttpStatus.BAD_REQUEST),
    ORDERS_STATUS_NAME_EXISTED(1037, "Order status name existed", HttpStatus.CONFLICT),

    ADDRESS_NOT_EXISTED(1038, "Address not existed", HttpStatus.BAD_REQUEST),
    ADDRESS_EXISTED(1039, "Address existed", HttpStatus.BAD_REQUEST),
    ADDRESS_NAME_EXISTED(1040, "Address name existed", HttpStatus.CONFLICT),

    SPECIFICATION_NOT_FOUND(1041, "Specification not found", HttpStatus.NOT_FOUND),
    SPECIFICATION_EXISTED(1042, "Specification existed", HttpStatus.BAD_REQUEST),
    SPECIFICATION_NAME_EXISTED(1043, "Specification name existed", HttpStatus.CONFLICT),

    ORDER_DETAIL_NOT_FOUND(1044, "Order detail not found", HttpStatus.BAD_REQUEST),
    INVALID_QUANTITY(1045, "Invalid quantity", HttpStatus.BAD_REQUEST),

    SEND_EMAIL_ERROR(1046, "Send email error", HttpStatus.BAD_REQUEST),

    EMAIL_OR_PHONE_REQUIRED(1040, "EMAIL OR PHONE NUMBER CANNOT BE EMPTY", HttpStatus.BAD_REQUEST),
    ACCOUNT_OR_PASSWORD_EMPTY(1050, "ACCOUNT OR PASSWORD CANNOT BE EMPTY", HttpStatus.BAD_REQUEST),
    OUT_OF_STOCK(1051, "The product is out of stock", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    ErrorCode() {}
}
