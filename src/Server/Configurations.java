package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations m_file = null;
    private Properties prop;
    private InputStream in;
    private OutputStream out;

    private Configurations() throws IOException {
        prop = new Properties();
        in = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(in);
    }

    public static Configurations Instance() {
        try {
            if (m_file == null)
                m_file = new Configurations();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m_file;
    }

    public synchronized String getProperty(String property){
        if (m_file == null)
            return null;
        return prop.getProperty(property);
    }
    public void create()throws IOException{
        prop = new Properties();
        in = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(in);
        out = new FileOutputStream("resources\\config.properties");
    }
}
