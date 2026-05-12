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
                - strengths: list of matching skills or experiences
                - missingSkills: list of important missing skills
                - feedback: list of clear improvement suggestions
                """.formatted(request.resume(), request.jobDescription());

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .entity(ResumeEvaluationResponse.class);
    }
}