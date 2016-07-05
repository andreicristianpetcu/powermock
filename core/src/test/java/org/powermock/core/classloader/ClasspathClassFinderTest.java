package org.powermock.core.classloader;

import org.assertj.core.api.FileAssert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClasspathClassFinderTest {

    @Test
    public void whenGetAllClassesFromClasspath_thenJDKClassIsReturned() {
        assertTrue(ClasspathClassFinder.getAllClassesFromClasspath().contains(ArrayList.class));
    }

    @Test
    public void whenGetAllClassesFromClasspath_thenLibraryClassIsReturned() {
        assertTrue(ClasspathClassFinder.getAllClassesFromClasspath().contains(FileAssert.class));
    }

    @Test
    public void whenGetAllClassesFromClasspath_thenPowermockClassIsReturned() {
        assertTrue(ClasspathClassFinder.getAllClassesFromClasspath().contains(HardToTransform.class));
    }

}