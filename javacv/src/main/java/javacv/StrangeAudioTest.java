package javacv;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StrangeAudioTest {
    public static void main(String[] args) throws FrameGrabber.Exception, FileNotFoundException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(new FileInputStream("javacv/data/2576114892619882502_0.m4a"));
        grabber.start();
        System.out.println(grabber.getFormat());
        grabber.stop();
        grabber.release();
    }
}
