package cn.demo.algorithm;

import cn.demo.algorithm.SelectionSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
        int[] arr = {1, 9, 4, -1, 88, 10};
        int start = 0;
        int min = 0;

        while (true) {
            for (int i = start; i < arr.length; i++) {
                min = arr[min] > arr[i] ? i : min;
            }
            if (start == arr.length - 1) {
                break;
            }
            swap(arr, start, min);

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


    /**
     * 对数器
     * 测试升级：
     * 1、产生足够多的数据样本，根据数据对比结果，进而验证算法逻辑正确性:设置执行50次，并发线程数3
     * 2、使用自己写的算法得到的结果，跟正确结果做对比，验证算法结果是否正确
     */
    private final static ReentrantLock lock = new ReentrantLock();
    private static int count = 0;

    //加锁实现计数器：ReentrantLock
    @Test(invocationCount = 150, threadPoolSize = 3)
    void testCheckedData() {
        lock.lock();
        ++count;
        int[] arr = genSampleData();
        int[] arr2 = new int[10000];
        System.arraycopy(arr, 0, arr2, 0, 10000);

        Arrays.sort(arr);
        SelectionSort.selectionSort(arr2);
        boolean isTrue = true;
        for (int i = 0; i < 10000; i++) {
            isTrue = arr2[i] != arr[i] ? false : true;
            if (!isTrue) {
                break;
            }
        }
        log.info("次数：{} ,算法正确吗？{}", count, isTrue);
        lock.unlock();
    }

    //atomicInteger 做并发场景计数器
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 作废
     */
    @Test(invocationCount = 10, threadPoolSize = 3)
    void testCheckedData2() {

        int[] arr = genSampleData();
        int[] arr2 = new int[10000];
        System.arraycopy(arr, 0, arr2, 0, 10000);

        Arrays.sort(arr);
        SelectionSort.selectionSort(arr2);
        boolean isTrue = true;
        for (int i = 0; i < 10000; i++) {
            isTrue = arr2[i] != arr[i] ? false : true;
            if (!isTrue) {
                break;
            }
        }
        log.info("次数：{} ,算法正确吗？{}", atomicInteger.get(), isTrue);

    }

    //产生随机样本
    int[] genSampleData() {
        int[] arr = new int[10000];
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            arr[i] = r.nextInt();
        }
        return arr;
    }

    //方式四：选择排序
    @Test
    void testSelectSort() {
        int arr[] = { 1, 7, 2, 9, 2,4, 0, -1, 88};//
        //1、首先考虑边界值
        if (null == arr || arr.length < 2) {
            return;
        }

        //1～n-1，2～n-1
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[minPos] > arr[j] ? j : minPos;
            }
            swap(arr, i, minPos);
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
