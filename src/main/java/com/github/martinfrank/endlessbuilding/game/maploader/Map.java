package com.github.martinfrank.endlessbuilding.game.maploader;

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
