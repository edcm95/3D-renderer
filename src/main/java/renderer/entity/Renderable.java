package renderer.entity;

import java.awt.*;

public interface Renderable {

    void render(Graphics g);

    void rotate(boolean cw, double xDeg, double yDeg, double zDeg);
}
