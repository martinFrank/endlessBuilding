package com.github.martinfrank.endlessbuilding.game.enhancementloader;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "enhancement")
@XmlAccessorType(XmlAccessType.FIELD)
public class Enhancement {

    @XmlAttribute(name = "type")
    String type;

    @XmlElement(name = "fieldtypes")
    FieldTypes fieldTypes;

    @XmlElement(name = "income")
    Income income;

    @XmlElement(name = "buildcost")
    BuildCost buildcost;

    @XmlElement(name = "upkeep")
    Upkeep upkeep;

    @Override
    public String toString() {
        return "Enhancement{" +
                "type='" + type + '\'' +
                "fieldTypes='" + fieldTypes + '\'' +
                ", income=" + income +
                ", buildcost=" + buildcost +
                ", upkeep=" + upkeep +
                '}';
    }
}
