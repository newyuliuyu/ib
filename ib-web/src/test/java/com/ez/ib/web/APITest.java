package com.ez.ib.web;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
            System.out.println(parent.substring(0,parent.length()-1));
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
}
