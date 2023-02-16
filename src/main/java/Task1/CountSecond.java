package Task1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class CountSecond {
    private int n = 20;
    private Semaphore lock = new Semaphore(1);
    private AtomicInteger counter = new AtomicInteger(1);
    public void number(IntConsumer printNumber) throws InterruptedException {
        int step = n - n/5;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 5 != 0) {
                printNumber.accept(counter.get());
                counter.incrementAndGet();
                i++;
            }
            lock.release();
            Thread.sleep(1000);
        }
    }

    public void fiveSeconds(Runnable printFive) throws InterruptedException {
        int step = n/5;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 5 == 0) {
                printFive.run();
                counter.incrementAndGet();
                i++;
            }
            lock.release();
            Thread.sleep(1000);
        }
    }
}
