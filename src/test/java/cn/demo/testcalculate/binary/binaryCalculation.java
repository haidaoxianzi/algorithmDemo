package cn.demo.testcalculate.binary;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-07
 * @Description:二进制计算
 */
@SpringBootTest
public class binaryCalculation {
    @Test
    void initData() {
        int num = 83928328;
        print(num);
    }


    void print(int num) {
        for (int i = 31; i >= 0; i--) {
            //num & (1 << i)的结果是0或1
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }


}
