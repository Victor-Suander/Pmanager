package com.java360.pmanager.infrastructure.dto;

import com.java360.pmanager.domain.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private final String id;
    private final String secret;
    private final String name;
    private final String email;

    public static MemberDTO create(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getSecret(),
                member.getName(),
                member.getEmail()
        );
    }
}
