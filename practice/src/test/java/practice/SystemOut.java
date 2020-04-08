package practice;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SystemOut {

    @Test
    public void testBufferOut() throws IOException, InterruptedException {
//        BufferedWriter bf= new BufferedWriter();
        File file = new File("test.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(0);
        randomAccessFile.write("hello world!".getBytes());
        Thread.sleep(2000);
        randomAccessFile.seek(0);
        randomAccessFile.write("hi world!           ".getBytes());

    }
}
