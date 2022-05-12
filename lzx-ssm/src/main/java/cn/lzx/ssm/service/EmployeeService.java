package cn.lzx.ssm.service;

import cn.lzx.ssm.entity.Employee;
import cn.lzx.ssm.entity.EmployeeExample;
import cn.lzx.ssm.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     *
     * @return
     */
    public List<Employee> getAll() {

        List<Employee> employeeList = employeeMapper.selectByExampleWithDept(null);
        return employeeList;
    }

    /**
     * 保存员工信息
     *
     * @param employee
     * @return true:当前姓名可用；false：当前姓名不可用。
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检查员工姓名是否可用,请求域传入的值与数据库查出的值是否相等
     *
     * @param empName
     * @return count 状态 true or false
     */
    public boolean checkUser(String empName) {
        //创建一个条件
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //拼装条件,使员工名字必须等于这个值
        criteria.andEmpNameEqualTo(empName);
        //根据创建条件，查出符合条件的记录数
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 查询员工信息，在编辑时回显
     *
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 修改员工信息后，保存
     *
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据主键进行员工删除
     *
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键id批量删除
     *
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        //创建删除条件
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id(1,2,3...)
        criteria.andEmpIdIn(ids);

        //根据条件删除
        employeeMapper.deleteByExample(example);
    }
}
