package cn.lzx.ssm.test;

import cn.lzx.ssm.entity.Department;
import cn.lzx.ssm.entity.Employee;
import cn.lzx.ssm.mapper.DepartmentMapper;
import cn.lzx.ssm.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author lzx
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    /**
     * 测试department
     */
    @Test
    public void crudTest() {
        System.out.println(departmentMapper);
        departmentMapper.insertSelective(new Department(null, "测试部"));
    }

    @Test
    public void crudTest2() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 5; i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uuid, "M", uuid + "@gmail.com", 1));
        }
        System.out.println("success!!");

    }


}
