package jp.ddo.chiroru.utils;

import java.util.regex.Pattern;

/**
 * @author chiroru
 * @seince 1.0.0.0
 */
public final class StringUtils {

    private static final Pattern BLANK_STRING_PATTERN = Pattern.compile("[\\s ]");
    public static boolean isBlank(String suspect) {
        if (suspect == null || suspect.length() == 0)
            return true;

        if (BLANK_STRING_PATTERN.matcher(suspect).find())
            return true;

        return false;
    }

    /**
     * 指定された文字列が{@code null}、または、空文字の場合に{@code true}を返却します。<br/>
     * @param suspect　検査対象の文字列
     * @return 指定された文字列が{@code null}、または、空文字の場合に{@code true}
     */
    public static boolean isEmpty(String suspect) {
        return suspect == null || suspect.length() == 0;
    }
}
