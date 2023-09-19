package com.merchandise.cardnote.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.merchandise.cardnote.entity.UserCollections;
import com.merchandise.cardnote.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAPIController {

    @Autowired
    private UserService shelterService;

    @PostMapping(value = "api/shelter/insert")
    public String insert(@RequestBody UserCollections user) {
        shelterService.insert(user.getUsername(), user.getPassword());
        return "데이터 삽입 완료!";
    }

    @GetMapping(value = "api/shelter/read")
    public List<UserCollections> findAll() {
        return shelterService.selectAll();
    }

}
