package renderer.util;

import config.Configuration;
import renderer.entities.SpatialPoint;
import java.awt.*;

public class PointManager {

    public static Point get2DPointFromSpatialPoint(SpatialPoint sp) {
        double x3d = sp.y * Configuration.SCALE;
        double y3d = sp.z * Configuration.SCALE;
        double depth = sp.x * Configuration.SCALE;
        double[] newVal = scale(x3d, y3d, depth);

        int x2d = (int) ((Configuration.WIDTH >> 1) + newVal[0]);
        int y2d = (int) ((Configuration.HEIGHT >> 1) - newVal[1]);

        return new Point(x2d, y2d);
    }

    public static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt(x3d * x3d + y3d * y3d);
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 20 - depth;
        double localScale = Math.abs(Configuration.LOCAL_SCALE_FACTOR / (depth2 + Configuration.LOCAL_SCALE_FACTOR));
        dist *= localScale;

        double[] values = new double[2];
        values[0] = dist * Math.cos(theta);
        values[1] = dist * Math.sin(theta);

        return values;
    }

    public static void rotateX(SpatialPoint point, boolean clockwise, double degrees) {
        double radius = Math.sqrt(point.y * point.y + point.z * point.z);
        double theta = Math.atan2(point.z, point.y);
        theta += 2 * Math.PI / 360 * degrees * (clockwise ? -1 : 1);

        point.y = radius * Math.cos(theta);
        point.z = radius * Math.sin(theta);
    }

    public static void rotateY(SpatialPoint point, boolean clockwise, double degrees) {
        double radius = Math.sqrt(point.x * point.x + point.z * point.z);
        double theta = Math.atan2(point.x, point.z);
        theta += 2 * Math.PI / 360 * degrees * (clockwise ? -1 : 1);

        point.x = radius * Math.sin(theta);
        point.z = radius * Math.cos(theta);
    }

    public static void rotateZ(SpatialPoint point, boolean clockwise, double degrees) {
        double radius = Math.sqrt(point.y * point.y + point.x * point.x);
        double theta = Math.atan2(point.y, point.x);
        theta += 2 * Math.PI / 360 * degrees * (clockwise ? -1 : 1);

        point.y = radius * Math.sin(theta);
        point.x = radius * Math.cos(theta);
    }
}
