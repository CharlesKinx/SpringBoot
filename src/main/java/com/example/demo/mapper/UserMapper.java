package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into USER (name,account_id,token,gmt_creat,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Integer creator);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set name = #{name},token =#{token},gmt_modified = #{gmtModified},avatar_url =#{avatarUrl} where id = #{id}")
    void update(User user);
}
