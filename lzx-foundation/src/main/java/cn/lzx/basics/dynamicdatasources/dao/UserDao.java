package cn.lzx.basics.dynamicdatasources.dao;

import cn.lzx.basics.dynamicdatasources.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserDao {
    User getById(Integer id);
}
