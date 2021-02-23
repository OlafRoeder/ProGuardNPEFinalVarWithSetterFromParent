# ProGuard NPE when final variable is accessed from parent

SSCCE for a situation, where ProGuard introduces a NullPointerException into code that is correct without obfuscation.
Child.class has a final variable "someValue". Child extends AbstractsParent, the final variable is initialized in
Child's constructor, after calling super(); super() leads to AbstractParent which in its constructor calls the abstract
method "someMethod()" which is implemented in Child. In someMethod() the final variable someValue is used - with a
preceding nullcheck. Works without obfuscation. When obfuscating this code, ProGuard eliminates the nullcheck, leading
to a NullPointerException.

* prerequisites: JDK 8
* build with "gradlew proguard"

<code>
//command

java -jar SSCCE_ProGuard_NPE_final_variable_nullcheck-0.1-DEVELOPER.jar

// output (expected)

someValue is null

//command

java -jar SSCCE_ProGuard_NPE_final_variable_nullcheck-0.1-DEVELOPER-OBFUSCATED.jar

// output (not expected)

Exception in thread "main" java.lang.NullPointerException

at application.Child.a(Unknown Source)

at application.AbstractParent.<init>(Unknown Source)

at application.Child.<init>(Unknown Source)

at application.Main.main(Unknown Source)

</code>