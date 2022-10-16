package de.lohrfink.junit5.extension;

import de.lohrfink.junit5.extention.LFETYamlSource;

import java.lang.annotation.Annotation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

abstract class AbstractMockLfetYamlAnnotationBuilder<A extends Annotation, B extends AbstractMockLfetYamlAnnotationBuilder<A, B>> {


    static MockLfetYamlSourceBuilder lfetYamlSource() {
        return new MockLfetYamlSourceBuilder();
    }

    private AbstractMockLfetYamlAnnotationBuilder() {
    }

    protected abstract B getSelf();

    abstract A build();

    static class MockLfetYamlSourceBuilder extends AbstractMockLfetYamlAnnotationBuilder<LFETYamlSource, MockLfetYamlSourceBuilder> {

        private String[] resources = {};
        private String[] files = {};

        private MockLfetYamlSourceBuilder() {
        }

        @Override
        protected MockLfetYamlSourceBuilder getSelf() {
            return this;
        }

        MockLfetYamlSourceBuilder resource(String... resources) {
            this.resources = resources;
            return this;
        }

        MockLfetYamlSourceBuilder file(String... files) {
            this.files = files;
            return this;
        }

        @Override
        LFETYamlSource build() {
            LFETYamlSource annotation = mock(LFETYamlSource.class);

            when(annotation.resources()).thenReturn(this.resources);
            when(annotation.files()).thenReturn(this.files);

            return annotation;
        }

    }


}