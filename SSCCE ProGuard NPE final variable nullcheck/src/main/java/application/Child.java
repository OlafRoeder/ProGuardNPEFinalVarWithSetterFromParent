package application;

public class Child extends AbstractParent {

    private final String someValue;

    Child() {
        super();
        someValue = "Hello";
    }

    @Override
    void someMethod() {

        /*ProGuard will eliminate this nullcheck, leading to a NPE in obfuscated code.*/
        if (someValue != null)
            System.out.println("someValue empty? " + someValue.isEmpty());
        else
            System.out.println("someValue is null");
    }
}
