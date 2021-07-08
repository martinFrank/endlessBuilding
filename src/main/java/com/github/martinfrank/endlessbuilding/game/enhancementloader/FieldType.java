package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fieldtype")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldType {

    @XmlAttribute(name = "type")
    String type;
}
