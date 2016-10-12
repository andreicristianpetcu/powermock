package org.powermock.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class SpecificClassClassLoader extends ClassLoader {

    private String classFileToLoad;

    public SpecificClassClassLoader(String classFileToLoad) {
        this.classFileToLoad = classFileToLoad;
    }

    public Class loadClass(String fullClassName) throws ClassNotFoundException {
        if (isProhibitedPackage(fullClassName) || isNotTheClassIWantToLoad(fullClassName)) {
            return super.loadClass(fullClassName);
        }

        try {
            return loadClassFromUrl(fullClassName, "file://" + classFileToLoad);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load class from file " + classFileToLoad);
        }
    }

    private boolean isNotTheClassIWantToLoad(String fullClassName) {
        return !classFileToLoad.endsWith(fullClassName + ".class");
    }

    private boolean isProhibitedPackage(String fullClassName) {
        return fullClassName.startsWith("java.lang");
    }

    private Class loadClassFromUrl(String fullClassName, String classFilePath) throws IOException {
        InputStream input = new URL(classFilePath).openConnection().getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = input.read();
        while (data != -1) {
            buffer.write(data);
            data = input.read();
        }
        input.close();
        byte[] classData = buffer.toByteArray();
        return defineClass(fullClassName, classData, 0, classData.length);
    }
}
