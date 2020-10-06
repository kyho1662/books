package com.group.books.controllers;

import com.group.books.entities.Suggestion;
import com.group.books.services.MemberService;
import com.group.books.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private MemberService memberService;

    // @route   POST api/suggestion
    // @desc    Create a post
    // @access  Private
    @PostMapping(value="/api/suggestion")
    public String postSuggestion(@RequestBody Suggestion suggestion) {

        try {
            // 생성 날짜 입력
            if(suggestion.getRegdate() == null)  {
                suggestion.setRegdate(new Date());
            }

            // 생성자(로그인한 멤버의 memberId) 입력
            //Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            suggestion.setCreator(memberService.getMemberById(2));
            suggestionService.insertSuggestion(suggestion);
            return "suggestion posted";

        } catch(Exception err) {
            return err.getMessage();
        }
    }

    // @route   GET api/suggestion
    // @desc    get suggestions
    // @access  Public
    @GetMapping(value="/api/suggestion")
    public List<Suggestion> suggestions() {
        return suggestionService.getAllSuggestions();
    }

    // @route   PUT api/suggestion
    // @desc    modify suggestions
    // @access  Private
    @PutMapping(value="/api/suggestion")
    public String modifySuggestion(@RequestBody Suggestion suggestion) {
        // find by suggestion id & update suggestion
        return "suggestion modified";
    }

    // @route   DELETE api/suggestion
    // @desc    delete suggestion
    // @access  Private
    @DeleteMapping(value="/api/suggestion")
    public String deleteSuggestion(@RequestParam int suggestionId) {
        suggestionService.deleteSuggestionById(suggestionId);
        return "suggestion deleted";
    }

}
