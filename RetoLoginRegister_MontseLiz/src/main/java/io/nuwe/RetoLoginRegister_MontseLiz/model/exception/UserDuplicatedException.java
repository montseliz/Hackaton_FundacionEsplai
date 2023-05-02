package io.nuwe.RetoLoginRegister_MontseLiz.model.exception;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException(String message) {
        super(message);
    }

}