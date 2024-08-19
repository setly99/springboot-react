package com.kh.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile {
	private int userId;
    private String username;
    private String profileImageUrl;
    private LocalDateTime createdAt;
}
