package cn.lzx.ssm.mapper;

import cn.lzx.ssm.entity.Employee;
import cn.lzx.ssm.entity.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lzx
 */
public interface EmployeeMapper {

    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    /**
     * @param example
     * @return
     * 新增联合查询，不局限表内容，携带部门名称
     */
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    /**
     *
     * @param empId
     * @return
     */
    Employee selectByPrimaryKeyWithDept(Integer empId);

    /**
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}