package com.github.martinfrank.endlessbuilding.gui;


import com.github.martinfrank.endlessbuilding.map.MapEdge;
import com.github.martinfrank.endlessbuilding.map.MapField;
import com.github.martinfrank.endlessbuilding.map.MapNode;

public class MouseSelection {

    private final int x;
    private final int y;
    private MapNode node;
    private MapEdge edge;
    private MapField field;

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

    @Override
    public String toString() {
        return "mouse selection @ " + x + "/" + y + " field='" + (field == null ? "" : field.getIndex()) + "', edge='" + edge + "', node='" + node + "'.";
    }
}
