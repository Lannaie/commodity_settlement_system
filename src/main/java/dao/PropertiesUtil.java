package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * created by Bonnie on 2022/2/7
 */
public class PropertiesUtil {
    private Properties properties;
    private InputStream inputStream;

    public Properties getProperties( String filename ) throws IOException {
        properties = new Properties();
        // 加载配置文件到输入流
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
        // 从输入流读取属性列表
        properties.load(inputStream);
        return properties;
    }
}
