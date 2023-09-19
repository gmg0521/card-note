package com.merchandise.cardnote.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.merchandise.cardnote.dto.RegistryReqeust;
import com.merchandise.cardnote.entity.UserCollections;
import com.merchandise.cardnote.repository.UserCollectionsRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserCollectionsRepo userCollectionsRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/registry")
    public String registryForm(Model model) {
        model.addAttribute("user", new RegistryReqeust());
        return new String("user/registry");
    }

    @PostMapping(value = "/registry")
    public String registry(@ModelAttribute RegistryReqeust registryReqeust) {
        UserCollections user = UserCollections.builder()
                .username(registryReqeust.getUsername())
                .password(passwordEncoder.encode(registryReqeust.getPassword()))
                .build();

        userCollectionsRepo.save(user);

        return "redirect:/user/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("user", new RegistryReqeust());
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return new String("user/login");
    }

}
