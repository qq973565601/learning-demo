package cn.lzx.ssm.controller;

import cn.lzx.ssm.entity.Department;
import cn.lzx.ssm.service.DepartmentService;
import cn.lzx.ssm.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理与部门相关的请求
 *
 * @author lzx
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有部门信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDept() {
        List<Department> dept = departmentService.getDept();
        return Msg.success().add("depts", dept);
    }
}
