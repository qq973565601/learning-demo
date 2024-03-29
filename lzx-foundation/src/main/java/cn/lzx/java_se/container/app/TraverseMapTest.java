package cn.lzx.java_se.container.app;

import cn.lzx.java_se.container.entity.Grade;
import cn.lzx.java_se.container.service.TraverseMapService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * java不同遍历方式
 *
 * @author lzx
 * @since 2023/1/16
 */
public class TraverseMapTest {

    private static Map<String, Grade> gradeMap;

    static {
        gradeMap = new HashMap<>();
        Grade aliceGrade = new Grade("2022001", "alice", "101", 90.0, 89.0, 70.0);
        Grade bobGrade = new Grade("2022002", "bob", "101", 91.0, 87.0, 75.0);
        Grade michaleGrade = new Grade("2022002", "michale", "102", 59.0, 90.0, 83.0);
        Grade janeGrade = new Grade("2022002", "jane", "102", 56.0, 97.0, 83.0);
        Grade kangkangGrade = new Grade("2022002", "kangkang", "102", 93.0, 67.0, 65.0);
        gradeMap.put("2022001", aliceGrade);
        gradeMap.put("2022002", bobGrade);
        gradeMap.put("2022003", michaleGrade);
        gradeMap.put("2022004", janeGrade);
        gradeMap.put("2022005", kangkangGrade);
    }

    @Test
    public void testTraversByKeySet() {
        TraverseMapService traverseMapService = new TraverseMapService();
        traverseMapService.traverseByKeySet(gradeMap);
    }

    @Test
    public void testTraverseByEntrySet() {
        TraverseMapService traverseMapService = new TraverseMapService();
        traverseMapService.traverseByEntrySet(gradeMap);
    }

    @Test
    public void testTraverseByIterator() {
        TraverseMapService traverseMapService = new TraverseMapService();
        traverseMapService.traverseByIterator(gradeMap);
    }

    @Test
    public void testTraverseByLambda() {
        TraverseMapService traverseMapService = new TraverseMapService();
        traverseMapService.traverseByLambda(gradeMap);
    }
}
