package week1;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public interface DivideAndConquerableThreads<OutputType> extends Runnable {

    boolean isBasic();
    int getMaxThreads();
    OutputType baseFunction();

    List<? extends DivideAndConquerableThreads<OutputType>> decompose();

    OutputType recombine(List<OutputType> intermediateResults);

    default List<? extends DivideAndConquerableThreads<OutputType>> stump() {
        return new ArrayList<DivideAndConquerableThreads<OutputType>>(0);
    }
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    // DEFAULT divideAndConquer method which returns a type of 'OutputType'
    default OutputType divideAndConquer(ThreadPoolExecutor threadPoolExecutor) {
        if (this.isBasic()) {
            return this.baseFunction();
        }
        List<? extends DivideAndConquerableThreads<OutputType>> subcomponents = this.decompose();
        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
        // TODO: threadpool comes here -> for each 'add' there should be a thread!
        /** 1. assign each subcomponent.divideAndConquer() a thread. (or each add(subcomponent.divNC())?)
         * 2. is threadPoolSize is reached, stop creating new threads,
         * 3. switch to sequential processing! */
        subcomponents.forEach(subcomponent -> {
//            TODO: check if there is still an available thread...
//            if (threadPoolExecutor.getPoolSize() < threadPoolExecutor.getMaximumPoolSize()) {
            if (executor.getPoolSize() < executor.getMaximumPoolSize()) {
                executor.submit(() -> {
                    intermediateResults.add(subcomponent.divideAndConquer(threadPoolExecutor));
                    return null;
                });
//                ThreadPoolExecutorTask threadPoolExecutorTask = new ThreadPoolExecutorTask(intermediateResults, subcomponent, threadPoolExecutor);
//                threadPoolExecutor.execute(threadPoolExecutorTask);
            }
            else intermediateResults.add(subcomponent.divideAndConquer(threadPoolExecutor));
        });
        return recombine(intermediateResults);
    }

}