package me.korolz.encryptfactory.controllers;

import me.korolz.encryptfactory.models.Encryption;
import me.korolz.encryptfactory.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class MainController {

    private EncryptionService encryptionService;

    @Autowired
    public MainController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping("/encrypt")
    public String encryptText(@RequestParam String message, Model model) {
        try {
            Encryption encryption = encryptionService.createEncryption(message);
            model.addAttribute("link", encryptionService.getLink(encryption));
            model.addAttribute("password", encryption.getPassword());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "result";
    }

    @GetMapping("/decrypt/{link}")
    public String decryptText(@PathVariable String link, Model model) {
        try {
            Encryption encryption = encryptionService.getEncryption(link);
            model.addAttribute("text", encryptionService.decrypt(encryption));
            model.addAttribute("password", encryption.getPassword());
        }
        catch (Exception e) {
            return "old";
        }
        return "decryption";
    }
}
