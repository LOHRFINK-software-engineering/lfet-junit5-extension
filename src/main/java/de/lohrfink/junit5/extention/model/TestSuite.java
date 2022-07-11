package de.lohrfink.junit5.extention.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Objects;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestSuite {

    List<TestCase> testcases;

    String testsuite;

    public boolean isRecommendedTestCases() {
        return Objects.requireNonNull(testsuite).equalsIgnoreCase("Empfohlene Testfälle");
    }

}
