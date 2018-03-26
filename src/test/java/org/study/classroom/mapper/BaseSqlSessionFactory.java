package org.study.classroom.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

public class BaseSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void generateSqlSession(){
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
             sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(reader);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public SqlSession getSqlSession(){
        if (sqlSessionFactory == null) {
            System.out.println("this is null");
           generateSqlSession();
        }
        return sqlSessionFactory.openSession();
    }
}
