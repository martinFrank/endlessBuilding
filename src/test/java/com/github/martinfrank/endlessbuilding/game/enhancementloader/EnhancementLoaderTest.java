package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import com.github.martinfrank.endlessbuilding.game.Enhancement;
import com.github.martinfrank.endlessbuilding.game.EnhancementType;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

public class EnhancementLoaderTest {

    @Test
    public void test() throws MalformedURLException, JAXBException {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        EnhancementLoader enhancementLoader = new EnhancementLoader(resourceManager);
        Enhancements enhancements = enhancementLoader.loadXmlEnhancements();
        System.out.println(enhancements);

        Enhancement lumbermill = enhancements.getEnhancement(EnhancementType.LUMBERMILL);
        System.out.println(lumbermill);

        Enhancement quarry = enhancements.getEnhancement(EnhancementType.QUARRY);
        System.out.println(quarry);
    }
}
