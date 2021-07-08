package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "buildcost")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildCost {

    @XmlElement(name = "qualityunit")
    List<QualityUnit> qualityUnits;

    @Override
    public String toString() {
        return "BuildCost{" +
                "qualityUnits=" + qualityUnits +
                '}';
    }
}
