package dev.ayelen.auth.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ayelen.auth.register.RegisterExceptions.RegisterException;
import dev.ayelen.auth.register.RegisterExceptions.UserAlreadyExistsException;

@RestController
@RequestMapping("${api-endpoint}")
public class RegisterController {
    
    private final RegisterService registerService;
    
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            return new ResponseEntity<>(registerService.registerUser(request), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Username already exists: " + e.getMessage() + "\"}");          
        } catch (RegisterException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Registration error: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Unexpected error: " + e.getMessage() + "\"}");
        }
    }
}
