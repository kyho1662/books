package com.group.books.controllers;

import com.group.books.entities.Member;
import com.group.books.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class PageController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value="/")
    public String index() {
        Member testMember = new Member("test");
        memberService.insertMember(testMember);
        return "index";
    }
}
