package com.how2java.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.mapper.CategoryMapper;
import com.how2java.mapper.OrderMapper;
import com.how2java.mapper.ProductMapper;
import com.how2java.pojo.Category;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;
import com.sun.media.sound.SoftSynthesizer;

public class TestMapper {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();
		CategoryMapper mapper = ss.getMapper(CategoryMapper.class);
//		insert(mapper);
//		get(mapper);
//		update(mapper);
//		listAll(mapper);
//		delete(mapper);
//		select(mapper);
//		ProductMapper pm = ss.getMapper(ProductMapper.class);
//		list(pm);
		listCategory(ss);
		ss.commit();
		ss.close();
	}
	//ע�ⷽʽ��ȡ����
	public static void get(CategoryMapper mapper){
		Category c = mapper.get(1);
		System.out.println(c.getName());
	}
	//ע�ⷽʽ����һ������
	public static void insert(CategoryMapper mapper){
		Category c = new Category();
		c.setName("�����µ�");
		mapper.add(c);
	}
	//ע�ⷽʽ������������
	public static void select(CategoryMapper mapper){
		
		List<Category> list = mapper.getAll();
		for(Category c:list){
			System.out.println(c.getName());
		}
	}
	//ע�ⷽʽ�޸�һ������
	public static void update(CategoryMapper mapper){
		
		Category c =new Category();
		c.setId(3);
		c.setName("�����޸ĵ�");
		mapper.update(c);
	}
	//ע�ⷽʽɾ��һ������
	public static void delete(CategoryMapper mapper){
		
		mapper.delete(3);
	}
	//ע�ⷽʽһ�Զ�
	public static void listAll(CategoryMapper mapper){
		List<Category> lc = mapper.getAll();
		for(Category c:lc){
			System.out.println(c.getName());
			List<Product> products=c.getProducts();
			for(Product p: products){
				System.out.println(p.getName());
			}
		}
	}
	//ע�ⷽʽ���һ
	public static void list(ProductMapper mapper){
		List<Product> list = mapper.list();
		for(Product p:list){
			 System.out.println(p + "\t��Ӧ�ķ�����:\t" + p.getCategory().getName());

		}
	}
	//ע�ⷽʽ��Զ�
	public static void listOrder(SqlSession session){
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		List<Order> lo = mapper.list();
		for(Order o:lo){
			System.out.println(o.getCode());
			List<OrderItem> loi = o.getOrderItems();
			if(loi!=null){
				for(OrderItem oi:loi){
                    System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
				}
			}
		}
	}
	//ע�ⷽʽ��ҳ
	public static void listCategory(SqlSession session){
		CategoryMapper mapper =  session.getMapper(CategoryMapper.class);
		List<Category> lc = mapper.listCategory(0, 5);
		System.out.println("��ѯ����ĿΪ��"+lc.size());
		for(Category c:lc){
			System.out.println(c.getName());
		}
	}
}
