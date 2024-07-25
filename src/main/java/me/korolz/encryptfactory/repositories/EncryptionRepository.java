package me.korolz.encryptfactory.repositories;

import me.korolz.encryptfactory.models.Encryption;
import org.springframework.data.repository.CrudRepository;

public interface EncryptionRepository extends CrudRepository<Encryption, String> {
    Encryption findByLink(String link);
}
