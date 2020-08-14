package practice.file;

import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class HotWordConvert {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/rhding/projects/webRealTime/words.csv");
        List<String> fileString =  Files.readLines(file, Charset.defaultCharset());
        File result = new File("result.txt");
        FileWriter writer  = new FileWriter(result);
        int j = 0;
        for(String i:fileString) {
            writer.write(i);
            writer.write(" | ");
            j++;
            if (j%10 == 0) {
                writer.write('\n');
            }
        }
        writer.close();

    }
}
