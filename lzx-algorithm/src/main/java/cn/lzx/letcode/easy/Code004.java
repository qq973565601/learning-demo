package cn.lzx.letcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @since 2023/6/1
 */
public class Code004 {
    public static void main(String[] args) {

        int[] arr1 = new int[]{1,2,3};
        int[] arr2 = new int[]{4,5,6};
        int i1 = arr1.length;
        int i2  = arr2.length;
        int l = i1 + i2;
        int[] arr3 = new int[l];

        List list = new ArrayList();
        for (int i = 0; i < arr1.length; i++) {
            list.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            list.add(arr2[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            arr3[i] = (int) list.get(i);
        }
        System.out.println(arr3.toString());
    }
}
