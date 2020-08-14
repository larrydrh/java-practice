package javacv;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_MP3;
import static org.bytedeco.flycapture.global.FlyCapture2.FRAME_RATE;

@Slf4j
public class JavaCVTest {

    /* 可以录音为MP3格式音频*/
    public static void testAduioByJavaCV() throws FrameRecorder.Exception, InterruptedException {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("./testAudio.mp3", 1);
        recorder.setAudioOption("crf", "0");
        // Highest quality
        recorder.setAudioQuality(0);
        // 16 Kbps
        recorder.setAudioBitrate(16000);
        // 44.1MHZ
        recorder.setSampleRate(44100);
        // 1 channel
        recorder.setAudioChannels(1);
        // mp3
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3);
        recorder.start();

        AudioFormat audioFormat = new AudioFormat(44100.0F, 16, 1, true, false);

        // 得到所有Mixer信息，通俗的说就是声音设备信息
        Mixer.Info[] minfoSet = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(minfoSet[3]);
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        try {
            //初始化TargeLine，与使用JDK一样

            // TargetDataLine line = (TargetDataLine)mixer.getLine(dataLineInfo); //可以使用声音设备索引来录制音频
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);//这个就是查找默认可用的录音设备，没有特殊指定
            line.open(audioFormat);
            line.start();

            int sampleRate = (int) audioFormat.getSampleRate();
            int numChannels = audioFormat.getChannels();

            // Let's initialize our audio buffer...
            int audioBufferSize = sampleRate * numChannels;
            byte[] audioBytes = new byte[audioBufferSize];

            // Using a ScheduledThreadPoolExecutor vs a while loop with
            // a Thread.sleep will allow
            // us to get around some OS specific timing issues, and keep
            // to a more precise
            // clock as the fixed rate accounts for garbage collection
            // time, etc
            // a similar approach could be used for the webcam capture
            // as well, if you wish
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

            exec.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Read from the line... non-blocking
                        int nBytesRead = 0;
                        while (nBytesRead == 0) {
                            nBytesRead = line.read(audioBytes, 0, line.available());
                        }

                        // Since we specified 16 bits in the AudioFormat,
                        // we need to convert our read byte[] to short[]
                        // (see source from FFmpegFrameRecorder.recordSamples for AV_SAMPLE_FMT_S16)
                        // Let's initialize our short[] array
                        int nSamplesRead = nBytesRead / 2;
                        short[] samples = new short[nSamplesRead];

                        // Let's wrap our short[] into a ShortBuffer and
                        // pass it to recordSamples
                        ByteBuffer.wrap(audioBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(samples);
                        ShortBuffer sBuff = ShortBuffer.wrap(samples, 0, nSamplesRead);

                        // recorder is instance of
                        // org.bytedeco.javacv.FFmpegFrameRecorder
                        recorder.recordSamples(sampleRate, numChannels, sBuff);
                        log.info("recorder samples size: {}", nSamplesRead);
                    } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, (long) 1000 / FRAME_RATE, TimeUnit.MILLISECONDS);
            Thread.sleep(20000);
            recorder.stop();
            recorder.release();
            exec.shutdown();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }

        //仅用于测试，有点低级，仅测试功能，实际项目中需要通过标志来控制线程
//        for (;;){}
    }

    /*可以将raw格式的音频转换为MP3格式 */
    public static void testTranslateAudioFormat() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(byteArrayOutputStream,1);
        recorder.setAudioCodec(AV_CODEC_ID_MP3);
        recorder.setFormat("s16le");
        recorder.setSampleRate(16000);
        byte[] audioBytes = Files.readAllBytes(Paths.get("javacv/data/record1.raw"));
//        FFmpegFrameGrabber grabber2 = new FFmpegFrameGrabber("javacv/data/record1.raw");
//        grabber2.start();
        short[] samples = new short[audioBytes.length/2];
        ByteBuffer.wrap(audioBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(samples);
        ShortBuffer sBuff = ShortBuffer.wrap(samples, 0, audioBytes.length/2);

        try {
            recorder.start();
            recorder.recordSamples(16000, 1, sBuff);
            recorder.stop();
            recorder.release();
        } catch (FrameRecorder.Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Files.write(Paths.get("myFile.mp3"), byteArrayOutputStream.toByteArray());
    }
    public static void main(String[] args) throws IOException, InterruptedException {
//        testAduioByJavaCV();
        testTranslateAudioFormat();
    }
}
