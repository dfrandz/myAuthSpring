package com.frandz.produit.repository;

import com.frandz.produit.model.Jwt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JwtRepo extends CrudRepository<Jwt, Integer> {
    Optional<Jwt> findByValeur(String value);

    @Query("FROM Jwt j WHERE j.expire = :expire AND j.desactive = :desactive AND j.user.email = :email ")
    Optional<Jwt> findUserValidToken(String email, boolean desactive, boolean expire);
}
