package jp.ddo.chiroru.snipet;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtils {

	public static void main(String[] args) throws Exception {
		DecimalFormat df = new DecimalFormat();
		df.setParseBigDecimal(true);
		Number n = df.parse("-123,456,789,123,456,789,123,456,789,123,456,789");
		System.out.println(n);
		System.out.println(n.getClass());
		System.out.println(((BigDecimal)n).toBigInteger());
		try {
		((BigDecimal)n).toBigIntegerExact();
		} catch (ArithmeticException e) {
			System.out.println("精度がおちました！");
		}
	}
}
