package com.example.market.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Profile {
	private long id;
    private String userId;
    private String userType;
    private String name;
    private String email;
    private String image;
    private String sessionId;
}