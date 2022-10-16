package de.lohrfink.junit5.extention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCase {

    String testcase;

    String info;

    List<String> tags;

    List<TestStep> teststeps;

    public String instruction(String key) {
        Optional<TestStep> optionalTestStep = teststeps.stream().filter(testStep -> {
            List<String> instructionValues = testStep.getInstructionValues("=");
            return instructionValues.contains(key);
        }).findFirst();
        if (optionalTestStep.isPresent()) {
            return optionalTestStep.get().getInstructionValues("=").get(1);
        } else {
            return "";
        }
    }

    public String expectation(String key) {
        Optional<TestStep> optionalTestStep = teststeps.stream().filter(testStep -> {
            List<String> expectationValues = testStep.getExpectationValues("=");
            return expectationValues.contains(key);
        }).findFirst();
        if (optionalTestStep.isPresent()) {
            return optionalTestStep.get().getExpectationValues("=").get(1);
        } else {
            return "";
        }
    }

}
