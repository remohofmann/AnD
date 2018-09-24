package week1;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutorTask<OutputType> implements Runnable {

    List<OutputType> intermediateResults;
    DivideAndConquerableThreads<OutputType> subcomponent;
    ThreadPoolExecutor threadPoolExecutor;
    private static Lock lock = new ReentrantLock();

    public ThreadPoolExecutorTask(List<OutputType> intermediateResults,
                                  DivideAndConquerableThreads<OutputType> subcomponent,
                                  ThreadPoolExecutor threadPoolExecutor) {
        this.intermediateResults = intermediateResults;
        this.subcomponent = subcomponent;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void run() {
        lock.lock();
        intermediateResults.add(subcomponent.divideAndConquer(threadPoolExecutor));
        lock.unlock();
    }
}
