package com.frandz.produit.service;

import com.frandz.produit.model.User;
import com.frandz.produit.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceimpl, UserDetailsService {

    private UserRepo userRepo;
    @Override
    public Optional<User> getByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepo.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found "+username);
        }else{
            return user.get();
        }
    }
}
