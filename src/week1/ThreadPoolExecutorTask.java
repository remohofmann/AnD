package week1;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTask<OutputType> implements Runnable {

    List<OutputType> intermediateResults;
    DivideAndConquerableThreads<OutputType> subcomponent;
    ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolExecutorTask(List<OutputType> intermediateResults,
                                  DivideAndConquerableThreads subcomponent,
                                  ThreadPoolExecutor threadPoolExecutor) {
        this.intermediateResults = intermediateResults;
        this.subcomponent = subcomponent;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void run() {
        intermediateResults.add(subcomponent.divideAndConquer(threadPoolExecutor));
    }
}
