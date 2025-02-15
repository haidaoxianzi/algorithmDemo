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

    @Test(invocationCount = 1)
    void testInsertSort() {
          int[] arr = genArr(7);
        // int[] arr = {228, 14};
       // int[] arr = {3027, 1990, 5859, 7377, 7886, 5201, 233};
        //1990 3027 5201 5859 7377 7886 233
        print(arr);
        insertSort(arr);
        print(arr);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }


        for (int end = 1; end <= arr.length; end++) {
            if (arr.length == 2) {
                if (arr[end] < arr[end - 1])
                    swap(arr, end, end - 1);
                break;
            }
            //end值域是最大下标=len-1 ,pre-1>0才能进入循环，即 len-3>=0才能进入循环，所以循环生效前提是数组长度>=3
            //所以额外要考虑数组长度为2以内(包含2)的情况。
            for (int pre = end - 1; pre - 1 >= 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre, pre - 1);
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
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

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
