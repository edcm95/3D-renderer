import renderer.Display;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Display display = new Display();
        threadPool.execute(display);

        System.out.println("Started");
    }
}
