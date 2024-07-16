package me.korolz.encryptfactory.repositories;

import me.korolz.encryptfactory.models.CipherModel;
import org.springframework.data.repository.CrudRepository;

public interface CipherModelRepository extends CrudRepository<CipherModel, String> {
}
