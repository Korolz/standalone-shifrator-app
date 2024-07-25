package me.korolz.encryptfactory.services;

import me.korolz.encryptfactory.cipher.Decryptor;
import me.korolz.encryptfactory.cipher.Encryptor;
import me.korolz.encryptfactory.cipher.KeyGen;
import me.korolz.encryptfactory.helpers.RandomString;
import me.korolz.encryptfactory.models.Encryption;
import me.korolz.encryptfactory.repositories.EncryptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {
    private EncryptionRepository encryptionRepository;
    @Value("${host.name}")
    private String hostname;

    @Autowired
    public EncryptionService(EncryptionRepository modelRepository) {
        this.encryptionRepository = modelRepository;
    }

    public Encryption createEncryption(String message) throws Exception {
        String innerEncryption = Encryptor.encryptAES(message, KeyGen.getInnerKey());

        SecretKey secretKey = KeyGen.createSecretKey128();
        String outerEncryption = Encryptor.encryptAES(innerEncryption, secretKey);

        String password = Base64.getUrlEncoder().encodeToString(secretKey.getEncoded());

        String linkPart = RandomString.create();

        Encryption model = new Encryption(linkPart, outerEncryption, password);
        encryptionRepository.save(model);
        return model;
    }

    public String getLink(Encryption encryption) {
        return String.format(
                "http://%s/decrypt/%s",
                hostname,
                encryption.getLink()
        );
    }

    public String decrypt(Encryption encryption) throws Exception{
        byte[] outerBytes = Base64.getUrlDecoder().decode(encryption.getPassword()); //get bytes
        SecretKey key = new SecretKeySpec(outerBytes, "AES"); //get secondary key
        String decryptedOnce = Decryptor.decryptAES(encryption.getEncrypted(), key);
        encryptionRepository.delete(encryption);
        return Decryptor.decryptAES(decryptedOnce, KeyGen.getInnerKey());
    }

    public Encryption getEncryption(String link) throws Exception {
        Encryption model = encryptionRepository.findByLink(link);
        if (model == null) {
            throw new Exception("Entity not found");
        }
        return model;
    }
}
