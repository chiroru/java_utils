package jp.ddo.chiroru.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * {@link StringUtils}のユニットテストです.
 * </p>
 * 
 * @author chiroru_0130@yahoo.co.jp
 * @since 1.0.01
 */
@RunWith(Theories.class)
public class StringUtilsTest {

    private final static Logger L = LoggerFactory.getLogger(StringUtilsTest.class);
    
    @DataPoints
    public static String[] BLANK_STRING = {" ", "\t", "\n", "\r"};

    @Theory
    public void ブランク文字かどうか判定できる(String suspect)
            throws Exception {
        L.info(" SUSPECT : [" + suspect + "]");
        assertThat(StringUtils.isBlank(suspect), is(true));
    }
}
