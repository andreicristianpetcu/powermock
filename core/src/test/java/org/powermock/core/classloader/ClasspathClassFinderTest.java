package org.powermock.core.classloader;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.Test;
import org.powermock.filter.JacocoMethodsFilter;
import org.powermock.filter.MockMethodsFilter;

import java.lang.reflect.Method;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ClasspathClassFinderTest {

    @Test
    public void whenGetAllClassesFromClasspath_thenLibraryClassIsReturned() throws NoSuchFieldException, IllegalAccessException {
        new BigDecimalComparator();

        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspath();
        assertTrue(allClassesFromClasspath.contains(BigDecimalComparator.class));
    }

    @Test
    public void whenGetAllClassesFromClasspath_thenPowermockClassIsReturned() throws NoSuchFieldException, IllegalAccessException {
        new HardToTransform();

        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspath();
        assertTrue(allClassesFromClasspath.contains(HardToTransform.class));
    }

    @Test
    public void whenGetAllClassesFromClasspath_thenInterfaceAndImplementationAreReturned() throws NoSuchFieldException, IllegalAccessException {
        new JacocoMethodsFilter();
        new MockMethodsFilter(){ public boolean shouldSkipMockingMethod(Method method) { return false; } };

        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspath();

        assertTrue(allClassesFromClasspath.contains(MockMethodsFilter.class));
        assertTrue(allClassesFromClasspath.contains(JacocoMethodsFilter.class));
    }

    @Test
    public void getAllClassesFromClasspathImplementingMockMethodFilters_thenJacocoMethodFiltersIsReturned() throws NoSuchFieldException, IllegalAccessException {
        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspathImplementing(MockMethodsFilter.class);

        assertTrue(allClassesFromClasspath.contains(JacocoMethodsFilter.class));
    }

}