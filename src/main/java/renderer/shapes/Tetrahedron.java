package renderer.shapes;

import renderer.entity.Renderable;
import renderer.entity.SpatialPoint;
import renderer.entity.SpatialPolygon;

import java.awt.*;

public class Tetrahedron implements Renderable {

    private SpatialPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color, int size, SpatialPoint origin) {
        this.polygons = new SpatialPolygon[6];
        this.color = color;
        setupPolygons(size, origin);
    }

    private void setupPolygons(int size, SpatialPoint origin) {
        if (color == null) {
            return;
        }

        SpatialPoint sp1 = new SpatialPoint(size >> 1, -size >> 1, -size >> 1);
        SpatialPoint sp2 = new SpatialPoint(size >> 1, size >> 1, -size >> 1);
        SpatialPoint sp3 = new SpatialPoint(size >> 1, size >> 1, size >> 1);
        SpatialPoint sp4 = new SpatialPoint(size >> 1, -size >> 1, size >> 1);
        SpatialPoint sp5 = new SpatialPoint(-size >> 1, -size >> 1, -size >> 1);
        SpatialPoint sp6 = new SpatialPoint(-size >> 1, size >> 1, -size >> 1);
        SpatialPoint sp7 = new SpatialPoint(-size >> 1, size >> 1, size >> 1);
        SpatialPoint sp8 = new SpatialPoint(-size >> 1, -size >> 1, size >> 1);

        polygons[0] = new SpatialPolygon(Color.MAGENTA, sp1, sp2, sp3, sp4);
        polygons[1] = new SpatialPolygon(Color.GREEN, sp5, sp6, sp7, sp8);
        polygons[2] = new SpatialPolygon(Color.CYAN, sp1, sp2, sp6, sp5);
        polygons[3] = new SpatialPolygon(Color.YELLOW, sp1, sp5, sp8, sp4);
        polygons[4] = new SpatialPolygon(Color.WHITE, sp2, sp6, sp7, sp3);
        polygons[5] = new SpatialPolygon(Color.BLUE, sp4, sp3, sp7, sp8);
    }

    public void rotate(boolean cw, double xDeg, double yDeg, double zDeg) {
        for (SpatialPolygon sp : polygons) {
            sp.rotate(cw, xDeg, yDeg, zDeg);
        }
        sortPolygons();
    }


    @Override
    public void render(Graphics g) {
        for (SpatialPolygon sp : polygons) {
            sp.render(g);
        }
    }

    public void sortPolygons() {

    }
}
