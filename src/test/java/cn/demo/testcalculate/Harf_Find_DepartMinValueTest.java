package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-15
 * @Description:基于二分法查找局部最小值【非全局最小，局部最小泛指比相邻左右两边都小】 数组非线性排序，且相邻元素不等值
 */
@SpringBootTest
public class Harf_Find_DepartMinValueTest {
    /*查找局部最小值【非数组最小值】*/
    @Test
    void departMinValueTest() {
        int[] arr = CheckToolTest.randomArray(10, 1000);
        CheckToolTest.print(arr);
        int minVal = testDepartMinValue(arr);
        System.out.println("局部最小值：" + minVal);
    }

    /*
     * @Param arr :相邻元素不能相等，数组无序
     * @Return 返回局部最小值下标
     * */
    @Test(invocationCount = 1, threadPoolSize = 1)
    int testDepartMinValue(int[] arr) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        //采用二分法的形式
        int L = 0;
        int R = N - 1;

        if (arr[L] < arr[L+1]) {
            return L;
        }
        if (arr[R] < arr[R - 1]) {
            return R;
        }
        while (L < R - 1) {
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid] > arr[mid - 1]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
        //即便多个元素，不停被赋值的 L和 R 也有可能成为边界位置，这种情况要处理。
        return arr[L] > arr[R] ? R : L;
    }
}
