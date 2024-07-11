package me.korolz.encryptfactory.controllers;

import me.korolz.encryptfactory.Services.Decryptor;
import me.korolz.encryptfactory.Services.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private Encryptor encryptor;
    private Decryptor decryptor;
    @Value("${host.name}")
    private String hostname;

    @Autowired
    public MainController(Encryptor encryptor, Decryptor decryptor) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping("/encrypt")
    public String encryptText(@RequestParam String message, Model model) {
        try {
            model.addAttribute("link", "http://" + hostname + "/decrypt/" + encryptor.encrypt(message));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "result";
    }

    @GetMapping("/decrypt/{message}")
    public String decryptText(@PathVariable String message, Model model) {
        try {
            model.addAttribute("text", decryptor.decrypt(message));
        }
        catch (Exception e) {
            return "old";
        }
        return "decryption";
    }
}
