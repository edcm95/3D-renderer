package renderer.shapes;

import renderer.entities.SpatialPoint;
import renderer.entities.SpatialPolygon;

import java.awt.*;

public class Tetrahedron extends Shape {


    public Tetrahedron(int size, SpatialPoint origin) {
        this.polygons = new SpatialPolygon[6];
        setupPolygons(size, origin);
    }

    protected void setupPolygons(int size, SpatialPoint origin) {
        SpatialPoint sp1 = new SpatialPoint((size >> 1) + origin.x, (-size >> 1) + origin.y, (-size >> 1) + origin.z);
        SpatialPoint sp2 = new SpatialPoint((size >> 1) + origin.x, (size >> 1) + origin.y, (-size >> 1) + origin.z);
        SpatialPoint sp3 = new SpatialPoint((size >> 1) + origin.x, (size >> 1) + origin.y, (size >> 1) + origin.z);
        SpatialPoint sp4 = new SpatialPoint((size >> 1) + origin.x, (-size >> 1) + origin.y, (size >> 1) + origin.z);
        SpatialPoint sp5 = new SpatialPoint((-size >> 1) + origin.x, (-size >> 1) + origin.y, (-size >> 1) + origin.z);
        SpatialPoint sp6 = new SpatialPoint((-size >> 1) + origin.x, (size >> 1) + origin.y, (-size >> 1) + origin.z);
        SpatialPoint sp7 = new SpatialPoint((-size >> 1) + origin.x, (size >> 1) + origin.y, (size >> 1) + origin.z);
        SpatialPoint sp8 = new SpatialPoint((-size >> 1) + origin.x, (-size >> 1) + origin.y, (size >> 1) + origin.z);

        polygons[0] = new SpatialPolygon(Color.MAGENTA, sp1, sp2, sp3, sp4);
        polygons[1] = new SpatialPolygon(Color.GREEN, sp5, sp6, sp7, sp8);
        polygons[2] = new SpatialPolygon(Color.CYAN, sp1, sp2, sp6, sp5);
        polygons[3] = new SpatialPolygon(Color.YELLOW, sp1, sp5, sp8, sp4);
        polygons[4] = new SpatialPolygon(Color.WHITE, sp2, sp6, sp7, sp3);
        polygons[5] = new SpatialPolygon(Color.RED, sp4, sp3, sp7, sp8);
    }
}
