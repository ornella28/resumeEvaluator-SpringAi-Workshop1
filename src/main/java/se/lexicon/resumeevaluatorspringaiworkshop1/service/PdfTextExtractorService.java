package se.lexicon.resumeevaluatorspringaiworkshop1.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfTextExtractorService {

    private final Tika tika = new Tika();

    public String extractText(MultipartFile file) {
        try {
            return tika.parseToString(file.getInputStream());
        } catch (IOException | org.apache.tika.exception.TikaException e) {
            throw new RuntimeException("Could not extract text from PDF", e);
        }
    }
}