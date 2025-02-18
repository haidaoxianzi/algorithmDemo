package cn.demo.data_structure;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Auther: gina
 * @Date: 2025-02-18
 * @Description:题：单链表反转
 */
@SpringBootTest
public class SingleLinkTest {
    @Test
    void test() {

    }

    public class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //单链表反转，且返回反转后链表的头指针
    public Node reverseSingleLink(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;//head.next指针放在next节点存储，含义：next指向了头节点的下个节点
            head.next = pre;//head.next指针指向pre
            pre = head;//pre节点存储head节点内容
            head = next;//head节点空出来存储next节点内容
        }
        return pre;
    }
}
