package se.lexicon.resumeevaluatorspringaiworkshop1.dto;

import java.util.List;

public record ResumeEvaluationResponse(
        int matchScore,
        String summary,
        List<String> matchedSkills,
        List<String> strengths,
        List<String> missingSkills,
        List<String> improvementSuggestions
) {


}
