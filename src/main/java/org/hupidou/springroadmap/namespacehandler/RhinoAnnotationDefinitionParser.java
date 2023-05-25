package org.hupidou.springroadmap.namespacehandler;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.concurrent.atomic.AtomicInteger;

public class RhinoAnnotationDefinitionParser implements BeanDefinitionParser {

    private static final AtomicInteger counter = new AtomicInteger();

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(false);
        beanDefinition.setBeanClass(RhinoBean.class);
        String id = element.getAttribute("id");
        if (StrUtil.isBlank(id)) {
            id = "rhino_" + counter.incrementAndGet();
        }
        MutablePropertyValues properties = beanDefinition.getPropertyValues();
        if (element.hasAttribute("package")) {
            properties.addPropertyValue("package", element.getAttribute("package"));
        }
        if (element.hasAttribute("order")) {
            properties.addPropertyValue("order", Integer.valueOf(element.getAttribute("order")));
        }
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return beanDefinition;
    }
}
