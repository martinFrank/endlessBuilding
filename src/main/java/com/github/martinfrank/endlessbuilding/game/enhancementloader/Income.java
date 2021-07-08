package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "income")
@XmlAccessorType(XmlAccessType.FIELD)
public class Income {

    @XmlElement(name = "qualityunit")
    List<QualityUnit> qualityUnits;

    @Override
    public String toString() {
        return "Income{" +
                "qualityUnits=" + qualityUnits +
                '}';
    }
}
