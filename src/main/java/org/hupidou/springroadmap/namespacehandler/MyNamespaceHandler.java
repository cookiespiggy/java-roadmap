package org.hupidou.springroadmap.namespacehandler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("my-bean-2", new MyBeanDefinitionParser());
    }
}