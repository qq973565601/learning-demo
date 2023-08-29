package cn.lzx.data_convert.json_convert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 读取json文件
 *
 * @author lzx
 * @since 2023/3/13
 */
public class ReadJsonTest {

    @Test
    public void getJson() {
        String url = "src\\main\\resources\\json\\zhangsan.json";
        File json = new File(url);
        try {
            String jsonString = FileUtils.readFileToString(json, "UTF-8");
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
