package com.merchandise.cardnote.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.merchandise.cardnote.entity.SecurityUser;
import com.merchandise.cardnote.service.CardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CardService cardService;

    @GetMapping(value = "/")
    public String index(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getUser());
            model.addAttribute("cardList", cardService.아이디로찾기(principal.getUser().getId()));
        }
        return new String("index");
    }

}
