package cn.demo.algorithm;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * @Auther: gina
 * @Date: 2025-02-10
 * @Description:插入排序
 */
@SpringBootTest
public class InsertSortTest {

    @Test
    void test() {
        int[] arr = genArr(7);
        print(arr);
        insertSort(arr);
        print(arr);
    }

    //插入排序
    void insertSort(int[] arr) {

        for (int end = 1; end < arr.length; end++) {
            for (int pre = end - 1; pre - 1 >= 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre, pre - 1);
            }
        }
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int[] genArr(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(10000);
        }

        return arr;
    }

    void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
