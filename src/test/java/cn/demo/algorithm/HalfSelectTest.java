package cn.demo.algorithm;

import cn.demo.testcalculate.CheckToolTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-14
 * @Description:二分查找
 */
@SpringBootTest
public class HalfSelectTest {

    @Test
    void halfSelectTest() {
        int testTime = 1;
        int maxSize = 10;
        int maxValue = 500;
        boolean succeed = true;
        int num = 200;
        for (int i = 0; i < testTime; i++) {
            int[] arr = CheckToolTest.randomArray(maxSize, maxValue);
            //数字在数组里，注释释放掉
           /* if (arr[0] > 130) {
                num = arr[0];
            }*/
            InsertSortTest.insertSort(arr);
            boolean a = findCheck(arr, num);
            boolean b = find(arr, num);

           /* if (b) {
                System.out.println("num=" + num + ",在数组里");
                InsertSortTest.print(arr);
            }else{
                System.out.println("num=" + num + ",没在数组里");
                InsertSortTest.print(arr);
            }*/
            if (a != b) {
                InsertSortTest.print(arr);
                System.out.println(num);
                succeed = false;
                break;
            }
        }
        System.out.println("对数器校验结果:" + (succeed ? "正确" : "失败"));
    }

    //arr为有序数组
    boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static boolean findCheck(int[] arr, int num) {
        for (int a : arr) {
            //todo 如果元素值和查找值200，==是不是就不对了呀
            if (a >= num) {
                return true;
            }
        }
        return false;
    }
}
