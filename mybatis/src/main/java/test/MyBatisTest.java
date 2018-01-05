package test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import bean.Employee;
import bean.EmployeeMapper;

public class MyBatisTest {
	private static SqlSession session;
	static{
		String resource="mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(is);
			session=ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void close(){
		if(session!=null)
			session.close();
	}
	@Test
	public void test1() throws IOException{
		Employee emp=session.selectOne("bean.EmployeeMapper.getEmpById", 1);
		System.out.println(emp);
		this.close();
	}
	@Test
	public void test2(){
		EmployeeMapper empMapper=session.getMapper(EmployeeMapper.class);
		Employee emp=empMapper.getEmpById(1);
		System.out.println(emp);
		this.close();
	}
}
