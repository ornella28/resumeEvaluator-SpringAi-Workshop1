package se.lexicon.resumeevaluatorspringaiworkshop1.service;

import org.springframework.stereotype.Service;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationRequest;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationResponse;

import java.util.List;

@Service
public class ResumeEvaluatorService {

    public ResumeEvaluationResponse evaluate(ResumeEvaluationRequest request) {

        return new ResumeEvaluationResponse(
                80,
                List.of("Good Java knowledge", "Some backend experience"),
                List.of("Spring AI", "Docker"),
                List.of("Add more Spring Boot project examples", "Mention specific tools used")
        );
    }
}