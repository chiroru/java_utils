package jp.ddo.chiroru.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author chiroru
 */
public class NumberFormatUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void isRangeOverで文字列として空文字を指定() {
        String str = "";
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRangeOverで文字列としてブランク文字列を指定() {
        String str = "\\s";
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRangeOverで文字列として半角スペースを指定() {
        String str = "　";
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRangeOverで文字列としてnullを指定() {
        String str = null;
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRangeOverでタイプとしてnullを指定() {
        String str = "1";
        NumberFormatUtils.isRangeOver(null, str);
        assertThat(true, is(true));
    }

    @Test
    public void isRangeOverでbyte型で正常1() {
        String str = "0";
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test
    public void isRangeOverでbyte型で正常2() {
        String str = "127";
        NumberFormatUtils.isRangeOver(Byte.class, str);
        assertThat(true, is(true));
    }

    @Test(expected = NumberFormatException.class)
    public void isRangeOverでbyte型で異常() {
        String str = "128";
        NumberFormatUtils.isRangeOver(Byte.class, str);
    }
}
