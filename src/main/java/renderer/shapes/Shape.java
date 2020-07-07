package renderer.shapes;


import renderer.entities.Renderable;
import renderer.entities.SpatialPoint;
import renderer.entities.SpatialPolygon;
import java.awt.*;

public abstract class Shape implements Renderable {

    protected SpatialPolygon[] polygons;

    @Override
    public void render(Graphics g) {
        for (SpatialPolygon sp : polygons) {
            sp.render(g);
        }
    }

    protected abstract void setupPolygons(int size, SpatialPoint origin);

    @Override
    public void rotate(boolean cw, double xDeg, double yDeg, double zDeg) {
        for (SpatialPolygon sp : polygons) {
            sp.rotate(cw, xDeg, yDeg, zDeg);
        }
        sortPolygons();
    }

    protected void sortPolygons() {
        polygons = SpatialPolygon.sortPolygons(polygons);
    }
}
