package cn.demo.test_data_structure;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: gina
 * @Date: 2025-02-18
 * @Description:基于双向链表，实现双端队列的练习 弹出要留意是否null ,是否一个节点
 * push要留意是否null .
 */
@SpringBootTest
public class SDQueueByDoubleLinkedTest {

    public static class Node<V> {
        private V val;
        private Node<V> next;
        private Node<V> last;

        public Node(V val) {
            next = null;
            last = null;
            this.val = val;
        }
    }

    public static class MySDQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        //双端队列
        public MySDQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushTail(V v) {
            Node<V> cur = new Node<>(v);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        public V popTail() {
            V ans = null;
           /*
            我的写法有问题，从尾巴弹出，要考虑临界点 1个节点的情况
            if (head == null) {
                return ans;
            } else {
                ans = tail.val;
                tail = tail.last;
                tail.next = null;
            }*/
            if (head == null) {
                return ans;
            }
            ans = tail.val;
            size--;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            return ans;

        }

        //头插
        public void pushHead(V v) {
            Node<V> cur = new Node<>(v);
            if (null == head) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;

        }

        //头弹
        public V popHead() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = head.val;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            return ans;

        }

        public V peekHead() {
            if (head == null) {
                return null;
            }

            return head.val;
        }

        public V peekTail() {
            if (tail == null) {
                return null;
            }
            return tail.val;
        }
    }

    /**
     * 对数器测试
     */
    @Test
    void test() {
        int testTimes = 10000;
        int maxValue = 200000;
        MySDQueue<Integer> sdQueue = new MySDQueue();
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < testTimes; i++) {
            if (sdQueue.isEmpty() != queue.isEmpty() || queue.size() != sdQueue.size) {
                System.out.println("error");
                break;
            }
            Double d = Math.random();
            int num = (int) (d * maxValue);
            if (d < 0.2) {
                sdQueue.pushTail(num);

                queue.addLast(num);

            } else if (d < 0.5 && d > 0.3) {
                sdQueue.pushHead(num);
                queue.addFirst(num);
            } else if (d >= 0.5 && d <= 0.8) {
                if (!sdQueue.isEmpty()) {
                    int num1 = sdQueue.popHead();
                    int num2 = queue.removeFirst();
                    if (num1 != num2) {
                        System.out.println("error");
                    }
                }
            } else {
                if (!sdQueue.isEmpty()) {
                    int num1 = sdQueue.popTail();
                    int num2 = queue.removeLast();
                    if (num1 != num2) {
                        System.out.println("error");
                    }
                }
            }

        }
        if (!sdQueue.isEmpty()) {
            if (sdQueue.size() != queue.size()) {
                System.out.println("error");
            }
            if (sdQueue.peekHead() != queue.peekFirst() || sdQueue.peekTail() != queue.peekLast()) {
                System.out.println("error");
            }
        }
    }

}
