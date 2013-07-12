package jp.ddo.chiroru.utils;

import java.util.regex.Pattern;

/**
 * <p>
 * {@link String}型の値を処理するユーティリティです.
 * </p>
 * @author chiroru
 * @since 1.0.0.0
 */
public final class StringUtils {

    /** インスタンスを抑止します. */
    private StringUtils() { }

    /** 空文字を表見する正規表現です. */
    private static final Pattern BLANK_STRING_PATTERN =
            Pattern.compile("[\\s ]");

    /**
     * <p>
     * 指定された文字列が空文字の場合に{@code true}を返却します.
     * </p>
     * 
     * @param suspect 検査対象の文字列
     * @return 指定された文字列が空文字の場合に{@code true}
     */
    public static boolean isBlank(final String suspect) {
        if (suspect == null || suspect.length() == 0) {
            return true;
        }

        if (BLANK_STRING_PATTERN.matcher(suspect).find()) {
            return true;
        }

        return false;
    }

    /**
     * <p>
     * 指定された文字列が{@code null}、または、空文字の場合に{@code true}を返却します.
     * </p>
     * 
     * @param suspect　検査対象の文字列
     * @return 指定された文字列が{@code null}、または、空文字の場合に{@code true}
     */
    public static boolean isEmpty(final String suspect) {
        return suspect == null || suspect.length() == 0;
    }
}
