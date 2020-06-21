package com.github.seanyinx.testbed.spring.infrastructure;

import com.github.seanyinx.testbed.spring.domain.UserEntity;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepo {

  @Select("select * from user_entity where id = #{id}")
  Optional<UserEntity> findById(@Param("id") long id);

  @Delete("delete from user_entity where id = #{id}")
  void deleteById(@Param("id") long id);

  @Insert("insert into user_entity(id, name) values(#{user.id}, #{user.name})")
  void save(UserEntity user);
}
