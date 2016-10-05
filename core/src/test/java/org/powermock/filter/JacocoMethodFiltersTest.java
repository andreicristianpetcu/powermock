package org.powermock.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.FakeMethodProducer;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class JacocoMethodFiltersTest {

    @InjectMocks
    private JacocoMethodsFilter jacocoMethodFilters;

    @Test
    public void givenJacocoMethodName_whenShouldSkipMockingMethod_thenReturnFalse() throws NoSuchMethodException {
        Method jacocoMethod = FakeMethodProducer.getMethodWithName("$jacocoInit");
        assertTrue(jacocoMethodFilters.shouldSkipMockingMethod(jacocoMethod));
    }

    @Test
    public void givenJacocoMethodName_whenShouldSkipMockingMethod_thenReturnTrue() throws NoSuchMethodException {
        Method jacocoMethod = FakeMethodProducer.getMethodWithName("nonJacoco");
        assertFalse(jacocoMethodFilters.shouldSkipMockingMethod(jacocoMethod));
    }

}