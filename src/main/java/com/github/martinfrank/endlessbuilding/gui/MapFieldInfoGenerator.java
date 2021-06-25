package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.FieldQualityUnitMapper;
import com.github.martinfrank.endlessbuilding.game.QualityUnit;
import com.github.martinfrank.endlessbuilding.map.MapField;

import java.util.List;
import java.util.stream.Collectors;

public class MapFieldInfoGenerator {

    public static String generateInfo(MapField currentFieldInfo) {
        if (currentFieldInfo == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("field ").
                append(currentFieldInfo.getIndex().getX()).
                append("/").
                append(currentFieldInfo.getIndex().getY()).
                append("\n").append("field type: ").append(currentFieldInfo.getData().getMapFieldType()).
                append("\n");
        if (currentFieldInfo.getData().getEnhancement() != null) {
            appendEnhancement(sb, currentFieldInfo);
        } else {
            List<QualityUnit> quList = FieldQualityUnitMapper.get(currentFieldInfo.getData().getMapFieldType());
            String quCollection = quList.stream().map(qu -> qu.unit.toString() + ":" + qu.amount).collect(Collectors.joining(","));
            sb.append("harvest from clicking: ").append(quCollection);
        }
        //FIXME describe what would happen with your current tool!

        return sb.toString();
    }

    private static void appendEnhancement(StringBuilder sb, MapField currentFieldInfo) {
        //FIXME proper text (once enhancemnt is implemented)
        sb.append(currentFieldInfo.getData().getEnhancement());
    }
}
