package Task2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class BuzzFizz {

    private int n;
    private Semaphore lock;
    private AtomicInteger counter;

    public BuzzFizz(int n) {
        this.n = n;
        this.lock = new Semaphore(1);
        this.counter = new AtomicInteger(1);
    }

    // printFizz.run()
    public void fizz(Runnable printFizz) throws InterruptedException {
        int step = n/3 - n/15;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 3 == 0 && counter.get() % 15 != 0) {
                printFizz.run();
                counter.incrementAndGet();
                i++;
            }
            lock.release();
        }
    }

    // printBuzz.run()
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int step = n/5 - n/15;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 5 == 0 && counter.get() % 15 != 0) {
                printBuzz.run();
                counter.incrementAndGet();
                i++;
            }
            lock.release();
        }
    }

    // printFizzBuzz.run()
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int step = n/15;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 15 == 0) {
                printFizzBuzz.run();
                counter.incrementAndGet();
                i++;
            }
            lock.release();
        }
    }

    // printNumber.accept(x)
    public void number(IntConsumer printNumber) throws InterruptedException {
        int step = n - n/3 - n/5 + n/15;
        int i = 0;
        while (i < step) {
            lock.acquire();
            if (counter.get() % 3 != 0 && counter.get() % 5 != 0) {
                printNumber.accept(counter.get());
                counter.incrementAndGet();
                i++;
            }
            lock.release();
        }
    }
}
