package practice.concurrent;

import java.util.concurrent.LinkedBlockingDeque;

public class Rmap {
    public static void main(String[] args) {
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque(1);
        linkedBlockingDeque.add(1);
        System.out.println("added  one member, add another mem");
        linkedBlockingDeque.push(2);
    }
}
