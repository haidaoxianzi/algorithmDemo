package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-12
 * @Description:01不等概率随机 到01等概率随机
 */
@SpringBootTest
public class Random01To01 {
    int testTimes = 1000000;

    /**
     * 01不等概率，调用后输出情况：00，11，01，10.
     * 假设0出现概率x，那么1出现概率 1-x
     * 排除掉结果00和11，就只剩下结果01，10.概率是 x(1-x)
     * 代码实现：出现00或11就回执函数，前后不同调用次数值保持不一致，即可实现01等概率随机。
     * 【不同调用次数出现在同一代码块】
     */

    @Test
    void testTimes() {
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (randomRegular01() ==0) {
                count++;
            }
        }
        System.out.println((double) count / testTimes);
    }

    /**
     * 01随机发生器，不等概率
     */
    @Test
    int random01() {
        return Math.random() < 0.91 ? 0 : 1;
    }

    /**
     * 01等概率随机发生器
     */
    @Test
    int randomRegular01() {
        int rr;
        do {
            rr = random01();
        } while (rr == random01());
        return rr;
    }
}
