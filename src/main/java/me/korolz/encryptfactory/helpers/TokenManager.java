package me.korolz.encryptfactory.helpers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TokenManager {

    private static final String tokenPath = "./config/BearerToken.txt";

    @Getter
    private static String authToken;

    public void loadToken() {
        Path path = Paths.get(tokenPath);
        try {
            if (Files.notExists(path)) {
                authToken = createAndStoreToken(path);
            } else {
                authToken = Files.readString(path);
            }
        } catch (Exception e) {
            System.out.println("Error loading token: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String createAndStoreToken(Path path) {
        String token = RandomString.create(50);

        try (FileWriter fw = new FileWriter(path.toFile())) {
            fw.write(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
