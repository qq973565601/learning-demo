package cn.lzx.postgre;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @since 2022/11/14
 */
public class PostgreTest {

    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/lzxtest";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, "postgres", "root");
            String sql = "select * from lzx_person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("connect success");
            List list = convertList(resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static List<Map<String, ?>> convertList(ResultSet rs) throws SQLException {

        List<Map<String, ?>>  list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map<String, Object> orderedRecords = new LinkedHashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                System.out.println(md.getColumnName(i) + "---" + rs.getObject(i));
                orderedRecords.put(md.getColumnName(i), rs instanceof BigInteger ? ((BigInteger) rs).longValue() : rs);
            }
            list.add(orderedRecords);
        }
        return list;
    }

}
