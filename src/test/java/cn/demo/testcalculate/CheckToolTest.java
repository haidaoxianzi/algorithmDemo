package cn.demo.testcalculate;

import cn.demo.algorithm.InsertSortTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-13
 * @Description:校对器测试类
 */
@SpringBootTest
public class CheckToolTest {

    @Test(threadPoolSize = 3,invocationCount = 12)
    void testCheck() {
        int maxLen = 50;
        int maxValue = 1000;
        int testTimes = 1000000;
        System.out.println("start");
        //step4 通过样本测试次数，做对数器测试
        for (int i = 0; i < testTimes; i++) {
            //step1、获取数组
            int[] arr = randomArray(maxLen, maxValue);
            //step2 拷贝数组
            int[] arr2 = copyArr(arr);
            //对数组进行插入排序
            InsertSortTest.insertSort(arr);
            //step3、测试arr排序结果是否正确
            if (!checkSort(arr)) {
                System.out.println("打印排序出错数组：");
                print(arr2);
                break;
            }
        }
        System.out.println("耐丝！");

    }

    void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * step1、获取数组
     * 根据给定数组最大长度和数组元素最大值，获取一个长度和元素值都随机的数组
     */
    int[] randomArray(int maxLen, int maxValue) {
        int arrLen = (int) (Math.random() * maxLen)+1;
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    /**
     * step2 拷贝数组
     */
    int[] copyArr(int[] arr) {
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr2, 0, arr, 0, arr.length);
        return arr2;
    }

    /** 作废
     * step3、测试arr和arr2是否等长
     * 方式1：验证两个数组是否相等
     */
    boolean equalValue(int[] arr, int[] arr2) {
        if (arr.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方式2：验证数组排序是否正确
     * 思路：只要出现，前一个元素比后面元素大，就说明排序有问题🤨
     * 先给最大值初始化下。
     */
    boolean checkSort(int[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (maxValue > arr[i]) {
                return false;
            }
            maxValue = arr[i];
        }
        return true;
    }

}
