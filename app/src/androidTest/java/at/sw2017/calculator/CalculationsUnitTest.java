package at.sw2017.calculator;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculationsUnitTest {

    @Test()
    public void testPrivateConstructor() {
        final Constructor<?>[] constructors = Calculations.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

    @Test
    public void testDoAddition() {
        int result = Calculations.doAddition(1, 2);

        assertEquals(3, result);
    }

    @Test
    public void testDoSubtraction() {
        int result = Calculations.doSubtraction(3, 2);

        assertEquals(1, result);
    }

    @Test
    public void testDoMultiplication() {
        int result = Calculations.doMultiplication(3, 4);

        assertEquals(12, result);
    }

    @Test
    public void testDoDivision() {
        int result = Calculations.doDivision(12, 3);

        assertEquals(4, result);
    }

    @Test
    public void testDoDivisionByZero() {
        int result = Calculations.doDivision(11, 0);

        assertEquals(0, result);
    }

    @Test
    public void testDoDivisionPerformsIntegerDivision() {
        int result = Calculations.doDivision(11, 4);

        assertEquals(2, result);
    }
}
