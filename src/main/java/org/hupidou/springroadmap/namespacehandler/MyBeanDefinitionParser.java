package org.hupidou.springroadmap.namespacehandler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class MyBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyBean.class);
        beanDefinition.getPropertyValues().add("name", name);
        beanDefinition.getPropertyValues().add("age", age);

        String beanName = parserContext.getReaderContext().generateBeanName(beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(beanName, beanDefinition);

        return beanDefinition;
    }
}