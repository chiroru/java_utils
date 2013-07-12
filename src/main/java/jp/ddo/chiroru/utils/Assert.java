package jp.ddo.chiroru.utils;

/**
 * <p>
 * 引数の仕様表明を行うためのユーティリティクラスです。<br/>
 * 本クラスを利用して表明されたAPIの仕様は、利用時に満たさなければいけない条件です。
 * APIを利用するプログラマーは、表明された仕様を満たす引数を指定する必要があります.
 * </p>
 * 
 * @author chiroru
 * @since 1.0.0.0
 * @see StringUtils
 */
public final class Assert {

    /** インスタンス化を抑止. */
    private Assert() { };

    /**
     * 
     * @param suspect
     */
    public static void isNotBlank(String suspect) {
        isNotBlank(suspect, "specify a non blank string.");
    }

    public static void isNotBlank(String suspect, String message) {
        if (StringUtils.isBlank(suspect))
            throw new IllegalArgumentException("specify a non blank string.");
    }

    public static void isNotNull(Object suspect) {
        if (suspect == null)
            throw new IllegalArgumentException("specify a non null value.");
    }
}
