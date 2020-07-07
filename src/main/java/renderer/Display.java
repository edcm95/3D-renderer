package renderer;

import config.Constants;
import renderer.entity.Renderable;
import renderer.entity.SpatialPoint;
import renderer.shapes.Tetrahedron;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends Canvas implements Runnable {

    private Thread thread;
    private JFrame frame;
    private final String title = "3D Renderer";
    private boolean running = false;

    //speed related
    private long timeStamp;
    private final long delta = 17;

    private List<Renderable> renderableList;

    private int frameCount;
    private long frameUpdateStamp;

    public Display() {
        this.renderableList = new ArrayList<>();
        this.frame = new JFrame();
        this.frameUpdateStamp = System.currentTimeMillis();

        init();
    }


    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() throws InterruptedException {
        running = false;
        this.thread.join();
    }

    private void init() {
        Dimension size = new Dimension(Constants.WIDTH, Constants.HEIGHT);
        this.setPreferredSize(size);

        //frame
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // add renderables
        renderableList.add(
            new Tetrahedron(Color.YELLOW, 200,new SpatialPoint(0, 0, 0))
        );

    }

    public void run() {

        while (running) {
            timeStamp = System.currentTimeMillis();

            render();
            update();

            speedControl();
        }

    }

    private void speedControl() {
        // fps management
        frameCount++;
        if (System.currentTimeMillis() - frameUpdateStamp >= 1000) {
            frame.setTitle(title + " | " + frameCount + " fps");
            frameCount = 0;
            frameUpdateStamp = System.currentTimeMillis();
        }

        // speed control
        long value = delta - (System.currentTimeMillis() - timeStamp);
        if (value <= 0) {
            return;
        }

        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // model graphics output
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        for (Renderable renderable : renderableList) {
            renderable.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void update() {
        renderableList.get(0).rotate(true, 1, 1, 1);
    }
}
