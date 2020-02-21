package cn.lcy.ontologyconstruction.config;

import cn.lcy.ontologyconstruction.util.Configuration;

import java.io.IOException;
import java.util.Properties;

public class Config {

    /**
     * 配置文件名
     */
    public static final String PROPERTY_FILE = "ontology-construction.properties";

    /**
     * 配置
     */
    public static Properties properties;

    /**
     * 本体标识
     */
    public static String pizzaNs;

    /**
     * 根路径
     */
    public static String rootPath;

    /**
     * 本体文件路径
     */
    public static String ontologyPath;

    /**
     * 实体词典路径
     */
    public static String individualDictPath;

    /**
     * 图片保存地址
     */
    public static String picSavePath;

    /**
     * 要抓取的页面数量
     */
    public static Long pageNum;

    /**
     * 读取配置
     */
    static {
        try {
            properties = Configuration.propertiesLoader(PROPERTY_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pizzaNs = properties.getProperty("pizzaNs").toString();

        rootPath = properties.getProperty("rootPath").toString();
        ontologyPath = rootPath + properties.get("ontologyPath").toString();
        individualDictPath = rootPath + properties.get("individualDictPath").toString();
        picSavePath = rootPath + properties.getProperty("picSavePath");

        pageNum = Long.parseLong(properties.getProperty("pageNum"));
    }

    /**
     * 配置类 不需要生成实例
     */
    private Config() {
    }
}
