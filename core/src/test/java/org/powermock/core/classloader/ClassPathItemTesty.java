package org.powermock.core.classloader;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ClassPathItemTesty {

    @Test
    public void test() throws MalformedURLException {
        String file = "/home/andrei/Dev/jacoco-offline-powermock-junit/target/test-classes/StaticClassTestngTest.class";
        URL url = new URL("file:/home/andrei/Dev/jacoco-offline-powermock-junit/target/test-classes/");
        new ClassPathItem(file, url);
    }
}