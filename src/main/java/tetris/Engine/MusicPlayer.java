package main.java.tetris.Engine;

import java.io.BufferedInputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
    private Clip clip;
    private boolean muted = false;
    private boolean paused = false;

    public void play(String fileName) {
        try {
            AudioInputStream in = null;

            URL url = MusicPlayer.class.getResource("/resources/" + fileName);
            if (url != null) {
                in = AudioSystem.getAudioInputStream(new BufferedInputStream(url.openStream()));
            } else {
                String[] paths = {"src/resources/" + fileName, "resources/" + fileName, "music/" + fileName};
                for (String p : paths) {
                    java.io.File f = new java.io.File(p);
                    if (f.exists()) {
                        in = AudioSystem.getAudioInputStream(f);
                        break;
                    }
                }
            }

            if (in == null) {
                System.out.println("Music not found: " + fileName);
                return;
            }

            clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Could not play music: " + e);
        }
    }

    public void toggleMute() {
        muted = !muted;
        updatePlayback();
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        updatePlayback();
    }

    private void updatePlayback() {
        if (clip == null) {
            return;
        }
        if (muted || paused) {
            clip.stop();
        } else if (!clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}