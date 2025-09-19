package com.hongbin.minigame001.mapper;

import com.hongbin.minigame001.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User u);
    User findByUsername(String username);
}
