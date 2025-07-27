package org.example.allure_ai_analyser.input_models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TestCaseErrorModel {
    private String testName;
    private String statusMessage;
    private String statusTrace;
}
