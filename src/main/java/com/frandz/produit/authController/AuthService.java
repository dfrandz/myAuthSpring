package com.frandz.produit.authController;

import com.frandz.produit.config.JwtService;
import com.frandz.produit.exception.EmailExistException;
import com.frandz.produit.exception.ExceptionHandling;
import com.frandz.produit.exception.ExpiredTokenException;
import com.frandz.produit.exception.InvalidTokenException;
import com.frandz.produit.model.Role;
import com.frandz.produit.model.User;
import com.frandz.produit.model.VerificationToken;
import com.frandz.produit.repository.UserRepo;
import com.frandz.produit.repository.VerificationTokenRepo;
import com.frandz.produit.service.EmailService;
import com.frandz.produit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService extends ExceptionHandling {
    private final UserRepo userRepo;
    private final UserService userService;
    private final JwtService jwtService;
    private final VerificationTokenRepo verificationTokenRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthenticationResponse register(RegisterRequest request) throws EmailExistException {
        var user =this.userRepo.findByEmail(request.getEmail());
        if (user.isPresent())
            throw new EmailExistException("email is already exist");
        var newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .password(this.passwordEncoder.encode(request.getPassword()))
                .status(false)
                .build();
        this.userRepo.save(newUser);
        var jwtToken = this.jwtService.generateToken(newUser);
        String code = this.generateCode();
        VerificationToken token = new VerificationToken(code,newUser);
        this.verificationTokenRepo.save(token);
        this.sendEmailUser(newUser,code);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(newUser)
                .message("email send successful")
                .build();
    }

    public AuthenticationResponse validateToken(String code) throws InvalidTokenException, ExpiredTokenException {
        VerificationToken token = this.verificationTokenRepo.findByToken(code);
        if (token == null)
            throw new InvalidTokenException("Invalid Token");
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            this.verificationTokenRepo.delete(token);
            throw new ExpiredTokenException("expired Token");
        }
        user.setStatus(true);
        this.userRepo.save(user);
        return AuthenticationResponse.builder()
                .message("email verifier")
                .user(user)
                .build();
    }

    public AuthenticationResponse auth(AuthRequest request){
        var user = this.userRepo.findByEmail(request.getEmail()).orElseThrow();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    public String generateCode() {
        Random random = new Random();
        Integer code = 100000 + random.nextInt(900000);
        return code.toString();
    }
    public void sendEmailUser(User u, String code) {
        String emailBody ="Bonjour "+ "<h1>"+u.getName() +"</h1>" +
                " Votre code de validation est "+"<h1>"+code+"</h1>";
        emailService.sendEmail(u.getEmail(), emailBody);
    }
}
