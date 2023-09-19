package com.merchandise.cardnote.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merchandise.cardnote.entity.UserCollections;

public interface UserCollectionsRepo extends MongoRepository<UserCollections, String> {

    Optional<UserCollections> findByUsername(String username);
}