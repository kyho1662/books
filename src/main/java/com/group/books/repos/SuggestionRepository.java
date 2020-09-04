package com.group.books.repos;

import com.group.books.entities.Member;
import com.group.books.entities.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
}
