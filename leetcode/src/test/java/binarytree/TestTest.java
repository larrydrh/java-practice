package binarytree;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestTest {

@Test
    public void testUtil() {
    int[][] envelopes = new int[][] {{5,4},{6,4},{6,7},{2,3}};
    Arrays.sort(envelopes, (t1, t2) -> {
        if (t2[0] == t1[0]) {
            return t1[1] - t2[1];
        } else {
            return t1[0] - t2[0];
        }
    });


}

}