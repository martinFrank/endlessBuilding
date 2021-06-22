package com.github.martinfrank.endlessbuilding.game.map;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
class Map {

    @XmlAttribute
    Integer width;

    @XmlAttribute
    Integer height;

    @XmlElement(name = "field")
    protected List<Field> fields;
}
