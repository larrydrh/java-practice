package unittest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    public static void main(String[] args) {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        CountDownLatch countDownLatch;
        Semaphore semaphore;
    }
}
