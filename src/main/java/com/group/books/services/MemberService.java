package com.group.books.services;

import com.group.books.entities.Member;
import com.group.books.entities.dtos.ResetPasswordRequest;
import com.group.books.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public List<Member> getAllMembers() {
        return repo.findAll();
    }

    public void insertMember(Member member) {
        repo.save(member);
    }

    public Member getMemberById(int memberId) {
        return repo.findById(memberId);
    }

    public Member getMemberByLoginId(String loginId) {
        return repo.findByLoginId(loginId);
    }

    public boolean isDuplicatedId(String loginId) {
        return repo.existsByLoginId(loginId);
    }

    public String findLoginId(String name, String email) {
        Member member = repo.findByNameAndEmail(name, email);
        return member.getLoginId();
    }

    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Member member = repo.findByLoginIdAndNameAndEmail(resetPasswordRequest.getLoginId(), resetPasswordRequest.getName(), resetPasswordRequest.getEmail());
        if(member != null) {
            member.setPassword(resetPasswordRequest.getPassword());
            repo.save(member);
            return true;
        } else {
            return false;
        }
    }

}
