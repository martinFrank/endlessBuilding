package com.github.martinfrank.endlessbuilding.game.maploader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
class Field {

    @XmlAttribute
    Integer x;

    @XmlAttribute
    Integer y;

    @XmlAttribute
    String type;

}
