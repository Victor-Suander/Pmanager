package com.java360.pmanager.infrastructure.controller;

import com.java360.pmanager.domain.applicationservice.MemberService;
import com.java360.pmanager.domain.applicationservice.ProjectService;
import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.infrastructure.dto.MemberDTO;
import com.java360.pmanager.infrastructure.dto.ProjectDTO;
import com.java360.pmanager.infrastructure.dto.SaveMemberDataDTO;
import com.java360.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.java360.pmanager.infrastructure.controller.RestConstants.PATH_MEMBERS;
import static com.java360.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MemberRestResource {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid SaveMemberDataDTO saveMemberData) {
        Member member= memberService.createMember(saveMemberData);

        return ResponseEntity
                .created(URI.create(PATH_MEMBERS + "/" + member.getId()))
                .body(MemberDTO.create(member));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMemberById(@PathVariable("id") String memberId) {
        Member member = memberService.loadMemberById(memberId);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(
            @PathVariable("id") String memberId,
            @Valid @RequestBody SaveMemberDataDTO saveMemberData
    ) {
        Member member = memberService.updateMember(memberId, saveMemberData);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

    // -> GET .../members
    // -> GET .../members?email={email}
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findMembers(
            @RequestParam(value = "email", required = false) String email
    ) {
        List<Member> members = memberService.findMembers(email);
        return ResponseEntity.ok(members
                .stream()
                .map(MemberDTO::create)
                .toList());
    }
}
