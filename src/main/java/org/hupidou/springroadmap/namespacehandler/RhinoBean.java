package org.hupidou.springroadmap.namespacehandler;

public class RhinoBean {

    private String annotationPackage;

    private int order = 2147483647;

    public String getPackage() {
        return this.annotationPackage;
    }

    public void setPackage(String annotationPackage) {
        this.annotationPackage = annotationPackage;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "RhinoBean{" +
                "annotationPackage='" + annotationPackage + '\'' +
                ", order=" + order +
                '}';
    }

}
