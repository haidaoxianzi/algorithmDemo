package cn.demo.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: gina
 * @Date: 2025-01-29
 * @Description: 1、选择排序
 */

@Slf4j
public class SelectionSort {

    public static void  selectionSort(int[] arr) {
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
           // log.info("最小值：{},最大值：{} ", arr[min], arr[max]);
            swap(arr, min, start);

            //todo 注意这里，前面 min和start 已调换之后，要考虑后面再次调换是否有冲突位置，若有则进行对应调整。
            max = max == min ? start : (max == start ? min : max);

            swap(arr, max, endPos);
          //  log.info("更换后，结果为：最小值：{},最大值：{} ", arr[start], arr[endPos]);
            min = ++start;
            max = --endPos;
        }

      /*  for (int i : arr) {
            log.info(String.valueOf(i));
        }*/
    }

    static void swap(int[] arr, int i, int j) {
        int tmp1 = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp1;
    }

}
