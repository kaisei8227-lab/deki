package com.example.cardgame.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.cardgame.model.MatchResult;

@Repository
public class MatchResultRepository {
    private final List<MatchResult> resultList = new ArrayList<>();

    public void save(MatchResult result) {
        resultList.add(result);
    }

    public List<MatchResult> findByUsername(String username) {
        return resultList.stream()
                .filter(r -> r.getUsername().equals(username))
                .collect(Collectors.toList());
    }
}