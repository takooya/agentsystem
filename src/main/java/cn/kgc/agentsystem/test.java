package cn.kgc.agentsystem;

import cn.kgc.agentsystem.common.MD5;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//        扫描输出所有IoC容器给我们的bean
        for (String name : ac.getBeanDefinitionNames()) {
            System.out.println("这是:" + name);
        }
    }

    @Test
    public void test01() {
        String s = MD5.MD5Encode("123456");
        System.out.println(s);
    }
}
