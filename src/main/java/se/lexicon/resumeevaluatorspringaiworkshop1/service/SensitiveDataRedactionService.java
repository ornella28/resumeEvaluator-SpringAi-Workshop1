package se.lexicon.resumeevaluatorspringaiworkshop1.service;

import org.springframework.stereotype.Service;

@Service
public class SensitiveDataRedactionService {

    public String redact(String text) {
        if (text == null) {
            return "";
        }

        return text
                // email
                .replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "[REDACTED]")
                // phone numbers, simple version
                .replaceAll("(\\+?\\d[\\d\\s().-]{7,}\\d)", "[REDACTED]")
                // Swedish personal number, simple version
                .replaceAll("\\b\\d{6,8}[-+]\\d{4}\\b", "[REDACTED]");
    }
}