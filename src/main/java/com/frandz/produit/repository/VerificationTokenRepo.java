package com.frandz.produit.repository;

import com.frandz.produit.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long>{
    VerificationToken findByToken(String token);
}
