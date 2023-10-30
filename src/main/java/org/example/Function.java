package org.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gg")
public class Function {
    @XmlElement
    private double x;
    @XmlElement
    private double d;

    public void setX(double x) {
        this.x = x;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getX() {
        return x;
    }

    public double getD() {
        return d;
    }
}
