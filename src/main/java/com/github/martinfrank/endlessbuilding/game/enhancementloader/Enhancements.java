package com.github.martinfrank.endlessbuilding.game.enhancementloader;


import com.github.martinfrank.endlessbuilding.game.EnhancementType;
import com.github.martinfrank.endlessbuilding.game.ResourecType;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "enhancements")
@XmlAccessorType(XmlAccessType.FIELD)
public class Enhancements {

    @XmlElement(name = "enhancement")
    protected List<Enhancement> enhancements;

    @Override
    public String toString() {
        return "Enhancements{" +
                "enhancements=" + enhancements +
                '}';
    }

    public com.github.martinfrank.endlessbuilding.game.Enhancement getEnhancement(EnhancementType enhancementType) {
        Enhancement enhancement = enhancements.stream().filter(e -> e.type.equals(enhancementType.name())).findAny().orElse(null);
        if (enhancement == null) {
            return null;
        }
        return buildEnhancement(enhancement);
    }

    private com.github.martinfrank.endlessbuilding.game.Enhancement buildEnhancement(Enhancement enhancement) {
        EnhancementType type = EnhancementType.valueOf(enhancement.type);

        List<MapFieldType> mapFieldTypes = new ArrayList<>();
        enhancement.fieldTypes.fieldTypes.forEach(ft -> mapFieldTypes.add(MapFieldType.valueOf(ft.type)));

        List<com.github.martinfrank.endlessbuilding.game.QualityUnit> income = new ArrayList<>();
        enhancement.income.qualityUnits.forEach(qu -> income.add(buildQualityUnit(qu)));

        List<com.github.martinfrank.endlessbuilding.game.QualityUnit> buildCosts = new ArrayList<>();
        enhancement.buildcost.qualityUnits.forEach(qu -> buildCosts.add(buildQualityUnit(qu)));

        List<com.github.martinfrank.endlessbuilding.game.QualityUnit> upkeep = new ArrayList<>();
        enhancement.upkeep.qualityUnits.forEach(qu -> upkeep.add(buildQualityUnit(qu)));

        return new com.github.martinfrank.endlessbuilding.game.Enhancement(type, mapFieldTypes, income, buildCosts, upkeep);

    }

    private com.github.martinfrank.endlessbuilding.game.QualityUnit buildQualityUnit(QualityUnit qu) {
        ResourecType resourecType = ResourecType.valueOf(qu.unit);
        return new com.github.martinfrank.endlessbuilding.game.QualityUnit(resourecType, qu.amount);
    }
}
