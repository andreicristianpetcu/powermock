package org.powermock.core.classloader;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.Test;

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

}