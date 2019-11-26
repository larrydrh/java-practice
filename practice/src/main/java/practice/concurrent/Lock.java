package practice.concurrent;

import java.util.LinkedHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Semaphore semaphore = new Semaphore(2);
        LinkedBlockingDeque<String> linkedBlockingDeque =  new LinkedBlockingDeque<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        CountDownLatch countDownLatch =  new CountDownLatch(2);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        Condition condition = reentrantLock.newCondition();

    }
}
