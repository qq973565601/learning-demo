package cn.lzx.data_convert.json_lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * Lambda表达式应用
 *
 * @author lzx
 * @since 2022/9/29
 */
public class LambdaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaTest.class);

    public static void main(String[] args) {
        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);
        System.out.println();
        LOGGER.info("======分割线======");
        printNum(value -> value % 2 == 0);
        LOGGER.info("======分割线======");
        Integer result = typeColver(Integer::valueOf);
        System.out.println(result);
        LOGGER.info("======分割线======");
        foreachArr(System.out::println);
    }

    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.print(i + "-");
            }
        }
    }

    public static <R> R typeColver(Function<String, R> function) {
        String str = "1235";
        return function.apply(str);
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }
}
