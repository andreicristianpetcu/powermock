package org.powermock.filter;

import java.lang.reflect.Method;

public interface MockMethodsFilter {
    boolean shouldSkipMockingMethod(Method method);
}
