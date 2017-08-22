package com.how2java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.how2java.pojo.Category;
import com.how2java.provider.CategoryDynaSqlProvider;

public interface CategoryMapper {
	@Select("select * from category where id=#{id}")
	public Category get(int id);
	@Insert("insert into category (name) values (#{name})")
	public void add(Category c);
	
	@Select("select * from category" )
	@Results({
		@Result(column="id",property="id"),
		@Result(property="products",javaType=List.class,column="id",many=@Many(select="com.how2java.mapper.ProductMapper.listByCategory"))
	})
	public List<Category> getAll();
	
	@Update("update category set name=#{name} where id=#{id}")
	public void updateCategory(Category c);
	@Delete("delete from category where id=#{id}")
	public void deleteCategory(int id);
	
	
	@InsertProvider(method="add",type=CategoryDynaSqlProvider.class)
	public int addCategory(Category category);
	
	@DeleteProvider(type=CategoryDynaSqlProvider.class,method="delete")
    public void delete(int id);  
        
    @SelectProvider(type=CategoryDynaSqlProvider.class,method="get")  
    public Category getCategory(int id);  
      
    @UpdateProvider(type=CategoryDynaSqlProvider.class,method="update")  
    public int update(Category category);   
        
    @SelectProvider(type=CategoryDynaSqlProvider.class,method="list")      
    public List<Category> list(); 
    @Select("select * from category limit #{start},#{count}")
    public List<Category> listCategory(@Param("start")int start,@Param("count")int count);
    @Select("select count(*) from category")
    public int getCount();
}
