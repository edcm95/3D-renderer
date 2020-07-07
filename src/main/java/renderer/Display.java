package renderer;

import config.Configuration;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display implements Runnable {

    private Canvas canvas;
    private JFrame frame;
    private final String title = "3D Renderer";
    private boolean running = false;

    //speed control
    private long timeStamp;
    private long delta;
    private int frameCount;
    private long frameUpdateStamp;

    // renderer
    private Renderer renderer;



    public Display() {
        this.canvas = new Canvas();
        this.renderer = new Renderer();
        this.delta = 1000 / Configuration.DESIRED_FPS;
        this.frame = new JFrame();
        this.frameUpdateStamp = System.currentTimeMillis();
        init();
    }

    private void init() {
        Dimension size = new Dimension(Configuration.WIDTH, Configuration.HEIGHT);
        canvas.setPreferredSize(size);

        //frame
        frame.setTitle(title);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void run() {
        running = true;
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
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        renderer.render(g);

        bs.show();
    }

    private void update() {
        renderer.update();
    }
}
