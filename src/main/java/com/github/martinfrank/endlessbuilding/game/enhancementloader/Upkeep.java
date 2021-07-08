package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "upkeep")
@XmlAccessorType(XmlAccessType.FIELD)
public class Upkeep {

    @XmlElement(name = "qualityunit")
    List<QualityUnit> qualityUnits;

    @Override
    public String toString() {
        return "Upkeep{" +
                "qualityUnits=" + qualityUnits +
                '}';
    }
}
