package cn.lzx.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.lzx.ssm.entity.Employee;
import cn.lzx.ssm.service.EmployeeService;
import cn.lzx.ssm.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 */
@Controller
public class EmployeeController {

    final static String split = "-";

    @Autowired
    EmployeeService employeeService;

    /**
     * index.js发送emps请求，处理器处理
     * 处理emps请求，由控制器跳转到list界面
     * 获取所有员工数据（分页查询）
     *
     * @return
     */
    @RequestMapping("/Emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {//model保存的数据会带给页面并放在请求域中

        //引入pageHelper分页插件,在查询之前只需调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Employee> ems = employeeService.getAll();

        //封装了详细的分页信息，包括有我们查出来的数据,传入连续显示的页数
        PageInfo pageInfo = new PageInfo(ems, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "/list";
    }

    /**
     * 1、查询员工列表，保存分页信息
     * 使用ajax
     * 导入jackson包，使ResponseBody正常使用
     * ResponseBody：将返回的对象转为json字符串
     *
     * @return 员工分页列表信息
     */
    @ResponseBody
    @RequestMapping("/emps")
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        //引入pageHelper分页插件,在查询之前只需调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Employee> ems = employeeService.getAll();

        //封装了详细的分页信息，包括有我们查出来的数据,传入连续显示的页数
        PageInfo pageInfo = new PageInfo(ems, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 2、保存员工数据
     */
    @ResponseBody
    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public Msg saveEmp(Employee employee) {
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 3、检查员工姓名是否可用
     * RequestParam：指明客服端请求参数
     *
     * @return code:状态码 100：可用；200：不可用
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(@RequestParam("empName") String empName) {
        boolean result = employeeService.checkUser(empName);
        if (result) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 4、根据id查询员工信息，用作编辑回显
     * id来源于请求域中的变量,指定id是从路径中取出的值
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    /**
     * 5、根据员工id更新数据到数据库
     *
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Msg updateEmp(Employee employee) {

        System.out.println("111:" + employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 6、根据id单个删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmpBy(@PathVariable("ids") String ids) {

        if (ids.contains(split)) {

            //批量删除
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split(split);
            // 组装id集合
            for (String str_id : str_ids) {
                del_ids.add(Integer.parseInt(str_id));
            }
            employeeService.deleteBatch(del_ids);
        } else {
            //单个删除
            int id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }
}
