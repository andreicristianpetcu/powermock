package org.powermock.core;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.powermock.core.FakeMethodProducer.getMethodWithName;

public class MockRepositoryTest {

    @Test
    public void givenJacocoInit_whenShouldStubMethod_thenReturnFalse() throws NoSuchMethodException {
        Method method = getMethodWithName("$jacocoInit");

        boolean shouldStubMethod = MockRepository.shouldStubMethod(method);

        assertFalse(shouldStubMethod);
    }

}
