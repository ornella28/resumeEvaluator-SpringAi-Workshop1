package se.lexicon.resumeevaluatorspringaiworkshop1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationRequest;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeEvaluatorController {

    @PostMapping("/evaluate")
    public ResumeEvaluationResponse evaluate(@RequestBody ResumeEvaluationRequest request) {

        return new ResumeEvaluationResponse(
                80,
                List.of("Good Java knowledge", "Some backend experience"),
                List.of("Spring AI", "Docker"),
                List.of("Add more Spring Boot project examples", "Mention specific tools used")
        );

    }
}
