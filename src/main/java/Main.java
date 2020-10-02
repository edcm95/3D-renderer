import renderer.Output;
import renderer.Renderer;
import renderer.util.MusicPlayer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        Renderer renderer = new Renderer();
        Output output = new Output(renderer);

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(output);
        threadPool.execute(musicPlayer);

        System.out.println("Started");
    }
}
