package com.group.books.controllers;

import com.group.books.entities.Member;
import com.group.books.entities.dtos.FindIdRequest;
import com.group.books.entities.dtos.ResetPasswordRequest;
import com.group.books.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // @route   GET api/member/{memberId}
    // @desc    get member by id
    // @access  Public

    // @route   POST api/member
    // @desc    Register member
    // @access  Public
    @PostMapping(value="/api/member")
    public ResponseEntity<String> registerMember(@RequestBody Member member) {
        try {
            if(memberService.isDuplicatedId(member.getLoginId())) {
                return new ResponseEntity<>("Member already exists", HttpStatus.CONFLICT);
            } else {
                memberService.insertMember(member);
                return new ResponseEntity<>("Member registered", HttpStatus.OK);
            }

        } catch(Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @route   GET api/idCheck/{loginId}
    // @desc    Check duplicated id
    // @access  Public
    @GetMapping(value="/api/idCheck/{loginId}")
    public ResponseEntity<String> idCheck(@PathVariable String loginId) {
        try {
            if(memberService.isDuplicatedId(loginId)) {
                return new ResponseEntity<>("ID Duplicated", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>("ID Available", HttpStatus.OK);
            }
        } catch(Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // @route   GET api/members
    // @desc    get all members
    // @access  Private
    @GetMapping(value="/api/members")
    public List<Member> getAllMembers() {

        try {
            return memberService.getAllMembers();

        } catch(Exception err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    // @route   POST api/member/findId
    // @desc    Find login ID
    // @access  Public
    @PostMapping(value="/api/member/findId")
    public ResponseEntity<String> findId(@RequestBody FindIdRequest findIdRequest) {
        try {
            String result = memberService.findLoginId(findIdRequest.getName(), findIdRequest.getEmail());
            if(result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
            }

        } catch(Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @route   PUT api/member/resetPassword
    // @desc    Reset password
    // @access  Public
    @PutMapping(value="/api/member/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            if(memberService.resetPassword(resetPasswordRequest)){
                return new ResponseEntity<>("Password updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
            }


        } catch(Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
