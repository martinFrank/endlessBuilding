package com.github.martinfrank.endlessbuilding.gui;


import com.github.martinfrank.endlessbuilding.game.Tool;
import com.github.martinfrank.endlessbuilding.map.MapEdge;
import com.github.martinfrank.endlessbuilding.map.MapField;
import com.github.martinfrank.endlessbuilding.map.MapNode;

public class MouseSelection {

    private final int x;
    private final int y;
    private MapNode node;
    private MapEdge edge;
    private MapField field;
    private Tool tool;
    private boolean mousePrimary;
    private boolean mouseSecondary;

    public MouseSelection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MapNode getNode() {
        return node;
    }

    public void setNode(MapNode node) {
        this.node = node;
    }

    public boolean hasNode() {
        return node != null;
    }

    public MapEdge getEdge() {
        return edge;
    }

    public void setEdge(MapEdge edge) {
        this.edge = edge;
    }

    public boolean hasEdge() {
        return edge != null;
    }

    public MapField getField() {
        return field;
    }

    public void setField(MapField field) {
        this.field = field;
    }

    public boolean hasField() {
        return field != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public boolean isMousePrimary() {
        return mousePrimary;
    }

    public void setMousePrimary(boolean mouseLeft) {
        this.mousePrimary = mouseLeft;
    }

    public boolean isMouseSecondary() {
        return mouseSecondary;
    }

    public void setMouseSecondary(boolean mouseSecondary) {
        this.mouseSecondary = mouseSecondary;
    }

    @Override
    public String toString() {
        String fieldInfo = field == null ? "null" : field.getIndex().toString() + " " + field.getData().getMapFieldType();
        return "mouse selection @ " + x + "/" + y + " field='" + fieldInfo + "', edge='" + edge + "', node='" + node + "'.";
    }
}
