package se.lexicon.resumeevaluatorspringaiworkshop1.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationRequest;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationResponse;

@Service
public class ResumeEvaluatorService {

    private final ChatClient chatClient;

    public ResumeEvaluatorService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ResumeEvaluationResponse evaluate(ResumeEvaluationRequest request) {

        String prompt = """
                You are a professional technical recruiter.

                Evaluate the resume against the job description.

                Resume:
                %s

                Job description:
                %s

                 Return the result as a structured object with:
                 - matchScore: number from 0 to 100
                 - summary: short summary of the match
                 - matchedSkills: list of skills found in both resume and job description
                 - strengths: list of strong points in the resume
                 - missingSkills: list of important skills from the job description that are missing or weak in the resume
                 - improvementSuggestions: list of clear, actionable suggestions to improve the resume
                """.formatted(request.resume(), request.jobDescription());

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .entity(ResumeEvaluationResponse.class);
    }
}