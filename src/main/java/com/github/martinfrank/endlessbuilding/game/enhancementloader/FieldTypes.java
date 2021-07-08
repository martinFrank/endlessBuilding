package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "fieldtypes")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldTypes {

    @XmlElement(name = "fieldtype")
    List<FieldType> fieldTypes;

    @Override
    public String toString() {
        return "BuildCost{" +
                "fieldTypes=" + fieldTypes +
                '}';
    }
}
