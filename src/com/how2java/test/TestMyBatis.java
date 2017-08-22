package com.how2java.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestMyBatis {

	public static void main(String[] args) throws Exception {
		// 根据配置文件mybatis-config.xml得到sqlSessionFactory 。
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
		//然后再根据sqlSessionFactory 得到session
		SqlSession ss = sf.openSession();
		
		//最后通过session的selectList方法，调用sql语句listCategory。listCategory这个就是在配置文件Category.xml中那条sql语句设置的id。
		//执行完毕之后，得到一个Category集合，遍历即可看到数据。
//		List<Product> list = ss.selectList("listProduct");
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
//		List<Category> list1 = ss.selectList("listCategory");
//		for(Category p:list1){
//			System.out.println(p.getName());
//		}
		
		//新增
//		Product p = new Product();
//		p.setName("擦汗");
//		p.setPrice(33.3f);
//		ss.insert("addProduct", p);
//		listAll(ss);
		
		
		//删除
//		Product p = new Product();
//		p.setId(8);
//		ss.delete("deleteProduct", p);
//		listAll(ss);
		
//		//获取
//		Product p=ss.selectOne("findProduct", 3);
//		System.out.println(p.getName());
		
		//修改
//		Product p=ss.selectOne("findProduct",3);
//		p.setName("修改后产品名称");
//		ss.update("updateProduct", p);
		
//		//模糊查找
//		String name = "修改";
//		List<Product> list = ss.selectList("findProductByName", name);
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
//		
		
//		//多条件查找
//		Map<String ,Object> params = new HashMap<>();
//		params.put("id", 1);
//		params.put("name", "产品");
//		List<Product> list = ss.selectList("findProductByNameAndId", params);
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
		
//		//一对多关系
//		findCategory(ss);
		
//		//多对一关系
//        findProduct(ss);
//		ss.commit();
//		ss.close();
		listCategory(ss);
	}
	private static void listAll(SqlSession session){
		List<Product> list = session.selectList("listProduct");
		for(Product p:list){
			System.out.println(p.getName());
		}
	}
	//测试多对一关系
	private static void findProduct(SqlSession session){
		List<Product> list = session.selectList("findProductCategory");
		for(Product p:list){
			System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
			}
	}
	//测试一对多关系
	private static void findCategory(SqlSession session){
		List<Category> cs = session.selectList("findCategoryAndProduct");
      for (Category c : cs) {
          System.out.println(c);
          List<Product> ps = c.getProducts();
          for (Product p : ps) {
              System.out.println("\t"+p);
          }
      }
	}
	//mybatis 分页
	public static void listCategory(SqlSession session){
		Map<String,Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("count", 10);
		List<Category> lc =session.selectList("listCategorys",map);
		System.out.println("结果数目为："+lc.size());
		for(Category c:lc){
			System.out.println(c.getName());
		}
	}
}
