package cn.lzx.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

/**
 * @author lzx
 * @since 2022/9/29
 */
public class LambdaTest01 {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaTest01.class);

    public static void main(String[] args) {
        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);
        System.out.println();
        LOGGER.info("======分割线======");
        printNum(value -> value % 2 == 0);
    }

    /**
     * 01
     *
     * @param operator 入参
     * @return 计算值
     */
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    /**
     * 02
     *
     * @param predicate 入参
     */
    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.print(i + "-");
            }
        }
    }
}
