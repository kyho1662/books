package com.group.books.services;

import com.group.books.entities.Member;
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
}
