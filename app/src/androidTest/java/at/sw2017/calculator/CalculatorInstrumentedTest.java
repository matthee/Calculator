package at.sw2017.calculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorInstrumentedTest {
    @Rule
    public ActivityTestRule<Calculator> mActivityRule = new ActivityTestRule<>(Calculator.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017.calculator", appContext.getPackageName());
    }

    @Test
    public void testButtons() {
        for (int i = 1; i <= 9; i++) {
            onView(withId(getButtonIdFromNumber(i))).perform(click());
        }

        onView(withText("+")).perform(click());
        onView(withText("-")).perform(click());
        onView(withText("*")).perform(click());
        onView(withText("/")).perform(click());

        onView(withText("=")).perform(click());
        onView(withText("C")).perform(click());
    }

    @Test
    public void testNumberFieldDefault() {
        onView(withId(R.id.numberView)).check(matches(withText("0")));
    }

    @Test
    public void testViewIdResolving() {
        assertEquals(R.id.button0, getButtonIdFromNumber(0));
        assertEquals(R.id.button1, getButtonIdFromNumber(1));
        assertEquals(R.id.button2, getButtonIdFromNumber(2));
        assertEquals(R.id.button3, getButtonIdFromNumber(3));
        assertEquals(R.id.button4, getButtonIdFromNumber(4));
        assertEquals(R.id.button5, getButtonIdFromNumber(5));
        assertEquals(R.id.button6, getButtonIdFromNumber(6));
        assertEquals(R.id.button7, getButtonIdFromNumber(7));
        assertEquals(R.id.button8, getButtonIdFromNumber(8));
        assertEquals(R.id.button9, getButtonIdFromNumber(9));
    }

    @Test
    public void testInputField() {
        for (int i = 9; i >= 0; i--) {
            onView(withId(getButtonIdFromNumber(i))).perform(click());
        }

        onView(withText("9876543210")).check(matches(isDisplayed()));
    }

    @Test
    public void testClearButton() {
        onView(withId(getButtonIdFromNumber(3))).perform(click());
        onView(withText("C")).perform(click());

        onView(withId(R.id.numberView)).check(matches(withText("0")));
    }

    @Test
    public void testAdditionCalculations() {
        onView(withId(getButtonIdFromNumber(4))).perform(click());
        onView(withText("+")).perform(click());
        onView(withId(getButtonIdFromNumber(6))).perform(click());

        onView(withText("=")).perform(click());
        onView(withId(R.id.numberView)).check(matches(withText("10")));
    }

    @Test
    public void testSubtractionCalculations() {
        onView(withId(getButtonIdFromNumber(5))).perform(click());
        onView(withId(getButtonIdFromNumber(4))).perform(click());
        onView(withText("-")).perform(click());
        onView(withId(getButtonIdFromNumber(1))).perform(click());
        onView(withId(getButtonIdFromNumber(2))).perform(click());

        onView(withText("=")).perform(click());

        onView(withId(R.id.numberView)).check(matches(withText("42")));
    }


    @Test
    public void testMultiplicationCalculations() {
        onView(withId(getButtonIdFromNumber(7))).perform(click());
        onView(withText("*")).perform(click());
        onView(withId(getButtonIdFromNumber(5))).perform(click());

        onView(withText("=")).perform(click());
        onView(withId(R.id.numberView)).check(matches(withText("35")));
    }

    @Test
    public void testDivisionCalculations() {
        onView(withId(getButtonIdFromNumber(5))).perform(click());
        onView(withId(getButtonIdFromNumber(4))).perform(click());
        onView(withText("/")).perform(click());
        onView(withId(getButtonIdFromNumber(2))).perform(click());

        onView(withText("=")).perform(click());
        onView(withId(R.id.numberView)).check(matches(withText("27")));
    }

    @Test
    public void testPressingEqualsInitially() {
        onView(withText("=")).perform(click());

        onView(withId(R.id.numberView)).check(matches(withText("Error")));
    }

    @Test
    public void testAdditionAndDoubleEquals() {
        onView(withId(getButtonIdFromNumber(2))).perform(click());
        onView(withId(getButtonIdFromNumber(1))).perform(click());
        onView(withText("+")).perform(click());
        onView(withId(getButtonIdFromNumber(4))).perform(click());
        onView(withId(getButtonIdFromNumber(2))).perform(click());

        onView(withText("=")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.numberView)).check(matches(withText("63")));
    }

    @Test
    public void testPressingEqualsAfterEnteringOnlyOneNumber() {
        onView(withText("3")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.numberView)).check(matches(withText("Error")));
    }


    private int getButtonIdFromNumber(int number) {
        Context appContext = InstrumentationRegistry.getTargetContext();

        String buttonName = "button"+number;

        return appContext.getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());
    }
}
