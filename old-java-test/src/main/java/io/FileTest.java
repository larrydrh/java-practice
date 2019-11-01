package io;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws Exception {
        File path = new File("test_file.txt");
        if (path.canWrite())
            System.out.println("can write");
//        OutputStream outStream = new FileOutputStream(path);
//        outStream.write("hello world\n".getBytes());
//        outStream.close();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        System.out.println(bufferedReader.readLine());
    }
}
