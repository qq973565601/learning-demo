package cn.lzx.database.sqlserver;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 读取json文件
 *
 * @author lzx
 * @since 2023/3/13
 */
public class JsonUtils {

    public static void main(String[] args) {

        String url = "D:\\workspace\\GitLin\\learning-demo\\lzx-foundation\\src\\main\\resources\\json\\zhangsan.json";

        File json = new File(url);
        try {
            String jsonString = FileUtils.readFileToString(json, "UTF-8");
            System.out.println(jsonString);
            JSONObject toJson = JSONObject.parseObject(jsonString);
            System.out.println("===============");
            System.out.println(toJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
