package cn.lzx.ssm.service;

import cn.lzx.ssm.entity.Department;
import cn.lzx.ssm.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * @return
     */
    public List<Department> getDept() {

        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
