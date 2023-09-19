package com.merchandise.cardnote.api;

import com.merchandise.cardnote.dto.ResponseDTO;
import com.merchandise.cardnote.entity.CardCollections;
import com.merchandise.cardnote.service.CardService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestController
public class CardAPIController {

    private final CardService cardService;

    @GetMapping(value = "/api/cards")
    public ResponseDTO<List<CardCollections>> getAllCard() {
        return new ResponseDTO<>(HttpStatus.OK.value(), cardService.카드목록());
    }

    @GetMapping(value = "/api/cards/")
    public ResponseDTO<List<CardCollections>> getCardsByTitle(@RequestParam(required = true) String userid,
            @RequestParam(required = false) String title) {
        if (title == null) {
            return new ResponseDTO<List<CardCollections>>(HttpStatus.OK.value(), cardService.아이디로찾기(userid));
        }
        return new ResponseDTO<List<CardCollections>>(HttpStatus.OK.value(), cardService.제목으로찾기(userid, title));
    }

    @PostMapping(value = "api/cards/save")
    public ResponseDTO<Integer> save(@RequestBody CardCollections card) {
        cardService.카드저장(card);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping(value = "api/cards/{id}")
    public ResponseDTO<Integer> edit(@PathVariable String id, @RequestBody CardCollections card) {
        cardService.카드수정(id, card);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

}
