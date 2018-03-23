package com.tang.myCloud.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tang.myCloud.bean.CloudUser;

public class MyTest {
	@org.junit.Test
	public void test1() {
		CloudUser user = new CloudUser();
		user.setUserId(100);
		Field fields[] = user.getClass().getDeclaredFields();

		for (Field field : fields) {
			String name = field.getName();
			if (name.equals("username")) {
				field.setAccessible(true);
				try {
					field.set(user, "tang");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object o;
				try {
					o = field.get(user);
					System.out.println(o.toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(name);

		}
	}

	@Test
	public void test2() throws Exception {
		CloudUser user = new CloudUser();
		user.setUsername("刘洋");
		user.setUserPassword("liuyang");
		DBUtils utils = DBUtils.newInstance();
		utils.setDataSource(new ComboPooledDataSource());
		utils.add(user);
	}

	@Test
	public void test3() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		context.close();

	}
	/**
	 * test make Dir named by digits
	 */
	@Test
	public void test4(){
		File file = new File("G:/"+"/"+18823);
		System.out.println(file.mkdir());
	}
	@Test
	public void test5(){
		BigDecimal g = new BigDecimal(666);
		Object ob = (Object)g;
		System.out.println(ob);
	}
	@Test
	public void test6() throws Exception {
		char a = 65;
		getObject(4);
		getObject(a);
		getObject(4f);
		double d = 4;
		getObject(d);
		Double d1 = 40.54;
		getObject(d1);
	}
	public static void main(String[] args) {
		char a = 65;
		getObject(4);
		getObject(a);
		getObject(4f);
		double d = 4;
		getObject(d);
		Double d1 = 40.54;
		getObject(d1);
	}
	public static void getObject(Object ojb){
		System.out.println(ojb.getClass());
	}
	@Test
	public void testPattern() throws Exception {
		Pattern pattern = Pattern.compile(".*do");
		String s = "xxx.do";
		System.out.print( pattern.matcher(s).matches());
	}
	@Test
	public void testSplit() throws Exception {
		String s = "tang";
		if( s.contains("?"))
		System.out.println(s.split("?")[0]);
	}
	@Test
	public void testFilePath() throws Exception {
		URL url=Thread.currentThread().getContextClassLoader().getResource("");
		String path = new File("").getCanonicalPath();
		path = path.replaceAll("\\\\", "/");
		System.out.println(new File(path).exists());
		System.out.println("file1-absolutePath: "+url.toString());
		System.out.println("file2-absolutePath: "+path);
	}
	@Test
	public void testParseLong() throws Exception {
		System.out.println(Long.parseLong(""));
	}
}
