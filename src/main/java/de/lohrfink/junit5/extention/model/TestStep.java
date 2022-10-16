package de.lohrfink.junit5.extention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestStep {

    String instruction;
    String expectation;


    public List<String> getInstructionValues(String separator) {
        return getValues(getInstruction(), separator);
    }

    public List<String> getExpectationValues(String separator) {
        return getValues(getExpectation(), separator);
    }

    private List<String> getValues(String s, String separator) {
        List<String> result = new ArrayList<>();
        if (s != null) {
            for (String value : s.split(separator)) {
                result.add(value.trim());
            }
        }
        return result;
    }

}
