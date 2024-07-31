package me.korolz.encryptfactory.api;


import me.korolz.encryptfactory.models.Encryption;
import me.korolz.encryptfactory.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private EncryptionService encryptionService;

    @Autowired
    public ApiController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostMapping("/encryption")
    //TODO сделать DTO для Encryption по API, чтобы не передавалась заглушка зашифрованного текста
    public ResponseEntity<Encryption> encryptText(@RequestParam String message, Model model) {
        try {
            Encryption encryption = encryptionService.createEncryption(message);
            encryption.setEncrypted("dummy encrypted text");
            return ResponseEntity.status(HttpStatus.CREATED).body(encryption);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/decryption/{link}")
    public ResponseEntity<String> decryptText(@PathVariable String link, Model model) {
        try {
            Encryption encryption = encryptionService.getEncryption(link);
            String decrypted = encryptionService.decrypt(encryption);
            return ResponseEntity.status(HttpStatus.CREATED).body(decrypted);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
