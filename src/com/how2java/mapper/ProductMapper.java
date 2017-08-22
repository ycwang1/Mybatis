package com.how2java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.how2java.pojo.Product;

public interface ProductMapper {
	@Select(" select * from product where cid = #{cid}")
    public List<Product> listByCategory(int cid);
	@Select("select * from product")
	@Results({
		@Result(property="category",column="cid",one=@One(select="com.how2java.mapper.CategoryMapper.get"))
	})
	public List<Product> list();
}
