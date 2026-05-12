package se.lexicon.resumeevaluatorspringaiworkshop1.controller;

import org.springframework.web.bind.annotation.*;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationRequest;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationResponse;
import se.lexicon.resumeevaluatorspringaiworkshop1.service.ResumeEvaluatorService;

@RestController
@RequestMapping("/api/resume")
public class ResumeEvaluatorController {

    private final ResumeEvaluatorService service;

    public ResumeEvaluatorController(ResumeEvaluatorService service) {
        this.service = service;
    }

    @PostMapping("/evaluate")
    public ResumeEvaluationResponse evaluate(@RequestBody ResumeEvaluationRequest request) {

        return service.evaluate(request);
    }
}
