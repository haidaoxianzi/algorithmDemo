package cn.demo.testcalculate;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-08
 * @Description: 计算阶乘加和：1!+2!+3!+...+N!
 */
@SpringBootTest
public class JieChengSumTest {

    @Test
    void sumJieCheng() {
        int n = 4;
        int ans = 0;
        int tmpRs = 1;

        for (int i = 1; i <= n; i++) {
            tmpRs = tmpRs * i;
            ans += tmpRs;
        }
        if(n==0){
            System.out.println(1);
        }else {
            System.out.println(ans);
        }
    }
}
