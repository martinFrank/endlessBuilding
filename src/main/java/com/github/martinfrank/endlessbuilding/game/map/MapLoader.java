package com.github.martinfrank.endlessbuilding.game.map;

import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapFactory;
import com.github.martinfrank.endlessbuilding.map.MapField;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import com.github.martinfrank.maplib.MapStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;

public class MapLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapLoader.class);

    private final ResourceManager resourceManager;
    private final MapFactory mapFactory;

    public MapLoader(ResourceManager resourceManager, MapFactory mapFactory) {
        this.resourceManager = resourceManager;
        this.mapFactory = mapFactory;
    }

    private void setField(Map map, Field field) {
        MapField mapField = map.getField(field.x, field.y);
        mapField.getData().setMapFieldType(MapFieldType.valueOf(field.type));
    }

    private com.github.martinfrank.endlessbuilding.game.map.Map loadXmlMap() throws JAXBException, MalformedURLException {
        JAXBContext jaxbContext = JAXBContext.newInstance(com.github.martinfrank.endlessbuilding.game.map.Map.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (com.github.martinfrank.endlessbuilding.game.map.Map)
                jaxbUnmarshaller.unmarshal(resourceManager.getDefaultMap());
    }

    public Map loadDefaultMap() throws MalformedURLException, JAXBException {
        com.github.martinfrank.endlessbuilding.game.map.Map xmlMap = loadXmlMap();
        Map map = mapFactory.createMap(xmlMap.width, xmlMap.height, MapStyle.HEX_VERTICAL);
        xmlMap.fields.forEach(f -> setField(map, f));
        return map;
    }
}
