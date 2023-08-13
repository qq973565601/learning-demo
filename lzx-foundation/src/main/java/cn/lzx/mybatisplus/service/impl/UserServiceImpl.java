package cn.lzx.mybatisplus.service.impl;

import cn.lzx.mybatisplus.enums.SexEnum;
import cn.lzx.mybatisplus.mapper.UserMapper;
import cn.lzx.mybatisplus.pojo.UserVo;
import cn.lzx.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @since 2023/8/7
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserVo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    //==============================基本语句==============================

    @Override
    public void insertOne(UserVo userVo) {
        userMapper.insert(userVo);
    }

    @Override
    public void deleteOneById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateOneById(UserVo userVo) {
        userMapper.updateById(userVo);
    }

    @Override
    public UserVo selectOneById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteBatchByIds(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
    }

    @Override
    public List<UserVo> selectBatchByIds(List<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public void deleteOneByMap(Map<String, Object> map) {
        userMapper.deleteByMap(map);
    }

    @Override
    public List<UserVo> selectOneByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Integer age) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.eq("age", age);
        return userMapper.selectMaps(wrapper);
    }

    @Override
    public List<UserVo> selectAll() {
        return userMapper.selectList(null);
    }

    @Override
    public void deleteOneByWrapper(Long id) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        userMapper.delete(wrapper);
    }

    @Override
    public UserVo selectOneByWrapper(Long id) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Integer selectCountByWrapper(Integer age) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.eq("age", age);
        return userMapper.selectCount(wrapper);
    }

    //==============================复杂语句==============================

    @Override
    public List<UserVo> subQueryByWrapper() {
        //子查询
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.inSql("user_name", "select id,user_name from mp_user where id <=3");
        return userMapper.selectList(wrapper);
    }

    @Override
    public void updateOneByWrapper(UserVo userVo) {
        // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户更新
        // lambda中的条件有限执行
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        userMapper.update(userVo, wrapper);
    }

    @Override
    public List<UserVo> conditionQueryByWrapper(String userName) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(userName), "user_name", userName)
                .ge("age", 18).le("age", 30);
        return userMapper.selectList(wrapper);
    }

    @Override
    public List<UserVo> conditionLambdaQueryByWrapper(String userName) {
        LambdaQueryWrapper<UserVo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(userName), UserVo::getName, userName)
                .ge(UserVo::getAge, 20).le(UserVo::getAge, 30);
        return userMapper.selectList(wrapper);
    }

    @Override
    public Page<UserVo> selectAllByPage(Long current, Long size) {
        Page<UserVo> page = new Page<>(current, size);
        return userMapper.selectPage(page, null);
    }

    @Override
    public void updateSetEnum(UserVo userVo) {
        userVo.setSex(SexEnum.MALE);
        userMapper.insert(userVo);
    }
}
