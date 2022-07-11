package de.lohrfink.junit5.extention;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ArgumentsSource(LFETYamlArgumentsProvider.class)
public @interface LFETYamlSource {

    /**
     * @return the name of the yaml resource that contains the test data.
     */
    String value();
}
