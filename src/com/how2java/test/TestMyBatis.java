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
		// ���������ļ�mybatis-config.xml�õ�sqlSessionFactory ��
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
		//Ȼ���ٸ���sqlSessionFactory �õ�session
		SqlSession ss = sf.openSession();
		
		//���ͨ��session��selectList����������sql���listCategory��listCategory��������������ļ�Category.xml������sql������õ�id��
		//ִ�����֮�󣬵õ�һ��Category���ϣ��������ɿ������ݡ�
//		List<Product> list = ss.selectList("listProduct");
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
//		List<Category> list1 = ss.selectList("listCategory");
//		for(Category p:list1){
//			System.out.println(p.getName());
//		}
		
		//����
//		Product p = new Product();
//		p.setName("����");
//		p.setPrice(33.3f);
//		ss.insert("addProduct", p);
//		listAll(ss);
		
		
		//ɾ��
//		Product p = new Product();
//		p.setId(8);
//		ss.delete("deleteProduct", p);
//		listAll(ss);
		
//		//��ȡ
//		Product p=ss.selectOne("findProduct", 3);
//		System.out.println(p.getName());
		
		//�޸�
//		Product p=ss.selectOne("findProduct",3);
//		p.setName("�޸ĺ��Ʒ����");
//		ss.update("updateProduct", p);
		
//		//ģ������
//		String name = "�޸�";
//		List<Product> list = ss.selectList("findProductByName", name);
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
//		
		
//		//����������
//		Map<String ,Object> params = new HashMap<>();
//		params.put("id", 1);
//		params.put("name", "��Ʒ");
//		List<Product> list = ss.selectList("findProductByNameAndId", params);
//		for(Product p:list){
//			System.out.println(p.getName());
//		}
		
//		//һ�Զ��ϵ
//		findCategory(ss);
		
//		//���һ��ϵ
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
	//���Զ��һ��ϵ
	private static void findProduct(SqlSession session){
		List<Product> list = session.selectList("findProductCategory");
		for(Product p:list){
			System.out.println(p+" ��Ӧ�ķ����� \t "+ p.getCategory());
			}
	}
	//����һ�Զ��ϵ
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
	//mybatis ��ҳ
	public static void listCategory(SqlSession session){
		Map<String,Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("count", 10);
		List<Category> lc =session.selectList("listCategorys",map);
		System.out.println("�����ĿΪ��"+lc.size());
		for(Category c:lc){
			System.out.println(c.getName());
		}
	}
}
