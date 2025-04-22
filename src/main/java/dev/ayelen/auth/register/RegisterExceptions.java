package dev.ayelen.auth.register;


public class RegisterExceptions {
    
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class RegisterException extends RuntimeException {
        public RegisterException(String message) {
            super(message);
        }
        
        public RegisterException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
