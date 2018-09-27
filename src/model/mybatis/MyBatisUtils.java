package model.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
    
    public static String RESOURCE = "modelo/mybatis/mybatis-config.xml";    
    public static String ENVIRONMENT = "development";
    public static SqlSession getSession() throws IOException{
        SqlSession session = null;
        Reader reader = Resources.getResourceAsReader(RESOURCE);
        SqlSessionFactory sqlMapper =
                new SqlSessionFactoryBuilder().build(reader,ENVIRONMENT);
        session = sqlMapper.openSession();
        return session;
    }
    
}
