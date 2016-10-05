package org.powermock.core.classloader;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class ClasspathClassFinder {

    public static Set<Class> getAllClassesFromClasspath() {
        ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();

        return getClassesFromClassLoader(currentThreadClassLoader);
    }

    public static Set<Class> getAllClassesFromClasspathImplementing(final Class interfaceThatIsImplemented) {
        Set<Class> allClassesFromClasspath = getAllClassesFromClasspath();
        Set<Class> classesImplementingTheInterface = new HashSet<Class>();

        for(Class classThatMightBeAssignable:allClassesFromClasspath){
            boolean implementsTheInterface = interfaceThatIsImplemented.isAssignableFrom(classThatMightBeAssignable);
            if(implementsTheInterface){
                classesImplementingTheInterface.add(classThatMightBeAssignable);
            }
        }

        return classesImplementingTheInterface;
    }

    private static Set<Class> getClassesFromClassLoader(ClassLoader cleasLoaderToSearchFrom) {
        Set<Class> foundClasses = new HashSet<Class>();
        while (cleasLoaderToSearchFrom != null) {
            Vector<Class> classesVector = getClassesVector(cleasLoaderToSearchFrom);
            for (Class foundClass:classesVector) {
                foundClasses.add(foundClass);
            }
            cleasLoaderToSearchFrom = cleasLoaderToSearchFrom.getParent();
        }
        return foundClasses;
    }

    private static Vector<Class> getClassesVector(ClassLoader classLoaderToGetIteratorFrom) {
        Class superclassClassLoader = getSuperclassClassLoader(classLoaderToGetIteratorFrom);
        return getClassesField(classLoaderToGetIteratorFrom, superclassClassLoader);
    }

    private static Vector getClassesField(ClassLoader classLoaderToGetIteratorFrom, Class superclassClassLoader) {
        java.lang.reflect.Field classLoaderClassesField = null;
        Vector classLoaderClasses;
        try {
            classLoaderClassesField = superclassClassLoader
                    .getDeclaredField("classes");
            classLoaderClassesField.setAccessible(true);
            classLoaderClasses = (Vector) classLoaderClassesField.get(classLoaderToGetIteratorFrom);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("No field classes found");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("You cannot access this field");
        }
        return classLoaderClasses;
    }

    private static Class getSuperclassClassLoader(ClassLoader classLoaderToGetIteratorFrom) {
        Class classloaderClass = classLoaderToGetIteratorFrom.getClass();
        while (classloaderClass != ClassLoader.class) {
            classloaderClass = classloaderClass.getSuperclass();
        }
        return classloaderClass;
    }


}
