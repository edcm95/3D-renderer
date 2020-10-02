package renderer;

import config.Configuration;
import renderer.entities.Renderable;
import renderer.entities.SpatialPoint;
import renderer.shapes.Tetrahedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private List<Renderable> renderableList;

    public Renderer() {
        this.renderableList = new ArrayList<>();
        init();
    }

    private void init() {
        // add renderables
        renderableList.add(
                new Tetrahedron(200, new SpatialPoint(200, 200, 200))
        );

        renderableList.add(
                new Tetrahedron(200, new SpatialPoint(-200, -200, -200))
        );

        renderableList.add(
                new Tetrahedron(100, new SpatialPoint(0, 0, 0))
        );
    }

    public void render(Graphics g) {
        // model graphics output
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Configuration.WIDTH, Configuration.HEIGHT);

        for (Renderable renderable : renderableList) {
            renderable.render(g);
        }

        g.dispose();
    }

    public void update() {
        renderableList.parallelStream().forEach(
                (renderable) -> renderable.rotate(false, 1, 1, 1)
        );
    }
}
