package com.frandz.produit.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="jwt")
public class Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String valeur;
    private boolean desactive;
    private boolean expire;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;
}
