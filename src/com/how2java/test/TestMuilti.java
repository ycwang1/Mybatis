package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;
import com.sun.accessibility.internal.resources.accessibility;

public class TestMuilti {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		add();
//		delete();
//		find();
//		select();
//		update();
//		select();
		findOrder();
	}
	//查询多对多关系
	public static void find() throws Exception{
		String resource ="myBatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();
		
		List<Order> lo = ss.selectList("findOrderProduct");
		for(Order order:lo){
			System.out.println(order.getCode());
			List<OrderItem> loi = order.getOrderItems();
			for(OrderItem oi:loi){
				System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),
						oi.getProduct().getPrice(),oi.getNumber());
			}
		}
		
		ss.commit();
		ss.close();
		
	}
	//新增orderitem 表一条数据
	public static void add() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(is);
		SqlSession ss=ssf.openSession();
		Order o = ss.selectOne("findOrderProductById", 1);
		Product p = ss.selectOne("findProduct",1);
		OrderItem oi = new OrderItem();
		oi.setOrder(o);
		oi.setProduct(p);
		oi.setNumber(300);
		ss.insert("addOrderItem", oi);
		ss.commit();
		ss.clearCache();
	}
	//删除orderitem表一条数据
	public static void delete() throws Exception{
		String resource="mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();
		Order o = ss.selectOne("findOrderProductById",1);
		Product p = ss.selectOne("findProduct",1);
		OrderItem oi = new OrderItem();
		oi.setOrder(o);
		oi.setProduct(p);
		ss.delete("deleteOrderItem",oi);
		ss.commit();
		ss.close();
	}
	//测试if,where标签
	public static void select() throws Exception{
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();
		Product p =new Product();
		p.setName("产品");
		p.setPrice(88.83f);
		List<Product> list = ss.selectList("listProduct", p);
		for(Product p1 :list){
			System.out.println(p1.getName());
		}
		
		ss.commit();
		ss.close();
	}
	//测试if,where标签   更新
	public static void update() throws Exception{
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession ss = ssf.openSession();
		Product p =new Product();
		p.setName("少时诵诗书");
		p.setPrice(66f);
		p.setId(1);
		ss.update("updateProduct", p);
		
		ss.commit();
		ss.close();
	}
	//测试foreach标签  
		public static void findOrder() throws Exception{
			String resource = "mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
			SqlSession ss = ssf.openSession();
			List<Integer> ids = new ArrayList<>();
			ids.add(1);
			ids.add(2);
			List<Order> list = ss.selectList("findOrder",ids);
			for(Order o :list){
				System.out.println(o.getCode());
			}
			ss.commit();
			ss.close();
		}
}
