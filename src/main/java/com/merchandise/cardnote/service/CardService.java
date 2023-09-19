package com.merchandise.cardnote.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merchandise.cardnote.entity.CardCollections;
import com.merchandise.cardnote.repository.CardCollectionsRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final CardCollectionsRepo cardRepository;

    @Transactional
    public void 카드저장(CardCollections card) {
        cardRepository.save(card);
    }

    @Transactional
    public List<CardCollections> 카드목록() {
        return cardRepository.findAll();
    }

    @Transactional
    public List<CardCollections> 아이디로찾기(String id) {
        return cardRepository.findByUserId(id);
    }

    @Transactional
    public void 카드수정(String id, CardCollections tCard) {
        CardCollections card = cardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("카드 수정 실패: 아이디를 찾을 수 없습니다!");
        });

        card.setTitle(tCard.getTitle());
        card.setContent(tCard.getContent());
        card.setTags(tCard.getTags());
        log.info("cards : {}", card);
        cardRepository.save(card);
    }

    @Transactional
    public List<CardCollections> 제목으로찾기(String id, String keyword) {
        Iterator<CardCollections> cardList = cardRepository.findByUserId(id).iterator();
        List<CardCollections> titleCardList = new ArrayList<>();
        while (cardList.hasNext()) {
            CardCollections card = cardList.next();
            if (card.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                titleCardList.add(card);
        }
        return titleCardList;
    }
}
