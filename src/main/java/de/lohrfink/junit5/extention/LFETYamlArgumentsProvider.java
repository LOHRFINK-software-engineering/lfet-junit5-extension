package de.lohrfink.junit5.extention;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.lohrfink.junit5.extention.model.TestSuite;
import de.lohrfink.junit5.extention.model.TestSuites;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.util.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LFETYamlArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<LFETYamlSource> {

    private final InputStreamProvider inputStreamProvider;

    private ObjectMapper objectMapper;

    private LFETYamlSource annotation;

    private List<Source> sources;


    public LFETYamlArgumentsProvider() {
        this(DefaultInputStreamProvider.INSTANCE);
    }

    public LFETYamlArgumentsProvider(InputStreamProvider inputStreamProvider) {
        this.inputStreamProvider = inputStreamProvider;
    }


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        return Preconditions.notEmpty(this.sources, "Resources or files must not be empty!")
                .stream()
                .map(source -> source.open(extensionContext))
                .map(this::readYaml)
                .flatMap(Collection::stream)
                .flatMap(testSuite -> testSuite.getTestcases().stream())
                .map(Arguments::of);
    }

    private List<TestSuite> readYaml(InputStream inputStream) {
        try {
            return objectMapper.readValue(inputStream, TestSuites.class).getTestSuites();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(LFETYamlSource annotation) {
       this.annotation = annotation;
       this.objectMapper = new ObjectMapper(new YAMLFactory());
       Stream<Source> resources = Stream.of(annotation.resources()).map(inputStreamProvider::classpathResource);
       Stream<Source> files = Stream.of(annotation.files()).map(inputStreamProvider::file);
       this.sources = Stream.concat(resources, files).collect(Collectors.toList());
    }

    @FunctionalInterface
    private interface Source {

        InputStream open(ExtensionContext context);

    }

    interface InputStreamProvider {

        InputStream openClasspathResource(Class<?> baseClass, String path);

        InputStream openFile(String path);

        default Source classpathResource(String path) {
            return context -> openClasspathResource(context.getRequiredTestClass(), path);
        }

        default Source file(String path) {
            return context -> openFile(path);
        }

    }

    private static class DefaultInputStreamProvider implements InputStreamProvider {

        private static final DefaultInputStreamProvider INSTANCE = new DefaultInputStreamProvider();

        @Override
        public InputStream openClasspathResource(Class<?> baseClass, String path) {
            Preconditions.notBlank(path, () -> "Classpath resource [" + path + "] must not be null or blank");
            //noinspection resource
            InputStream inputStream = baseClass.getResourceAsStream(path);
            return Preconditions.notNull(inputStream, () -> "Classpath resource [" + path + "] does not exist");
        }

        @Override
        public InputStream openFile(String path) {
            Preconditions.notBlank(path, () -> "File [" + path + "] must not be null or blank");
            try {
                return Files.newInputStream(Paths.get(path));
            }
            catch (IOException e) {
                throw new JUnitException("File [" + path + "] could not be read", e);
            }
        }

    }


}
