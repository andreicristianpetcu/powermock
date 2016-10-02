package org.powermock.filter;

import java.lang.reflect.Method;

public interface MockMethodFilters {
    boolean shouldMockMethod(Method method);
}
