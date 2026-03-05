package cn.d4wn;

import cn.d4wn.mappers.JobMapper;
import cn.d4wn.pojo.Job;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建 session 对象
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();
        // 执行SQL语句
        JobMapper mapper = session.getMapper(JobMapper.class);
        Job res = mapper.selectByID("AC_ACCOUNT");
        System.out.println(res);
        // 关闭Session
        session.close();
    }

    @Test
    public void selectAll() throws IOException {
        // 创建 session 对象
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();
        // 执行SQL语句
        JobMapper mapper = session.getMapper(JobMapper.class);
        List<Job> res = mapper.selectAll();
        System.out.println(res);
        // 关闭Session
        session.close();
    }
}