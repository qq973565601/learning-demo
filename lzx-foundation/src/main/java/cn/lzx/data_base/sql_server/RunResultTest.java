package cn.lzx.data_base.sql_server;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @since 2023/7/4
 */
public class RunResultTest {

    @Test
    public void getResult() throws SQLException {

        long star = System.currentTimeMillis();
        Sql sql = new Sql();
        ConnectSqlServer connectSQLServer = new ConnectSqlServer();

        long t1 = System.currentTimeMillis();
        connectSQLServer.connectSqlNoValue(sql.getH01_01());
        connectSQLServer.connectSqlNoValue(sql.getH01_02());
        ResultSet resultSet1 = connectSQLServer.connectSqlValue(sql.getH01_03());
        System.out.println("第一个时间:" + (System.currentTimeMillis() - t1));

        long t2 = System.currentTimeMillis();
        connectSQLServer.connectSqlNoValue(sql.getH02_01());
        connectSQLServer.connectSqlNoValue(sql.getH02_02());
        ResultSet resultSet2 = connectSQLServer.connectSqlValue(sql.getH02_03());
        System.out.println("第二个时间:" + (System.currentTimeMillis() - t2));

        long t3 = System.currentTimeMillis();
        connectSQLServer.connectSqlNoValue(sql.getH03_01());
        connectSQLServer.connectSqlNoValue(sql.getH03_02());
        ResultSet resultSet3 = connectSQLServer.connectSqlValue(sql.getH03_03());
        System.out.println("第三个时间:" + (System.currentTimeMillis() - t3));

        long t4 = System.currentTimeMillis();
        ResultSet resultSet4 = connectSQLServer.connectSqlValue(sql.getH04_01());
        System.out.println("第4个时间:" + (System.currentTimeMillis() - t4));

        long t5 = System.currentTimeMillis();
        ResultSet resultSet5 = connectSQLServer.connectSqlValue(sql.getH05_01());
        System.out.println("第5个时间:" + (System.currentTimeMillis() - t5));

        long t6 = System.currentTimeMillis();
        ResultSet resultSet6 = connectSQLServer.connectSqlValue(sql.getH06_01());
        System.out.println("第6个时间:" + (System.currentTimeMillis() - t6));

        System.out.println("总时间为:" + (System.currentTimeMillis() - star));
        List list1 = connectSQLServer.resultSetToJsonObject(resultSet1);
        List list2 = connectSQLServer.resultSetToJsonObject(resultSet2);
        List list3 = connectSQLServer.resultSetToJsonObject(resultSet3);
        List list4 = connectSQLServer.resultSetToJsonObject(resultSet4);
        List list5 = connectSQLServer.resultSetToJsonObject(resultSet5);
        List list6 = connectSQLServer.resultSetToJsonObject(resultSet6);

        List result = new ArrayList();
        result.add(list1);
        result.add(list2);
        result.add(list3);
        result.add(list4);
        result.add(list5);
        result.add(list6);

        long jsonTime = System.currentTimeMillis();
        JSON.toJSONString(result);
        System.out.println("序列化时间为：" + (System.currentTimeMillis() - jsonTime));
    }
}
