package cn.lcy.ontologyconstruction.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Configuration {

    private static final String PROPERTY_FILE_NAME = "ontology-construction.properties";

    public static void main(String args[]) throws IOException {
        Configuration.propertiesLoader(PROPERTY_FILE_NAME);
    }

    public static Properties propertiesLoader(String fileName) throws IOException {
        // 文件在class的根路径
        InputStream is = Configuration.class.getClassLoader().getResourceAsStream(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Properties props = new Properties();

        props.load(br);

        return props;
    }
}
