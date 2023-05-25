package org.hupidou.springroadmap.namespacehandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 本模块的启动类
 */
public class StartMain {

    public static void main(String[] args) {


        ApplicationContext ctx = new ClassPathXmlApplicationContext("demo.xml");
        RhinoBean bean = ctx.getBean(RhinoBean.class);
        System.out.println(bean);


    }
}
