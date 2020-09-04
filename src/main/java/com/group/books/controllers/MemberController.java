package com.group.books.controllers;

import com.group.books.entities.Member;
import com.group.books.entities.Suggestion;
import com.group.books.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    // @route   POST api/member
    // @desc    Register member
    // @access  Private
    @PostMapping(value="/api/member")
    public String registerMember(@RequestBody Member member) {

        try {
            memberService.insertMember(member);
            return "member registered";

        } catch(Exception err) {
            return err.getMessage();
        }
    }

    // @route   GET api/member
    // @desc    get all members
    // @access  Private
    @GetMapping(value="/api/member")
    public List<Member> getAllMembers() {

        try {
            return memberService.getAllMembers();

        } catch(Exception err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}
