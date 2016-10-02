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
    private JacocoMethodFilters jacocoMethodFilters;

    @Test
    public void givenJacocoMethodName_whenShouldMockMethod_thenReturnFalse() throws NoSuchMethodException {
        Method jacocoMethod = FakeMethodProducer.getMethodWithName("$jacocoInit");
        assertFalse(jacocoMethodFilters.shouldMockMethod(jacocoMethod));
    }

    @Test
    public void givenJacocoMethodName_whenShouldMockMethod_thenReturnTrue() throws NoSuchMethodException {
        Method jacocoMethod = FakeMethodProducer.getMethodWithName("nonJacoco");
        assertTrue(jacocoMethodFilters.shouldMockMethod(jacocoMethod));
    }

}