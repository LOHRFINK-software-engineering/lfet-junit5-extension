package de.lohrfink.junit5.extension.example;

import de.lohrfink.junit5.extention.LFETYamlSource;
import de.lohrfink.junit5.extention.model.TestCase;
import org.junit.jupiter.params.ParameterizedTest;

public class KaffeeVollautomatTest {

    @ParameterizedTest
    @LFETYamlSource("Kaffeevollautomat_LF2_using_ti_group_V10.testcases.yaml")
    void recommendedTestcases(TestCase testCase) {
        testCase.getTeststeps().forEach(System.out::println);
        new Kaffeevollautomat().execute(new Kaffeevollautomat.Model(Kaffeevollautomat_LF2_using_ti_group_V10DT.GewaehltesGetraenk.$001, true, false, false, false, true));
    }

}
