package cn.wq.algorithm.cn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: gina
 * @Date: 2025-01-27
 * @Description:选择排序
 */
@SpringBootTest
@Slf4j
public class SelectSortTest {
    /**
     * 方式一
     * 思路：
     * 初始化起始位置，比较值范围start ~ (len-1) ;
     * 遍历查找最小值，记录坐标min；
     * 把start位置值和min坐标位置值互换；
     * 进入下一轮比较，比较范围（start+1）～（len-1）；
     * 继续循环：后续比较得到最小值，进行互换操作
     */
    @Test
    void test() {
        Integer[] arr = {1, 9, 4, -1, 88, 10};
        int start = 0;
        int min = 0;
        while (true) {
            for (int i = start; i < arr.length; i++) {
                if (arr[min] > arr[i]) {
                    min = i;
                }
            }
            if (start == arr.length - 1) {
                break;
            }
            int a = arr[start];
            arr[start] = arr[min];
            arr[min] = a;
            start += 1;
            min = start;

        }
        for (int i : arr) {
            log.info(String.valueOf(i));
        }
    }

    /**
     * 方式二
     */
    @Test
    void selectionSort() {
        int[] arr = {1, 9, 30, 29, 55, 10, -9, -4, 66};
        int min = 0;
        for (int j = 0; j < arr.length; j++) {
            min = j;
            for (int i = j; i < arr.length; i++) {
                min = arr[min] > arr[i] ? i : min;
            }
            log.info("最小值：{} ", arr[min]);
            swap(arr, min, j);
        }
        for (int i : arr) {
            log.info(String.valueOf(i));
        }
    }

    /**
     * 方式三 ：
     * 优化，把最大值和最小值都查出来
     */
    @Test
    void selectionSort2() {
        int[] arr = {1, 9, 30, 29, 55, 10, -9, -4, 66};
        //移动指针
        int min = 0;
        int max = arr.length - 1;

        //固定位置，收缩
        int start = 0;
        int endPos = arr.length - 1;

        while (min < max) {

            for (int i = start; i <= endPos; i++) {
                min = arr[min] > arr[i] ? i : min;
                max = arr[max] < arr[i] ? i : max;
            }
            log.info("最小值：{},最大值：{} ", arr[min], arr[max]);
            swap(arr, min, start);

            //todo 注意这里，前面 min和start 已调换之后，要考虑后面再次调换是否有冲突位置，若有则进行对应调整。
            max = max == min ? start : (max == start ? min : max);

            swap(arr, max, endPos);
            log.info("更换后，结果为：最小值：{},最大值：{} ", arr[start], arr[endPos]);
            min = ++start;
            max = --endPos;
        }

        for (int i : arr) {
            log.info(String.valueOf(i));
        }


    }

    void swap(int[] arr, int i, int j) {
        int tmp1 = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp1;
    }
}
