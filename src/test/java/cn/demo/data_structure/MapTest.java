package cn.demo.data_structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Auther: gina
 * @Date: 2025-02-16
 * @Description:数据结构测试：HashMap 和TreeMAp
 */
@SpringBootTest
@Slf4j
public class MapTest {
    HashMap<Integer, String> map = new HashMap<>();
    TreeMap<Integer, String> tMap = new TreeMap<>();

    @BeforeMethod
    void intTest() {
        map.put(1, "你好1");
        map.put(3, "你好3");
        map.put(6, "你好6");
        map.put(8, "你好8");
        map.put(2, "你好2");
        map.put(-1, "你好-1");
        map.put(9, "你好9");

        tMap.put(3, "我是老六，嘻嘻");
        tMap.put(0, "我是老六，嘻嘻");
        tMap.put(7, "我是老六，嘻嘻");
        tMap.put(2, "我是老六，嘻嘻");
        tMap.put(5, "我是老六，嘻嘻");
        tMap.put(9, "我是老六，嘻嘻");
    }

    /*
     * treeMap时间复杂度： Logn级别
     * */
    @Test
    void testTreeMap() {
        print(tMap.containsKey(7));
        print(tMap.containsKey(6));
        print(tMap.get(3));
        tMap.put(3, "我是老八，嘻嘻");
        print(tMap.get(3));

        tMap.remove(3);
        print(tMap.get(3));

        //对treeMap内部，是按照key进行排序的。
        print(tMap.firstKey());
        print(tMap.lastKey());

        //<=5 ,输出：距离5最近的key
        print(tMap.floorKey(5));
        print(tMap.floorKey(6));

        System.out.println("------------------");
        //>=5,输出：距离5最近的key
        print(tMap.ceilingKey(5));
        print(tMap.ceilingKey(6));

        //如果使用非原生类型作为key，需要自己定义排序规则，并且把比较器传入到定义的TreeMap里。eg ,
        Comparator<Node> c = Comparator.comparingInt(Node::getV);
        TreeMap<Node,String> t2=new TreeMap<>(c);
        //ConcurrentSkipListMap<Integer, String> skipMap = new ConcurrentSkipListMap(c);
        t2.put(new Node(1,"12str"), "i:j");
        t2.put(new Node(36,"12str"), "s");
        t2.put(new Node(12,"12str"), "c");
        t2.put(new Node(27,"12str"), "e");
        t2.put(new Node(100,"12str"), "i:j");
        print("max(value)="+ t2.lastEntry().getKey()+",key="+ t2.lastEntry().getValue());
    }

    /*
     * Hash表 增删查，时间复杂度都是big O(1) .
     * */
    @Test
    void testHashMap() {
        print(map.get(7));
        print(map.get(8));
        System.out.println("======================================");

        //新增 ，更新
        map.put(8, "123");

        print(map.get(8));
        map.remove(8);

        print(map.get(8));
        System.out.println("======================================");
        //如果值存在则返回true
        print(map.containsKey(9));
        //不存在的值，返回false
        print(map.containsKey(8));


        map.put(200, "hello");
        Integer a = 200;
        Integer b = 200;
        print(a == b);
        //java里== 比较的是地址，-128到127之间数值可以复用，所以这个范围的值相等，则==比较的结果就是true
        //除了Integer类型，还有Double, Flow,String ,Char 在map里contains获取的都是值。
        print(map.containsKey(a));
        print(map.containsKey(b));

        System.out.println("-----------");

        //非原生类型，按内存地址进行传递
        Node node1 = new Node(1, "str1");
        Node node2 = new Node(1, "str2");
        HashMap<Node, String> map2 = new HashMap<>();
        map2.put(node1, "node1");
        map2.put(node2, "node2");
        print(map2.containsKey(node1));
        print(map2.containsKey(node2));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class Node {
        private int v;
        private String str;
    }


    void print(Object str) {
        System.out.println(String.valueOf(str));
    }
}
