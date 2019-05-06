package edu.nyu.algo2.thread.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementer {

    private static final int nThreads = 2;

    private final AtomicInteger count;

    private static final AtomicInteger lastPrinted = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        Incrementer incrementer = new Incrementer();
        for (int i = 0; i < nThreads; ++i) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    process(this, incrementer, executorService);
                }
            });
        }
    }

    // Hint: might need some extra work here.
    private static void process(final Runnable runnable, Incrementer incrementer, ExecutorService executorService) {
        if (incrementer.getAndIncrement() != lastPrinted.getAndIncrement()) {
            System.err.printf("error at %d%n", lastPrinted.get());
            executorService.shutdownNow();
        } else {
            System.out.println(lastPrinted);
        }
        executorService.submit(runnable);
    }

    public Incrementer() {
        this.count = new AtomicInteger(0);
    }

    public int getAndIncrement() {
        return count.getAndIncrement();
    }

}

