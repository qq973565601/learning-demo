package cn.lzx.database.sqlserver;

import com.alibaba.fastjson.JSONException;
import org.apache.poi.ss.formula.functions.T;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @since 2023/7/4
 */
public class SqlServerTest {

    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://192.168.8.114:1433;DatabaseName=HT_PS";
    String userName = "sa";
    String userPwd = "123456";

    public ResultSet connectSqlValue(String sql) {
        ResultSet resultSet = null;
        try {
            Class.forName(driverName);
            System.out.println("加载SQLServer驱动类成功!");
        } catch (ClassNotFoundException a) {
            System.out.println("加载SQLServer驱动失败!");
            a.printStackTrace();
        }
        Connection dbcon = null;
        try {
            dbcon = DriverManager.getConnection(dbURL, userName, userPwd);
            Statement connStatement = dbcon.createStatement();
            resultSet = connStatement.executeQuery(sql);
            System.out.println("数据库连接成功!");
        } catch (SQLException e) {
            System.out.println("数据库连接失败!");
            e.printStackTrace();
        }
        return resultSet;
    }

    public void connectSqlNoValue(String sql) {
        Connection dbcon = null;
        try {
            dbcon = DriverManager.getConnection(dbURL, userName, userPwd);
            Statement connStatement = dbcon.createStatement();
            connStatement.execute(sql);
            System.out.println("数据库连接成功!");
        } catch (SQLException e) {
            System.out.println("数据库连接失败!");
            e.printStackTrace();
        }
    }

    public List resultSetToJsonObject(ResultSet rs) throws SQLException, JSONException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List list = new ArrayList<T>();// 定义一个list，用来存放数据
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(metaData.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);//将数据添加到list中
        }
        return list;
    }
}
