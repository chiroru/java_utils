package jp.ddo.chiroru.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 数値のフォーマット変換を行うユーティリティです.<br/>
 * @author chiroru
 * @since 1.0.0.1
 */
public final class NumberFormatUtils {

    /**
     * 指定された数値の文字列を指定した数値型に変更した際にサイズがオーバーするかどうかチェックします。
     * サイズがオーバーした場合、{@code NumberFormatException}をスローします。<br/>
     * @param type 数値型({@code Number}のサブクラス)
     * @param s 数値の文字列<br/>
     * @throws IllegalArgumentException 引数{@code type}に{@code null}を指定した場合、または、引数{@code s}に{@code null}や{@code 空文字}、ブランク文字列を指定した場合、{@code NumberFormatException}をスローされます。
     */
    public static void isRangeOver(Class<? extends Number> type, String s) {
        Assert.isNotBlank(s);
        Assert.isNotNull(type);

        if (type.equals(Byte.class)) {
            Byte.parseByte(s);
        } else if (type.equals(Short.class)) {
            Short.parseShort(s);
        } else if (type.equals(Integer.class)) {
            Integer.parseInt(s);
        } else if (type.equals(Long.class)) {
            Long.parseLong(s);
        } else if (type.equals(Float.class)) {
            Float.parseFloat(s);
        } else if (type.equals(Double.class)) {
            Double.parseDouble(s);
        } else if (type.equals(BigInteger.class) || type.equals(BigDecimal.class) || type.equals(Number.class)) {
            return;
        } else {
            throw new IllegalArgumentException("argument type, specify a subclass of Number.");
        }
    }
}
