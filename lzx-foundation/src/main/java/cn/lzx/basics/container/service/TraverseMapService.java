package cn.lzx.basics.container.service;

import cn.lzx.aentity.Grade;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author lzx
 * @since 2023/1/16
 */
public class TraverseMapService {

    /**
     * 根据keySet遍历
     *
     * @param gradeMap 需要遍历的map
     */
    public void traverseByKeySet(Map<String, Grade> gradeMap) {
        Set<String> stuNoSet = gradeMap.keySet();
        for (String stoNo : stuNoSet) {
            if (gradeMap.containsKey(stoNo)) {
                Grade grade = gradeMap.get(stoNo);
                doSomething(grade);
            }
        }
    }

    /**
     * 根据entrySet遍历
     *
     * @param gradeMap 需要遍历的map
     */
    public void traverseByEntrySet(Map<String, Grade> gradeMap) {
        Set<Map.Entry<String, Grade>> entrySet = gradeMap.entrySet();
        for (Map.Entry<String, Grade> gradeEntry : entrySet) {
            String key = gradeEntry.getKey();
            Grade value = gradeEntry.getValue();
            doSomething(value);
        }
    }

    /**
     * 使用iterator
     *
     * @param gradeMap 需要遍历的map
     */
    public void traverseByIterator(Map<String, Grade> gradeMap) {
        Iterator<Map.Entry<String, Grade>> iterator = gradeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Grade> temp = iterator.next();
            String key = temp.getKey();
            Grade value = temp.getValue();
            doSomething(value);
        }
    }

    /**
     * Java8以后使用map.forEach遍历
     *
     * @param gradeMap 需要遍历的map
     */
    public void traverseByLambda(Map<String, Grade> gradeMap) {
        gradeMap.forEach((key, value) -> doSomething(value));
    }

    private void doSomething(Grade grade) {
        System.out.println(grade);
    }
}
