package dev.ayelen.auth.login;

public class LoginResponse {
    private String message;
    private String token;
    private String roleName;

    public LoginResponse(String message, String token, String roleName) {
        this.message = message;
        this.token = token;
        this.roleName = roleName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
