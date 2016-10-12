package org.powermock.filter;

import java.lang.reflect.Method;

public class JacocoMethodsFilter implements MockMethodsFilter {

    @Override
    public boolean shouldSkipMockingMethod(Method method) {
        return method.getName().equals("$jacocoInit");
    }
}
