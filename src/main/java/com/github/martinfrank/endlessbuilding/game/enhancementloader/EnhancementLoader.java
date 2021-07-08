package com.github.martinfrank.endlessbuilding.game.enhancementloader;

import com.github.martinfrank.endlessbuilding.game.Enhancement;
import com.github.martinfrank.endlessbuilding.game.EnhancementType;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;

public class EnhancementLoader {

    private final Enhancements enhancements;
    private final ResourceManager resourceManager;

    public EnhancementLoader(ResourceManager resourceManager) throws MalformedURLException, JAXBException {
        this.resourceManager = resourceManager;
        enhancements = loadXmlEnhancements();
    }

    public Enhancements loadXmlEnhancements() throws MalformedURLException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Enhancements.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Enhancements)
                jaxbUnmarshaller.unmarshal(resourceManager.getEnhancements());
    }

    public Enhancement getEnhancement(EnhancementType enhancementType) {
        return enhancements.getEnhancement(enhancementType);
    }
}
