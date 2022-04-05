package pool;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.Properties;

public class MySqlDataSourceFactory {
    public static MysqlDataSource createMySqlDataSource() {
        MysqlDataSource dataSource = null;
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream("prop\\database.properties");
            props.load(in);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.pass"));
        } catch (IOException e) {

        }

        return dataSource;
    }
}
