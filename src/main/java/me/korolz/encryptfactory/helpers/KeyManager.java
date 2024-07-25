package me.korolz.encryptfactory.helpers;

import me.korolz.encryptfactory.cipher.KeyGen;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@Component
public class KeyManager {

    private static final String keyPath = "./config/encryption.key";

    public void loadKey() {
        Path path = Paths.get(keyPath);
        try {
            if (Files.notExists(path)) {
                SecretKey secretKey = createAndStoreKey(path);
                KeyGen.setInnerKey(secretKey);
            } else {
                SecretKey key = new SecretKeySpec(Files.readAllBytes(path), "AES");
                KeyGen.setInnerKey(key); //setting key
            }
        } catch (Exception e) {
            System.out.println("Error loading key: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private SecretKey createAndStoreKey(Path path) throws NoSuchAlgorithmException {
        SecretKey secretKey = KeyGen.createSecretKey128();

        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            fos.write(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }
}
