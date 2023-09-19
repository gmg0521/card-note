package com.merchandise.cardnote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merchandise.cardnote.entity.CardCollections;
import java.util.List;

public interface CardCollectionsRepo extends MongoRepository<CardCollections, String> {

    List<CardCollections> findByUserId(String userId);

}
