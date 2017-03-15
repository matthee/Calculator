package at.sw2017.calculator;

import android.net.NetworkInfo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorUnitTest {
    @Test
    public void testValuesOfState() {
        assertEquals(Calculator.State.INIT, Calculator.State.valueOf("INIT"));
        assertEquals(Calculator.State.ADD, Calculator.State.valueOf("ADD"));
        assertEquals(Calculator.State.SUBTRACT, Calculator.State.valueOf("SUBTRACT"));
        assertEquals(Calculator.State.MULTIPLY, Calculator.State.valueOf("MULTIPLY"));
        assertEquals(Calculator.State.DIVIDE, Calculator.State.valueOf("DIVIDE"));
        assertEquals(Calculator.State.NUM, Calculator.State.valueOf("NUM"));
    }
}
