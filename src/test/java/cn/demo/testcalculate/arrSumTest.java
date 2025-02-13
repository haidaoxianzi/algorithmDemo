package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * @Auther: gina
 * @Date: 2025-02-10
 * @Description:数组加和（l到r位置）
 */
@SpringBootTest
public class arrSumTest {
    @Test
    void test() {
        int arr[] = genArr(7);
        print(arr);
        int[] arr2 = getSumsArr(arr);
        print(arr2);
        int sum = getSumsLR(arr2, 3, 6);
        System.out.println(sum);
    }

    void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    int[] getSumsArr(int[] arr) {
        int arr2[] = new int[arr.length];
        arr2[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr2[i] = arr[i] + arr2[i - 1];
        }
        return arr2;
    }

    int getSumsLR(int[] arr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == 0) {
            return arr[r];
        } else {
            return arr[r] - arr[l - 1];
        }
    }

    int[] genArr(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(1000);
        }
        return arr;
    }
}
