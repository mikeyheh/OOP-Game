package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;

public class AudioManager {
    private Clip currentClip;
    private FloatControl volumeControl;

    public AudioManager() {
    }

    public void playMusic(String path) {
        stopCurrentMusic();
        try {
            InputStream audioInput = getClass().getResourceAsStream(path);
            if (audioInput != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioInput);
                currentClip = AudioSystem.getClip();
                currentClip.open(audioStream);

                volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);

                currentClip.start();
                currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopCurrentMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            // Convert volume (0.0 to 1.0) to decibels (-80.0 to 6.0)
            float min = volumeControl.getMinimum(); // Usually -80.0 dB
            float max = volumeControl.getMaximum(); // Usually 6.0 dB
            float newVolume = min + (volume * (max - min)); // Linear interpolation
            volumeControl.setValue(newVolume);
        } else {
            System.out.println("Volume control not available.");
        }
    }
}