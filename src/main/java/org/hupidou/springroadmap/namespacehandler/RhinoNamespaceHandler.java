package org.hupidou.springroadmap.namespacehandler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RhinoNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("rhino", new RhinoAnnotationDefinitionParser());
    }
}
