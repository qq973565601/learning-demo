package cn.lzx.test;

/**
 * @author lzx
 * @since 2023/8/13
 */
public class Test {
    public static void main(String[] args) {
        Object tenantSid = "356562";
        Long aLong = Long.valueOf((String) tenantSid);
        System.out.println(aLong);
    }
}
