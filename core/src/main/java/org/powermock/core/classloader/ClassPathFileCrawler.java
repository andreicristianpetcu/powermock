package org.powermock.core.classloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class ClassPathFileCrawler {

    private Set<ClassPathItem> filesFound = new HashSet<ClassPathItem>();

    Set<ClassPathItem> doFindAllClassPathResources() throws IOException {
        Set<ClassPathItem> result = new LinkedHashSet<ClassPathItem>();
        Enumeration<URL> resourceUrls = ClassPathFileCrawler.class.getClassLoader().getResources("");
        while (resourceUrls.hasMoreElements()) {
            URL classPathUrl = resourceUrls.nextElement();
            Set<ClassPathItem> walkedFiles = getAllNestedFiles(classPathUrl.getFile(), classPathUrl);
            result.addAll(walkedFiles);
        }
        return result;
    }

    Set<ClassPathItem> getAllNestedFiles(String fileOrDirectory, URL classPathUrl) {

        File fileObject = new File(fileOrDirectory);
        File[] childFiles = fileObject.listFiles();

        if (childFiles == null) return null;

        for (File childFile : childFiles) {
            String absolutePath = childFile.getAbsolutePath();
            if (childFile.isDirectory()) {
                getAllNestedFiles(absolutePath, classPathUrl);
            } else {
                if (absolutePath.endsWith(".class") && !absolutePath.contains("$")) {
                    filesFound.add(new ClassPathItem(absolutePath, classPathUrl));
                }
            }
        }
        return filesFound;
    }

}
