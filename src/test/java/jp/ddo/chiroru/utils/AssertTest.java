package jp.ddo.chiroru.utils;

import org.junit.Test;

public class AssertTest {

    // Theoryを利用
    @Test(expected = IllegalArgumentException.class)
    public void nullを許可しないことを表明()
            throws Exception {
        Assert.isNotBlank("test");
        Assert.isNotBlank(null);
    }
}
