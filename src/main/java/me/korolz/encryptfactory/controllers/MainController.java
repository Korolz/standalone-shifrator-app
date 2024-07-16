package me.korolz.encryptfactory.controllers;

import me.korolz.encryptfactory.services.CipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private CipherService cipherService;

    @Autowired
    public MainController(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping("/encrypt")
    public String encryptText(@RequestParam String message, Model model) {
        try {
            model.addAttribute("link", cipherService.getLink(message));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "result";
    }

    @GetMapping("/decrypt")
    public String decryptText(@RequestParam String key, @RequestParam String message, Model model) {
        try {
            model.addAttribute("text", cipherService.decrypt(key,message));
        }
        catch (Exception e) {
            return "old";
        }
        return "decryption";
    }
}
