package com.foo.base.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	User save(User t);

	int delete(List<Long> ids);

	int update(List<User> list);

	@Select(" select c.customer_username as name "
			+ " from np_customer c "
			+ "where c.customer_username "
			+ " like #{request.name,jdbcType=VARCHAR} ")
	List<User> list(@Param("request") UserRequest request);
}
