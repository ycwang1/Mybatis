package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;

public class TestCategoryMapper {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf =new SqlSessionFactoryBuilder().build(is);
		SqlSession  ss = ssf.openSession();
		CategoryMapper mapper = ss.getMapper(CategoryMapper.class);
		
//		listAll(mapper);
//		for(int i=0;i<100;i++){
//			add(mapper);
//		}
		getCount(mapper);
		ss.commit();
		ss.close();
		
	}
	private static void update(CategoryMapper mapper) {
        Category c= mapper.get(14);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }
  
    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(14);
        System.out.println(c.getName());
    }
  
    private static void delete(CategoryMapper mapper) {
        mapper.delete(13);
        listAll(mapper);
    }
  
    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
//        listAll(mapper);
    }
   
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
    private static void getCount(CategoryMapper mapper){
    	int count =mapper.getCount();
    	System.out.println("表category的数据数目："+count);
    }
}
