package de.lohrfink.junit5.extention;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.lohrfink.junit5.extention.model.TestSuite;
import de.lohrfink.junit5.extention.model.TestSuites;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.InputStream;
import java.util.stream.Stream;

public class LFETYamlArgumentsProvider implements ArgumentsProvider {

    final ObjectMapper objectMapper;

    public LFETYamlArgumentsProvider() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        final var testMethod = extensionContext.getRequiredTestMethod();
        final var testClass = testMethod.getDeclaringClass();

        final LFETYamlSource lfetYamlSource = testMethod.getAnnotation(LFETYamlSource.class);

        InputStream resourceAsStream = testClass.getResourceAsStream("/" + lfetYamlSource.value());
        TestSuites testSuites = objectMapper.readValue(resourceAsStream, TestSuites.class);

        return testSuites.getTestSuites().stream().flatMap(testsuite -> testsuite.getTestcases().stream()).map(Arguments::of);

    }
}
