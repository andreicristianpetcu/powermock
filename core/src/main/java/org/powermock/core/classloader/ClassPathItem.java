package org.powermock.core.classloader;

import java.net.URL;

class ClassPathItem {

    public final String file;
    public final URL classLoaderUrl;
    public final String classSimpleName;
    public final String packageName;
    public final String fullyQualifiedClassName;

    public ClassPathItem(String file, URL classLoaderUrl) {
        try {
            this.file = file;
            this.classLoaderUrl = classLoaderUrl;
            this.fullyQualifiedClassName = file.substring(classLoaderUrl.getFile().length(), file.length() - ".class".length()).replaceAll("/", ".");
            String[] splits = fullyQualifiedClassName.split("[.]");
            this.classSimpleName = splits[splits.length - 1];
            int endIndex = fullyQualifiedClassName.length() - this.classSimpleName.length() - 1;
            if(endIndex>0){
                this.packageName = fullyQualifiedClassName.substring(0, endIndex);
            } else {
                this.packageName = "";
            }
        } catch (Exception e) {
            throw new RuntimeException("Ups file: " + file + " URL:" + classLoaderUrl, e);
        }
    }

    @Override
    public String toString() {
        return "ClassPathItem{" +
                "file='" + file + '\'' +
                ", classLoaderUrl=" + classLoaderUrl +
                ", classSimpleName='" + classSimpleName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", fullyQualifiedClassName='" + fullyQualifiedClassName + '\'' +
                '}';
    }

}
