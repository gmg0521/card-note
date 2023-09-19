package com.merchandise.cardnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merchandise.cardnote.entity.UserCollections;
import com.merchandise.cardnote.repository.UserCollectionsRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserCollectionsRepo userCollectionsRepo;

    public void insert(String name, String password) {
        UserCollections entity = UserCollections.builder()
                .username(name)
                .password(password)
                .build();

        userCollectionsRepo.save(entity);

    }

    public List<UserCollections> selectAll() {
        List<UserCollections> list_1 = userCollectionsRepo.findAll();

        return list_1;
    }
}
