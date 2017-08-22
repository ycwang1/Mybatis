package com.how2java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.how2java.pojo.Order;

public interface OrderMapper {
	@Select("select * from how2java.order")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="orderItems",column="oid",javaType=List.class,many=@Many(select="com.how2java.OrderItemMapper.listByOrderId"))
	})
	public List<Order> list();
}
