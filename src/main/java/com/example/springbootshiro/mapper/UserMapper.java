package com.example.springbootshiro.mapper;

import com.example.springbootshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public User queryUserByName(@Param("name") String name);
}
