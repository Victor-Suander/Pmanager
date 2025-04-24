package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.exception.DuplicateMemberException;
import com.java360.pmanager.domain.exception.MemberNotFoundException;
import com.java360.pmanager.domain.repository.MemberRepository;
import com.java360.pmanager.infrastructure.dto.SaveMemberDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(SaveMemberDataDTO saveMemberData) {
        if(existsMemberWithEmail(saveMemberData.getEmail(), null)){
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }
        Member member = Member
                .builder()
                .name(saveMemberData.getName())
                .email(saveMemberData.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member loadMemberById(String memberId) {
        return memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public void deleteMember(String memberId) {
        Member member = loadMemberById(memberId);
        member.setDeleted(true);
    }

    @Transactional
    public Member updateMember(String memberId, SaveMemberDataDTO saveMemberData) {
        if(existsMemberWithEmail(saveMemberData.getEmail(), memberId)){
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }
     Member member = loadMemberById(memberId);

     member.setName(saveMemberData.getName());
     member.setEmail(saveMemberData.getEmail());

     return member;
    }

public List<Member> findMembers(String email) {
        List<Member> members;

        if(Objects.isNull(email)) {
            members = memberRepository.findAllNotDeleted2();
        } else {
            members = memberRepository
                    .findByEmailAndDeleted(email, false)
                    .map(List::of)
                    .orElse(List.of());
        }
        return members;
}

    private boolean existsMemberWithEmail(String email, String idToExclude) {
        return memberRepository
                .findByEmailAndDeleted(email,false)
                .filter(m -> !Objects.equals(m.getId(), idToExclude))
                .isPresent();

    }
}
