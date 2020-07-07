package renderer.entity;

import renderer.util.PointConverter;

import java.awt.*;

public class SpatialPolygon implements Renderable {

    private SpatialPoint[] points;
    private Color color;

    public SpatialPolygon(Color color, SpatialPoint... points) {
        this.color = color;
        this.points = new SpatialPoint[points.length];

        for (int i = 0; i < points.length; i++) {
            this.points[i] = new SpatialPoint(
                    points[i].x,
                    points[i].y,
                    points[i].z
            );
        }
    }

    public void render(Graphics g) {
        Polygon polygon2D = new Polygon();

        for (SpatialPoint point : this.points) {
            Point p = PointConverter.get2DPointFromSpatialPoint(point);
            polygon2D.addPoint(p.x, p.y);
        }

        g.setColor(color);
        g.fillPolygon(polygon2D);
    }

    public void rotate(boolean cw, double xDeg, double yDeg, double zDeg) {
        for(SpatialPoint sp : points) {
            PointConverter.rotateX(sp, cw, xDeg);
            PointConverter.rotateY(sp, cw, yDeg);
            PointConverter.rotateZ(sp, cw, zDeg);
        }
    }
}
