package JUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Sarah Fromming on 27/09/2017.
 */
public class JUnitTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JUnitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
