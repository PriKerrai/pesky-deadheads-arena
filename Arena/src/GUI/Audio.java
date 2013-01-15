package GUI;


import java.io.*;
import sun.audio.*;

//Author Johan
public class Audio implements Runnable
{
    AudioStream audioStream;
    public void run()
    {           
        try{
            String musicFile = "music/Arena.wav";
            InputStream in = new FileInputStream(musicFile);
            audioStream = new AudioStream(in);
        }catch (IOException e){
        
        }
        while(!Thread.interrupted())
        {
            AudioPlayer.player.start(audioStream);
            try {
                AudioPlayer.player.join();
            } catch (InterruptedException e)
            {
                break;
            }
        }
        AudioPlayer.player.stop(audioStream);   
    }
}