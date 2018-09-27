package week1;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

public interface DivideAndConquerableThreads<OutputType> extends DivideAndConquerable<OutputType>, Callable<OutputType> {


    // DEFAULT divideAndConquer method which returns a type of 'OutputType'
    default OutputType divideAndConquer(ThreadPoolExecutor threadPoolExecutor) throws ExecutionException, InterruptedException {
        if (this.isBasic()) {
            return this.baseFunction();
        }
        List<? extends DivideAndConquerableThreads<OutputType>> subcomponents = (List<? extends DivideAndConquerableThreads<OutputType>>) this.decompose();
        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

        // TODO: threadpool comes here -> for each 'add' there should be a thread!
        /** 1. assign each subcomponent.divideAndConquer() a thread. (or each add(subcomponent.divNC())?)
         * 2. is threadPoolSize is reached, stop creating new threads,
         * 3. switch to sequential processing! */
        if (threadPoolExecutor.getPoolSize() < threadPoolExecutor.getMaximumPoolSize() - 1) {
            final FutureTask<OutputType> futureTask1;
            final FutureTask<OutputType> futureTask2;
            subcomponents.forEach(subcomponent -> {
                // TODO: Callable & FutureTask!!!!
                try {
                    futureTask1 = new FutureTask<>(subcomponent);
                    futureTask2 = new FutureTask<>(subcomponent);
                    threadPoolExecutor.execute(futureTask1);
                    threadPoolExecutor.execute(futureTask2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // TODO : check if this is correctly executed for every case!
            if (futureTask1.isDone()) intermediateResults.add(futureTask1.get());
            if (futureTask2.isDone()) intermediateResults.add(futureTask2.get());
        } else {
            subcomponents.forEach(subcomponent -> {
                intermediateResults.add(subcomponent.divideAndConquer());
            });
        }
        return recombine(intermediateResults);
    }

}