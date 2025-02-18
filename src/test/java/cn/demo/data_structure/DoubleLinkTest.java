package cn.demo.data_structure;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-18
 * @Description:题：双链表反转
 */
@SpringBootTest
public class DoubleLinkTest {
    @Test
    void test() {

    }

    public class DoubleLinkNode {
        int v;
        DoubleLinkNode next;
        DoubleLinkNode last;

        public DoubleLinkNode(int v) {
            this.v = v;
        }
    }

    //双链表的反转
    DoubleLinkNode reverseDoubleLink(DoubleLinkNode head) {
        DoubleLinkNode pre = null;
        DoubleLinkNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
