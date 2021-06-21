package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapEdge;
import com.github.martinfrank.endlessbuilding.map.MapField;
import com.github.martinfrank.endlessbuilding.map.MapNode;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CivMapCanvas extends Canvas {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(CivMapCanvas.class);

    private Map map;

    public void setMap(Map map) {
        this.map = map;
        int h = (int) map.getTransformed().getHeight();
        int w = (int) map.getTransformed().getWidth();
        setHeight(h);
        setWidth(w);
        Shape aField = map.getFields().get(0).getShape();
        double fieldHeight = aField.getTransformed().getHeight();
        setWidth(map.getTransformed().getWidth());
        if (map.getRows() % 2 == 1) {
            setHeight(map.getTransformed().getHeight() - fieldHeight * 1.5);
        } else {
            setHeight(map.getTransformed().getHeight());
        }
        drawMap();
    }

    void drawMap() {
        if (map != null) {
            map.getFields().forEach(this::drawField);
        }
    }

    private void drawNode(MapNode node) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        Point point = node.getPoint().getTransformed();
        gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
    }

    private void drawEdge(MapEdge edge) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);

        Point a = edge.getLine().getTransformed().getA();
        Point b = edge.getLine().getTransformed().getB();
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private void drawField(MapField field) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.GRAY);
//        Player owner = field.getData().getOwner();
//        if (owner != null) {
//            gc.setFill(fromArgb(owner.getColor()));
//        }
        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(1);

        Shape shape = field.getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = xs.length;
        gc.fillPolygon(xs, ys, amount);

        field.getEdges().forEach(this::drawEdge);
        field.getNodes().forEach(this::drawNode);


    }


    public MouseSelection getSelectionAt(int x, int y) {
        MouseSelection selection = new MouseSelection(x, y);
        if (map != null) {
            selection.setNode(map.getNodeAt(x, y));
            selection.setEdge(map.getEdgeAt(x, y));
            selection.setField(map.getFieldAt(x, y));
        }
        return selection;
    }

    private static Color fromArgb(int argb) {
        LOGGER.debug("argb:{}, hex:{}", argb, Integer.toHexString(argb));
        double a = Math.abs((argb & 0xFF000000) / 4278190080d); //FIXME unprecise
        double r = (argb & 0x00FF0000) / 16711680d;
        double g = (argb & 0x0000FF00) / 65280d;
        double b = (argb & 0x000000FF) / 255d;
        LOGGER.debug("a {}", a);
        LOGGER.debug("r {}", r);
        LOGGER.debug("g {}", g);
        LOGGER.debug("b {}", b);
        return new Color(r, g, b, a);
    }

}
