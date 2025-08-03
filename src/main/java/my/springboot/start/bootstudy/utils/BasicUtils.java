package my.springboot.start.bootstudy.utils;

import java.util.Set;
import java.util.TreeSet;

public class BasicUtils {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>((a, b) -> b - a); // 降序排列
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        System.out.print(treeSet); // [3, 2, 1]
        System.out.println(9%3);
    }

}
