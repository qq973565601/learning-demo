package cn.lzx.mapper;

import cn.lzx.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author lzx
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectAllByUserId(Integer selectAllByUserId);
}




