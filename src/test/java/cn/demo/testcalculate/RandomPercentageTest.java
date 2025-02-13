package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-10
 * @Description:验证java中随机数函数是等概率随机的
 */
@SpringBootTest
public class RandomPercentageTest {

    @Test
    void test1() {
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.35) {
                count++;
            }
        }
        //  0.35以内的概率接近0.35，说明，随机数等概率
        System.out.println((double) count / testTimes);
        System.out.println("--------------------");

        //[0,1）-->[0,8)
        count=0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random()*8 < 5) {
                count++;
            }
        }
        System.out.println((double)count/testTimes);
        System.out.println(5/8d);
        System.out.println("--0到8范围------------------");
    }

    @Test
    void test2() {
        //输出等概率随机数，范围[0.1),double类型前闭后开

        int K = 9;
        //[0,9) -->[0,8]
        int[] arr = new int[9];
        for (int i = 0; i < 1000000; i++) {
            int r = (int) (Math.random() * K);
            arr[r]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println("数字：" + i + " 出现的次数: " + arr[i]);
        }
        //根据结果可以看出，次数很相近，推断出【Math.random()函数】是等概率随机输出
    }
}
