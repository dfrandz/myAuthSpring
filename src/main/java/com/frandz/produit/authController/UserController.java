package com.frandz.produit.authController;

import com.frandz.produit.exception.EmailExistException;
import com.frandz.produit.exception.ExpiredTokenException;
import com.frandz.produit.exception.InvalidTokenException;
import com.frandz.produit.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws EmailExistException {
        return ResponseEntity.ok(this.authService.register(request));
    }
    @GetMapping("/verifyEmail/{token}")
    public ResponseEntity<AuthenticationResponse> verifyEmail(@PathVariable("token") String token) throws InvalidTokenException, ExpiredTokenException {
        return ResponseEntity.ok(this.authService.validateToken(token));
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(this.authService.auth(authRequest));
    }
}
