package me.korolz.encryptfactory.services;

import me.korolz.encryptfactory.cipher.Decryptor;
import me.korolz.encryptfactory.cipher.Encryptor;
import me.korolz.encryptfactory.cipher.KeyGen;
import me.korolz.encryptfactory.models.CipherModel;
import me.korolz.encryptfactory.repositories.CipherModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class CipherService {
    private CipherModelRepository modelRepository;
    @Value("${host.name}")
    private String hostname;

    @Autowired
    public CipherService(CipherModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public String getLink(String message) throws Exception {
        /*
        TODO
        Метод, где вызываем Encryptor и создаем ссылку, заносим в бд ключи
         */
        KeyPair keyPair = KeyGen.createKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        String encryptedMessage = Encryptor.encryptRSA(message, publicKey);
        String encodedPublicKey = Base64.getUrlEncoder().encodeToString(publicKey.getEncoded());
        String encodedPrivateKey = Base64.getUrlEncoder().encodeToString(privateKey.getEncoded());
        CipherModel model = new CipherModel(encodedPublicKey, encodedPrivateKey);
        modelRepository.save(model);
        return String.format(
                "http://%s/decrypt?key=%s&message=%s",
                hostname,
                encodedPublicKey,
                encryptedMessage
        );
    }

    public String decrypt(String key, String message) throws Exception {
        CipherModel model = modelRepository.findById(key).orElseThrow();
        modelRepository.delete(model);
        byte[] bytes = Base64.getUrlDecoder().decode(model.getPrivateKey()); //получаем байты
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes); //создаем спек
        KeyFactory kf = KeyFactory.getInstance("RSA"); //делаем из спека приватный RSA ключ
        PrivateKey privateKey = kf.generatePrivate(ks);
        return Decryptor.decryptRSA(message, privateKey);
    }
}
