package com.ez.ib.web;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ClassName: APITest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-12 下午2:24 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class APITest {

    @Test
    public void fileAPITest() throws Exception {
        File file = new File("https://baidu.com", "a.img");
        System.out.println(file.toString());
    }

    @Test
    public void javaRegulartest() throws Exception {
        String test = "     测试文本正在测试中";
        test = "1．已知a=（1，sin2x），b=（2，sin2x），其中x∈（0，π）．若|a•b|=|a|•|b|，则tanx的值等于（　　）";
        String pattern = "^测试";
        pattern = "^\\d+";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(test);

        while (m.find()) {
            System.out.println(m.start());
        }

    }

    @Test
    public void java8api() throws Exception {
        String b = "1";
        List<Long> dataset = Arrays
                .stream(b.split(","))
                .map(item -> Long.parseLong(item))
                .collect(Collectors.toList());

        Long[] data = dataset.toArray(new Long[0]);
        System.out.println();

    }


    @Test
    public void pathTest() throws Exception {
        String parent = "http://www.qq.com\\";
        String test = "/test";


        if (parent.endsWith("/") || parent.endsWith("\\")) {
            System.out.println(parent.substring(0, parent.length() - 1));
        }


        Path path = Paths.get(parent, test);

        System.out.println(path.toString());

        File f = new File(parent, test);
        System.out.println(f.toString());
    }

    @Test
    public void test() throws Exception {
        C c = new C();
        c.print();
    }

    @Test
    public void test2() throws Exception {
        List<String> test = Lists.newArrayList("a", "b", "c", "d");
        Iterator<String> it = test.iterator();
        while (it.hasNext()) {
            String k = it.next();
            if (k.equals("b")) {
                it.remove();
            }
        }

        System.out.println();
    }

    @Test
    public void kk() {
        String abcd = "测试＝＝＝＝大饭店飞度";
        String test1 = abcd.replaceAll("测试", "\\$\\{\\{rootPath\\}\\}");
        String test2 = test1.replaceAll("\\$\\{\\{rootPath\\}\\}", "yingwen");
        System.out.println();
    }

    @Test
    public void testKK() throws Exception {
        List<String> list = Lists.newArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        ListIterator<String> it1 = list.listIterator();
        while (it1.hasNext()) {
            System.out.println("hasPrevious:" + it1.hasPrevious());
//            if (it1.hasPrevious()) {
//                System.out.println("previous:" + it1.previous());
//            }
            System.out.println("nextIndex:" + it1.nextIndex());
            System.out.println("previousIndex:" + it1.previousIndex());
            System.out.println("next:" + it1.next());
        }
        ListIterator<String> it2 = list.listIterator(list.size());
        while (it2.hasPrevious()) {
            System.out.println("nextIndex:" + it2.nextIndex());
            System.out.println("previousIndex:" + it2.previousIndex());
            System.out.println("hasNext:" + it2.hasNext());
            System.out.println("previous:" + it2.previous());
        }
    }

    @Test
    public void testList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add("" + i);
        }

        HashMap<String, String> map = new HashMap<>();

    }

    @Test
    public void testMap() throws Exception {
        System.out.println(100 & 5896412);
        ConcurrentHashMap map = new ConcurrentHashMap();

    }

    @Test
    public void getIPTest() throws Exception {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            // 得到每一个网络接口绑定的所有ip
            NetworkInterface nif = en.nextElement();
            Enumeration<InetAddress> inet = nif.getInetAddresses();
            // 遍历每一个接口绑定的所有ip
            while (inet.hasMoreElements()) {
                InetAddress ip = inet.nextElement();
//                if (!ip.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ip.getHostAddress())) {
//                    String ipaddress = ip.getHostAddress();
//                    System.out.println(ipaddress);
//                }

                String ipaddress = ip.getHostAddress();
                System.out.println(ipaddress);
            }

        }
    }

    @Test
    public void testString(){
        String a="http://192.168.1.145:8001/ib/search?addressType=0";

        System.out.println(a.lastIndexOf('?'));
    }
}
