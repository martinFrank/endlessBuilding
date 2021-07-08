package com.github.martinfrank.endlessbuilding.res;

import com.github.martinfrank.endlessbuilding.game.Enhancement;
import com.github.martinfrank.endlessbuilding.game.EnhancementType;
import com.github.martinfrank.endlessbuilding.game.enhancementloader.EnhancementLoader;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

public class EnhancementManager {

    private final EnhancementLoader enhancementLoader;

    public EnhancementManager(ResourceManager resourceManager) throws MalformedURLException, JAXBException {
        enhancementLoader = new EnhancementLoader(resourceManager);
    }

    public Enhancement getEnhancement(EnhancementType enhancementType) {
        return enhancementLoader.getEnhancement(enhancementType);
    }
}
