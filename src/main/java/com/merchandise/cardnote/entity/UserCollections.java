package com.merchandise.cardnote.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Document(collection = "user")
@ToString
public class UserCollections {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;

    @Builder
    public UserCollections(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = "ROLE_USER";
    }
}