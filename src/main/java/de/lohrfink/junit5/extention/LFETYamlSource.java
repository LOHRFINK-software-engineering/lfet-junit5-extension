package de.lohrfink.junit5.extention;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ArgumentsSource(LFETYamlArgumentsProvider.class)
@Documented
public @interface LFETYamlSource {

    /**
     * The CSV classpath resources to use as the sources of arguments; must not
     * be empty unless {@link #files} is non-empty.
     */
    String[] resources() default {};

    /**
     * The CSV files to use as the sources of arguments; must not be empty
     * unless {@link #resources} is non-empty.
     */
    String[] files() default {};
}
