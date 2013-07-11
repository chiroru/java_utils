package jp.ddo.chiroru.utils;

import java.io.IOException;
import java.util.Properties;

public interface PropertyLoader {

    Properties load(String path) throws IOException;

    Properties load(String path, boolean noCached) throws IOException;
}
