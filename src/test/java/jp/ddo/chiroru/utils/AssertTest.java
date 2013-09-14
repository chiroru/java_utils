package jp.ddo.chiroru.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AssertTest {

    @RunWith(Theories.class)
    public static class 表明違反にならない {

        @DataPoints
        public static Object[] NOT_NULL_VALUES = {
            123, "test", "\n"
        };

        @Theory
        public void nullを許可しない(Object suspect) {
            System.out.println(suspect);
            Assert.isNotNull(suspect);
            assertTrue(true);
        }
    }

    public static class 表明違反 {
        // Theoryを利用
        @Test(expected = IllegalArgumentException.class)
        public void nullを許可しないことを表明()
                throws Exception {
            Assert.isNotBlank("test");
            Assert.isNotBlank(null);
        }
    }
}
