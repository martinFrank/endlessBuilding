package com.github.martinfrank.endlessbuilding.gui;

public class ToolSelection {

    public Tool selected;

    public Tool selectInfoTool() {
        return setTool(Tool.INFO);
    }

    public Tool selectLumbermillTool() {
        return setTool(Tool.LUMBERMILL);
    }

    public Tool selectMineTool() {
        return setTool(Tool.MINE);
    }

    public Tool selectFarmTool() {
        return setTool(Tool.FARM);
    }

    public Tool selectFurnaceTool() {
        return setTool(Tool.FURNACE);
    }

    public Tool selectHarvestTool() {
        return setTool(Tool.HARVEST);
    }

    private Tool setTool(Tool tool) {
        selected = tool;
        return tool;
    }


}
