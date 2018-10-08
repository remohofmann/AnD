package week1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

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
            final FutureTask<OutputType>[] futureTasksArray = new FutureTask[subcomponents.size()];
            subcomponents.forEach(subcomponent -> {
                // TODO: Callable & FutureTask!!!!
                try {
                    FutureTask<OutputType> futureTask = new FutureTask<>(subcomponent);
                    for (int i = 0; i < subcomponents.size(); i++) {
                        futureTasksArray[i] = futureTask;
                        threadPoolExecutor.execute(futureTask);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // TODO : check if this is correctly executed for every case!
            for (FutureTask<OutputType> futureTask : futureTasksArray)
                try {
                    intermediateResults.add(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

        } else {
            subcomponents.forEach(subcomponent -> {
                intermediateResults.add(subcomponent.divideAndConquer());
            });
        }
        return recombine(intermediateResults);
    }

}