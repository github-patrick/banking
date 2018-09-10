package com.granite.banking.exceptions;

public class ValidationUtils {

    public static void handleModelConstraints(String errorMessage) {
        throw new ModelConstraintErrorException(errorMessage);
    }

    public static void handleUserDetailsNotFoundException(String errorMessage) {
        throw new UserDetailsNotFoundException((errorMessage));
    }

    public static void handleEmailExistsException(String errorMessage) {
        throw new EmailExistsException(errorMessage);
    }
}
