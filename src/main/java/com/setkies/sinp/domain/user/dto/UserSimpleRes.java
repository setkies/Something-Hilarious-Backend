package com.setkies.sinp.domain.user.dto;

import com.setkies.sinp.domain.user.User;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserSimpleRes {
    private final Long id;
    private final String name;
    private final String email;
    private final String profileImage;

    public UserSimpleRes(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
    }
}
