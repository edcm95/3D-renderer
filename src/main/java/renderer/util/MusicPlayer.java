package renderer.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer implements Runnable {

    @Override
    public void run() {
        System.out.println("Music player initiated.");

        InputStream is = null;
        try {

            is = this.getClass().getClassLoader().getResourceAsStream("music.wav");

            if (is == null) {
                System.out.println("Unable to open audio input stream.");
                return;
            }

            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (Exception ex) {
            System.out.println("Sound error.");
            ex.printStackTrace();

        } finally {
            if (is != null) {
                try {
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

