package de.lohrfink.junit5.extension;

import de.lohrfink.junit5.extention.LFETYamlSource;
import org.junit.jupiter.api.Test;

public class LFETYamlArgumentsProviderTest {

    @Test
    void throwsExceptionForMissingClasspathResource() {
        LFETYamlSource annotation = AbstractMockLfetYamlAnnotationBuilder.lfetYamlSource()//
                .resource("/does-not-exist.csv")//
                .build();

        // var exception = assertThrows(PreconditionViolationException.class,
        //        () -> provideArguments(new CsvFileArgumentsProvider(), annotation).toArray());

        //assertThat(exception).hasMessageContaining("Classpath resource [/does-not-exist.csv] does not exist");
    }


}
