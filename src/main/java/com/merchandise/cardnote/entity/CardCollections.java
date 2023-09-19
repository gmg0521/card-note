package com.merchandise.cardnote.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Document(collection = "card")
public class CardCollections {

    @Id
    private String id;
    private String userId;
    private String title;
    private String content;
    private String tags;

    @Builder
    public CardCollections(String id, String userId, String title, String content, String tags) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

}
