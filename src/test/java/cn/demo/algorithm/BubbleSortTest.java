package cn.demo.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: gina
 * @Date: 2025-01-29
 * @Description:冒泡排序----舍弃【有点问题】
 */
@Slf4j
@SpringBootTest
public class BubbleSortTest {

    /**
     * 冒泡排序:数据顺序随机
     */
    @Test
    void bubbleSortTest() {
        int size = 10;
        //int[] arr = genArr(size);
        int[] arr = {-19, -2008, -1367, -3253, 48, 88, 91, 14, 1429, 114};
        bubbleSort(arr);
        print(arr);
    }

    /**
     * 冒泡排序
     */
    @Test
    void bubbleSort(int[] arr) {
        for (int j = arr.length - 1; j > 0; j--) {
            int max = 0;
            //最大下标沉底
            for (int i = 0; i <= j; i++) {
                max = arr[max] > arr[i] ? max : i;
            }
            if (max != j) {
                swap(arr, max, j);
            }
        }
    }

    /**
     * 数组校对器
     */
    @Test(invocationCount = 100, threadPoolSize = 3)
    void testDataCheck() {
        int size = 10;
        int[] arr = genArr(size);
        int[] arr2 = new int[size];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        Arrays.sort(arr);

        bubbleSort(arr2);
        //print(arr);
        compareArrays(arr, arr2);
    }


    private int[] genArr(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt();
        }
        return arr;
    }

    void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private void compareArrays(int[] arr, int[] arr2) {
        boolean isTrue = true;
        for (int i = 0; i < arr.length; i++) {
            isTrue = arr[i] != arr2[i] ? false : true;
            if (!isTrue) break;
        }
        if (!isTrue) log.info("校对数据结果:{}", isTrue);
    }
}
