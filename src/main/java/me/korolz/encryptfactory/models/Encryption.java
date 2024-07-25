package me.korolz.encryptfactory.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class Encryption {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String link;
    @Column(columnDefinition = "TEXT")
    private String encrypted;
    @Column(columnDefinition = "TEXT")
    private String password;

    public Encryption(String link, String encrypted, String password) {
        this.link = link;
        this.encrypted = encrypted;
        this.password = password;
    }
}
