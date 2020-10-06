package com.group.books.controllers;

import com.group.books.entities.Member;
import com.group.books.entities.dtos.LoginRequest;
import com.group.books.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final MemberService memberService;

    @Autowired
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    // @route   POST api/auth
    // @desc    Authenticate user & get token
    // @access  Public
    @PostMapping(value="/api/auth")
    public ResponseEntity<String> auth(@RequestBody LoginRequest loginRequest) {
        Member member = memberService.getMemberByLoginId(loginRequest.getLoginId());
        if(member != null) {
            return new ResponseEntity<>(member.getLoginId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
        }

    }
}
