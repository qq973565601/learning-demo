package cn.lzx.service;

import cn.lzx.pojo.UserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @since 2023/8/7
 */
public interface UserService extends IService<UserVo> {

    void insertOne(UserVo userVo);

    void deleteOneById(Long id);

    void updateOneById(UserVo userVo);

    UserVo selectOneById(Long id);

    void deleteBatchByIds(List<Long> ids);

    List<UserVo> selectBatchByIds(List<Long> ids);

    void deleteOneByMap(Map<String, Object> map);

    List<UserVo> selectOneByMap(Map<String, Object> map);

    List<Map<String, Object>> selectMaps(Integer age);

    List<UserVo> selectAll();

    void deleteOneByWrapper(Long id);

    UserVo selectOneByWrapper(Long id);

    Integer selectCountByWrapper(Integer age);

    List<UserVo> subQueryByWrapper();

    void updateOneByWrapper(UserVo userVo);

    List<UserVo> conditionQueryByWrapper(String userName);

    List<UserVo> conditionLambdaQueryByWrapper(String userName);

    Page<UserVo> selectAllByPage(Long current, Long size);

    //void updateSetEnum(UserVo userVo);
}
