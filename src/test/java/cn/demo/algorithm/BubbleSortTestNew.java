package cn.demo.algorithm;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * @Auther: gina
 * @Date: 2025-02-08
 * @Description:冒泡排序
 */
@SpringBootTest
public class BubbleSortTestNew {
    @Test
    void test() {
        int n = 5;
        int[] arr = genArr(n);
        print(arr);
        bubbleSort(arr);
        print(arr);
    }

    //冒泡排序跟选择排序不同的点：冒泡排序，对比相邻的值进行互换，选择排序获取最小值的下标，跟下标首位做对调。
    void bubbleSort(int[] arr) {
        //0~n-1 ,0~n-2
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int[] genArr(int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i+" " );
        }
        System.out.println();
    }
}
