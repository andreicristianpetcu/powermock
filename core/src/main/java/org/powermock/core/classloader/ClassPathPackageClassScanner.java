package org.powermock.core.classloader;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

class ClassPathPackageClassScanner {

    private final String packageToScan;
    private LinkedHashSet<Class> classes;

    public ClassPathPackageClassScanner(String packageToScan) {
        this.packageToScan = packageToScan;
        this.classes = new LinkedHashSet<Class>();
    }

    public Set<Class> scanClassesInPackage() {
        try {
            ClassPathFileCrawler classPathFileCrawler = new ClassPathFileCrawler();
            for (ClassPathItem resource : classPathFileCrawler.doFindAllClassPathResources()) {
                if (resource.fullyQualifiedClassName.startsWith(packageToScan)) {
                    loadMyClassIfNeeded(resource);
                }
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Bad arguments ", ex);
        }
        return classes;
    }

    private void loadMyClassIfNeeded(ClassPathItem classPathItem) {
        SpecificClassClassLoader classLoader = new SpecificClassClassLoader(classPathItem.file);
        try {
            Class aClass = classLoader.loadClass(classPathItem.fullyQualifiedClassName);
            classes.add(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

