import renderer.Output;
import renderer.Renderer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Renderer renderer = new Renderer();
        Output output = new Output(renderer);
        threadPool.execute(output);

        System.out.println("Started");
    }
}
