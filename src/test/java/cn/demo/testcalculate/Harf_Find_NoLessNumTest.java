package cn.demo.testcalculate;

import cn.demo.algorithm.HalfSelectTest;
import cn.demo.algorithm.InsertSortTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-14
 * @Description:二分查找练习，找到>=num最左的位置
 */
@SpringBootTest
public class Harf_Find_NoLessNumTest {
    /**
     * 二分查找测试
     */
    @Test
    void testNoLessNum() {
        //int[] arr = {1000};
        int[] arr = CheckToolTest.randomArray(10, 1000);
        int num = 100;
        //指定为有序数组
        InsertSortTest.insertSort(arr);
        InsertSortTest.print(arr);
        int b = mostLeftPosition(arr, num);
        System.out.println(b);
    }

    //查找>=num最左的位置
    int mostLeftPosition(int[] arr, int num) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int pos = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= num) {
                pos = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return pos;
    }


    //对数器 todo 这里可以写一个对数器模版方法，实现抽象方法去调用
    @Test
    void checkDataTool() {
        int testTimes = 10000;
        int maxSize = 10;
        int maxValue = 1000;
        boolean succeed = true;
        int num;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = CheckToolTest.randomArray(maxSize, maxValue);
            num = arr[arr.length - 1];
            InsertSortTest.insertSort(arr);
            boolean a = HalfSelectTest.findCheck(arr, num);
            int b = mostLeftPosition(arr, num);
            if (a && b != -1) {
                System.out.println("num=" + num + ",a=" + a + ",b=" + b);
                CheckToolTest.print(arr);
                System.out.println("=======================");
            }
            if ((a && b == -1) || (!a && b != -1)) {
                System.out.println("num=" + num + ",a=" + a + ",b=" + b);
                succeed = false;
                CheckToolTest.print(arr);
                System.out.println("要查找的元素：" + num);
                break;
            }
        }
        System.out.println("对数检查结果" + (succeed ? "success" : "fail"));
    }


}
