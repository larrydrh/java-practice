package mediainfo;

import mediainfo.lib.MediaInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class MediaInfoDemo {
//    public static MediaInfo mediaInfo = new MediaInfo();
    public static void parseMediaInfo(Stream<Path> pathStream) {
        MediaInfo mediaInfo = new MediaInfo();
        pathStream.forEach(
                path -> {
//                    synchronized (mediaInfo) {
                        try {
                            mediaInfo.open(new File(path.toUri()));
                            if (Files.isDirectory(path)) {
                                System.out.println(path.toString());
                                return;
                            }

                            Map<MediaInfo.StreamKind, List<Map<String, String>>> snapShot = mediaInfo.snapshot();

//                    System.out.println(snapShot.toString());
                            try {
                                int audioLength = Integer.valueOf(snapShot.get(MediaInfo.StreamKind.Audio).get(0).get("Duration"));
                                int sampleRate = Integer.valueOf(snapShot.get(MediaInfo.StreamKind.Audio).get(0).get("SamplingRate"));
//                        System.out.println( path.toString() + ": "+ audioLength + " " + sampleRate);
                            } catch (NullPointerException nullPointerException) {
                                System.out.println(path.toString());
                            }
                            mediaInfo.close();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
//                    mediaInfo.dispose();
//                    }

                });
        System.out.println("finished");
    }
    public static void walkThroughAudio() throws IOException {
        Stream<Path> pathStream1 =  Files.walk(Paths.get("/home/rhding/Documents/lizhishort0526/"));
        Stream<Path> pathStream2 =  Files.walk(Paths.get("/home/rhding/Documents/lizhishort0526/"));
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(()-> parseMediaInfo(pathStream1));
        executor.execute(()-> parseMediaInfo(pathStream2));

//        executor.awaitTermination(1, TimeUnit.MINUTES);
        executor.shutdown();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        String audioFile = "mediainfo/data/AT0021W0342.wav";
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.open(new File(audioFile));
        System.out.println(mediaInfo.streamCount(MediaInfo.StreamKind.Audio));
        System.out.println(mediaInfo.get(MediaInfo.StreamKind.Audio, 0, "Duration"));

    }
}
