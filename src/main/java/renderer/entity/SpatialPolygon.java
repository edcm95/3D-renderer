package renderer.entity;

import renderer.util.PointManager;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

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
            Point p = PointManager.get2DPointFromSpatialPoint(point);
            polygon2D.addPoint(p.x, p.y);
        }

        g.setColor(color);
        g.fillPolygon(polygon2D);
    }

    public void rotate(boolean cw, double xDeg, double yDeg, double zDeg) {

        Arrays.asList(points).parallelStream().forEach((point) -> {
            PointManager.rotateX(point, cw, xDeg);
            PointManager.rotateY(point, cw, yDeg);
            PointManager.rotateZ(point, cw, zDeg);
        });
    }


    public static SpatialPolygon[] sortPolygons(SpatialPolygon[] input) {
        List<SpatialPolygon> list = Arrays.asList(input);
        list.sort((t0, t1) -> (int) Math.round(t0.getMaximumX() - t1.getMaximumX()));
        return list.toArray(new SpatialPolygon[input.length]);
    }

    private double getMaximumX() {
        double maxX = points[0].x;
        for (SpatialPoint point : points) {
            if (point.x > maxX) {
                maxX = point.x;
            }
        }
        return maxX;
    }
}
