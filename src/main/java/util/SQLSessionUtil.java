package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SQLSessionUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = SQLSessionUtil.class;

    public SQLSessionUtil() {
    }

    private static SqlSessionFactory initSQLSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            initSQLSessionFactory();
        }
        return sqlSessionFactory.openSession(true);
    }
}
