package org.powermock.core;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;

public class MockRepositoryTest {

    @Test
    public void givenJacocoInit_whenShouldStubMethod_thenReturnFalse() throws NoSuchMethodException {
        Method method = FakeJacocoModifiedClass.class.getDeclaredMethod("jacocoInit");
        Whitebox.setInternalState(method, "name", "$jacocoInit");

        boolean shouldStubMethod = MockRepository.shouldStubMethod(method);

        assertFalse(shouldStubMethod);
    }

}

class FakeJacocoModifiedClass {
    public void jacocoInit(){}
}