package org.powermock.core.classloader;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.Test;
import org.powermock.filter.JacocoMethodFilters;
import org.powermock.filter.MockMethodFilters;

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
        new JacocoMethodFilters();
        new MockMethodFilters(){ public boolean shouldMockMethod(Method method) { return false; } };

        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspath();

        assertTrue(allClassesFromClasspath.contains(MockMethodFilters.class));
        assertTrue(allClassesFromClasspath.contains(JacocoMethodFilters.class));
    }

    @Test
    public void getAllClassesFromClasspathImplementingMockMethodFilters_thenJacocoMethodFiltersIsReturned() throws NoSuchFieldException, IllegalAccessException {
        Collection<Class> allClassesFromClasspath = ClasspathClassFinder.getAllClassesFromClasspathImplementing(MockMethodFilters.class);

        assertTrue(allClassesFromClasspath.contains(JacocoMethodFilters.class));
    }

}