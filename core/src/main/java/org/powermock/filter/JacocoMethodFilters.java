package org.powermock.filter;

import java.lang.reflect.Method;

public class JacocoMethodFilters implements MockMethodFilters {
    @Override
    public boolean shouldMockMethod(Method method) {
        return !method.getName().equals("$jacocoInit");
    }
}
