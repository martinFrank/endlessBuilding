package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "qualityunit")
@XmlAccessorType(XmlAccessType.FIELD)
public class QualityUnit {

    @XmlAttribute
    String unit;

    @XmlAttribute
    Double amount;

    @Override
    public String toString() {
        return "QualityUnit{" +
                "unit='" + unit + '\'' +
                ", amount=" + amount +
                '}';
    }
}
