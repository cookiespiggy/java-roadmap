package org.hupidou.springroadmap.namespacehandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 在Spring中，spring.handlers文件是用来定义命名空间处理器的映射关系的。
 * 命名空间处理器是用来解析自定义命名空间的，它们负责将自定义命名空间中的元素转换为Spring的Bean定义，从而实现自定义标签的解析和处理。
 * <p>
 * Spring框架在启动时会自动加载spring.handlers文件，并将其中的命名空间处理器映射关系加载到内存中。当Spring解析XML配置文件时，如果遇到自定义命名空间，
 * 就会根据spring.handlers文件中的映射关系找到对应的命名空间处理器，并调用它的解析方法来解析自定义标签。
 * <p>
 * 因此，spring.handlers文件的作用是为自定义命名空间提供映射关系，它定义了命名空间和命名空间处理器之间的对应关系，从而让Spring能够正确地解析自定义标签。
 * <p>
 * 这样，当Spring容器启动时，就会自动加载MyNamespaceHandler类，并注册自定义的解析器。
 * 当Spring配置文件中使用my:my-bean元素时，就会调用MyBeanDefinitionParser类的parse方法，生成对应的BeanDefinition对象，并将其注册到Spring容器中。
 * 最终，Spring容器会根据这个BeanDefinition对象创建一个名为my-bean的Bean，并注入到其他的Bean中。
 */
public class StartMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("namespacehandler.xml");


        MyBean bean = ctx.getBean(MyBean.class);
        System.out.println(bean);


    }
}
