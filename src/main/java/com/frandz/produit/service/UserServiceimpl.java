package com.frandz.produit.service;

import com.frandz.produit.model.User;

import java.util.Optional;

public interface UserServiceimpl {
    Optional<User> getByEmail(String email);


}
