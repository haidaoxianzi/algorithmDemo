package cn.demo.test_data_structure;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: gina
 * @Date: 2025-02-18
 * @Description:基于链表，对队列和栈的练习，数据结构链表练习题
 */
@SpringBootTest
public class QueueAndStackByLinkedTest {

    public static class LinkedNode<V> {
        public V v;
        public LinkedNode<V> next;

        public LinkedNode(V v) {
            this.v = v;
            next = null;
        }
    }

    public static class MyQueue<V> {
        //队列先进先出，若用链表表示， 新增元素追加到链表的尾部，提取数据从头节点获取即可。所以需要两个指针节点
        private LinkedNode<V> tail;
        private LinkedNode<V> head;
        private int size;

        public MyQueue() {
            tail = null;
            head = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //弹出
        public V poll() {
            V ans = null;
            if (head != null) {
                //队列先进先出，从头节点弹
                ans = head.v;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
                size = 0;
            }
            return ans;
        }

        //新增,在尾部插入
        public void offer(V v) {
            LinkedNode<V> cur = new LinkedNode<V>(v);
            if (tail == null) {//todo 在尾部插入，所以要先判断尾巴是否null
                tail = cur;
                head = cur;
            } else {
               /* cur = tail.next;
                tail = cur;*/

               // demo里是这个值
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        //即将获取的值
        public V peek() {
            if (head != null) {
                return head.v;
            }
            return null;
        }
    }

    public static class MyStack<V> {
        //栈：后进先出，用链表存储，则进来的元素作为head节点，出去直接获取头节点数据即可，所以只需要一个节点指针
        private LinkedNode<V> head;
        private int size;

        public MyStack(){
            size=0;
            head=null;
        }
        //是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //获取size的值
        public int size() {
            return size;
        }


        //新增元素
        public void push(V v) {
            LinkedNode<V> cur = new LinkedNode<>(v);
            size++;
            if (head != null) {
                // cur = head.next;//todo 这里我写错了，不应该是cur存储头节点里的指针
                cur.next = head;//todo cur插入头节点，应该是cur.next指向head
            }
            head = cur;
        }

        //弹出元素
        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.v;
                head = head.next;
                size--;
            } else {
                size = 0;
            }
            return ans;
        }

        // 下一次弹出元素的值
        public V peek() {
            return head != null ? head.v : null;
        }

    }


    @Test
    public static void testStack() {
        int testTimes = 10000;
        int maxValue = 200000;
        MyStack<Integer> myStack = new MyStack();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < testTimes; i++) {
            if (myStack.isEmpty() != stack.isEmpty() || myStack.size != stack.size()) {
                System.out.println("testStack Error");
            }
            Double d = Math.random();

            if (d < 0.46) {
                int num = (int) (d * maxValue);
                myStack.push(num);
                stack.push(num);


            } else if (d > 0.5) {
                if (!myStack.isEmpty()) {
                    int a = myStack.pop();
                    int b = stack.pop();
                    if (a != b) {
                        System.out.println("Error");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int a = myStack.peek();
                    int b = stack.peek();
                    if (a != b) {
                        System.out.println("Error");
                    }
                }
            }
        }
        if (myStack.isEmpty() != stack.isEmpty() || myStack.size != stack.size()) {
            System.out.println("Error");

        }
        if (!myStack.isEmpty()) {
            System.out.println(myStack.peek() .equals( stack.peek()) ? "" : "Error");
        }
        System.out.println("测试结束！");
    }

    @Test
    public static void testQueue() {
        int testTimes =   10000;
        int maxValue = 20000;
        MyQueue<Integer> myQueue = new MyQueue();
        Queue<Integer> queue = new LinkedList();
        Object o = new Object();

        for (int i = 0; i < testTimes; i++) {
            if (myQueue.isEmpty() != queue.isEmpty() || myQueue.size() != queue.size()) {
                System.out.println("testQueue Error");
            }
            Double d = Math.random();
            if (d < 0.38) {
                int num = (int) (d * maxValue);
                myQueue.offer(num);
                queue.offer(num);
            } else if (d > 0.5) {
                if (!myQueue.isEmpty()) {
                    int a = myQueue.poll();
                    int b = queue.poll();
                    if (a != b) {
                        System.out.println("testQueue Error");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int a = myQueue.peek();
                    int b = queue.peek();
                    if (a != b) {
                        System.out.println("testQueue Error");
                    }
                }
            }

        }
        if (myQueue.isEmpty() != queue.isEmpty() || myQueue.size() != queue.size()) {
            System.out.println("testQueue Error");
        }
        if (!myQueue.isEmpty()) {
            int a = myQueue.peek();
            int b = queue.peek();
            if (a != b) {
                System.out.println("testQueue Error");
            }
        }
        System.out.println("测试结束！");
    }

    @Test
    void testStackAndQueue() {
        testQueue();
        testStack();
    }
}
