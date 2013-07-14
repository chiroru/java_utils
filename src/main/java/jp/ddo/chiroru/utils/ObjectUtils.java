package jp.ddo.chiroru.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code Object}型のオブジェクトを操作するユーティリティクラスです.
 */
public class ObjectUtils {
    /** 出力時のインデントの幅. */
    private static final int INDENT_WIDTH = 2;
    /** セパレータ文字列. */
    private static final String LF = System.getProperty("line.separator");
    /** 文字列表現出力メソッド. */
    private static Method tostring;

    static {
        try {
            tostring = Object.class.getMethod("toString", new Class[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ダンプ対象のオブジェクト. */
    private Object target;

    /**  */
    private Map<EmbededKey, String> embeded;

    /**
     * 引数で与えたオブジェクト用にインスタンスを生成する.
     * @param o 文字列化対象のオブジェクト
     */
    public ObjectUtils(Object o) {
        target = o;
    }

    class EmbededKey {
        Class cls;
        Object obj;
        EmbededKey(Class c, Object o) {
            obj = o;
            cls = c;
        }
        public boolean equals(Object o) {
            if (o instanceof EmbededKey) {
                return ((EmbededKey)o).cls == cls && ((EmbededKey)o).obj == obj;
            }
            return false;
        }
        public int hashCode() {
            return cls.hashCode() ^ obj.hashCode();
        }
    }


    public String toString() {
        embeded = new HashMap<EmbededKey, String>();
        String s = toString(target, 0);
        embeded = null;
        return s;
    }

    String toString(Object o, int level) {
        if (o == null) {
            return "null";
        } else {
            return dumpValue(o.getClass(), o, level);
        }
    }
    
    String dumpValue(Class<?> c, Object o, int level) {
        
        if (o == null) {
            return "null";
        } else if (c.isPrimitive() || c == Object.class) {
            return o.toString();
        }
        StringBuffer buffer = new StringBuffer();
        if (CharSequence.class.isInstance(o)) {
            buffer.append('"').append(o).append('"');
        } else if (c.isArray()) {
            buffer.append("[ ");
            for (int i = 0, n = Array.getLength(o); i < n; i++) {
                buffer.append(toString(Array.get(o, i), level + 1));
                buffer.append(", ");
            }
            buffer.append(']');
        } else {
            EmbededKey ekey = new EmbededKey(c, o);
            if (embeded.containsKey(ekey)) {
                buffer.append('(').append(embeded.get(ekey)).append(')');
            } else {
                buffer.append(dumpObject(c, o, level));
            }
        }
        return new String(buffer);
    }
    
    String dumpObject(Class c, Object o, int level) {
        assert o != null && !c.isArray() && !c.isPrimitive() 
                && !CharSequence.class.isInstance(c);
        try {
            Method m = c.getMethod("toString", new Class[0]);
            if (!tostring.equals(m)) {
                return o.toString();
            }
        } catch (NoSuchMethodException e) { // never
            return o.toString();
        }
        embeded.put(new EmbededKey(c, o), o.toString());
        StringBuffer buffer = new StringBuffer();
        buffer.append(o.toString());
        buffer.append(dumpFields(c, o, level));
        c = c.getSuperclass();
        if (c != null && c != Object.class) {
            buffer.append(LF);
            buffer.append(dumpValue(c, o, level));
        }
        return new String(buffer);
    }
    
    String dumpFields(Class c, Object o, int level) {
        String indent = createIndent(level);
        StringBuffer buffer = new StringBuffer();
        Field[] f = c.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            buffer.append(LF);
            buffer.append(indent).append(f[i].getName()).append('=');
            if (!f[i].isAccessible()) {
                changeAccessible(f[i], true);
            }
            try {
                buffer.append(dumpValue(f[i].getType(), f[i].get(o), level + 1));
            } catch (IllegalAccessException e) {
                buffer.append("EACCESS");
            }
        }
        return new String(buffer);
    }

    void changeAccessible(Field f, boolean tobe) {
        try {
            f.setAccessible(tobe);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 指定されたオブジェクトの階層に対応するインデントを生成します.
     * @param level オブジェクトの階層
     * @return インデント
     */
    final String createIndent(final int level) {
        char[] c = new char[level * INDENT_WIDTH];
        Arrays.fill(c, ' ');
        return new String(c);
    }
}
