package org.powermock.core;

import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

public class FakeMethodProducer {

    public static Method getMethodWithName(String newMethodName) throws NoSuchMethodException {
        Method method = ClassForMethodToRename.class.getDeclaredMethod("methodToRename");
        Whitebox.setInternalState(method, "name", newMethodName);
        return method;
    }

}

class ClassForMethodToRename {
    public void methodToRename(){}
}