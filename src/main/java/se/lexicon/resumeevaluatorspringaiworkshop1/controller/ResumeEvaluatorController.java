package se.lexicon.resumeevaluatorspringaiworkshop1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationRequest;
import se.lexicon.resumeevaluatorspringaiworkshop1.dto.ResumeEvaluationResponse;
import se.lexicon.resumeevaluatorspringaiworkshop1.service.PdfTextExtractorService;
import se.lexicon.resumeevaluatorspringaiworkshop1.service.ResumeEvaluatorService;
import se.lexicon.resumeevaluatorspringaiworkshop1.service.SensitiveDataRedactionService;

@RestController
@RequestMapping("/api/resume")
public class ResumeEvaluatorController {

    private final ResumeEvaluatorService service;
    private final PdfTextExtractorService  pdfTextExtractorService;
    private final SensitiveDataRedactionService redactionService;


    public ResumeEvaluatorController(
            ResumeEvaluatorService service,
            PdfTextExtractorService pdfTextExtractorService,
            SensitiveDataRedactionService redactionService
    ) {
        this.service = service;
        this.pdfTextExtractorService = pdfTextExtractorService;
        this.redactionService = redactionService;
    }

    @PostMapping(value = "/evaluate/pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResumeEvaluationResponse evaluatePdf(
            @RequestParam("resumeFile") MultipartFile resumeFile,
            @RequestParam("jobDescriptionFile") MultipartFile jobDescriptionFile
    ) {
        String resumeText = pdfTextExtractorService.extractText(resumeFile);
        String jobDescriptionText = pdfTextExtractorService.extractText(jobDescriptionFile);

        String redactedResume = redactionService.redact(resumeText);
        String redactedJobDescription = redactionService.redact(jobDescriptionText);

        ResumeEvaluationRequest request = new ResumeEvaluationRequest(
                redactedResume,
                redactedJobDescription
        );

        return service.evaluate(request);
    }
}
