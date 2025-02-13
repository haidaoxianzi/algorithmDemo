package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-12
 * @Description:随机数扩展练习
 */
@SpringBootTest
public class RandomExtTest {
    int testTimes = 1000000;

    /**
     * 从1-5随机 到1-7随机
     */
    @Test
    void test15Ext17() {

        int[] counts = new int[7];
        for (int i = 0; i < testTimes; i++) {
            counts[trans01To17()]++;
        }
        for (int a = 0; a < counts.length; a++) {
            System.out.println((a + 1) + "出现次数 ：" + counts[a]);
        }
    }

    @Test
    void testTimes() {
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (trans15To01() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("================");

        for (int i = 0; i < 100; i++) {

            System.out.println(trans01To17());
        }

    }

    /**
     * step1、对原等概率随机函数的扩容，先把15转为01发生器
     * 1～5随机函数，转为01发生器
     *
     * @return
     */
    static int trans15To01() {
        int ans = 0;
        do {
            ans = f15();
        } while (ans == 3);

        return ans > 3 ? 0 : 1;
    }

    //step2、基于目标诉求（扩容的随机范围）,往二进制上转
    //0~6等概率输出
    @Test
    int trans01To17() {
        int n;
        do {
            n = (trans15To01() << 2) + (trans15To01() << 1) + trans15To01();
        } while (n == 7);
        return n;
    }

    //1~5范围
    static int f15() {
        return (int) (Math.random() * 5) + 1;
    }
}
