package week1;

import sun.jvm.hotspot.oops.OopUtilities;

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
            final List<FutureTask<OutputType>> futureTasksList = new ArrayList<>(subcomponents.size());
            subcomponents.forEach(subcomponent -> {
                // TODO: Callable & FutureTask!!!!
                try {
                    FutureTask<OutputType> futureTask = new FutureTask<>(subcomponent);
                    for (int i = 0; i < subcomponents.size(); i++) {
                        futureTasksList.add(futureTask);
                        threadPoolExecutor.execute(futureTask);
//                        System.out.println(threadPoolExecutor.getPoolSize() + ": " + threadPoolExecutor.getMaximumPoolSize());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // adds the result of the thread calculation to the intermediateResults.
            for (FutureTask<OutputType> futureTask : futureTasksList)
                try {
                    intermediateResults.add(futureTask.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

        } else {
            subcomponents.forEach(subcomponent -> {
                intermediateResults.add(subcomponent.divideAndConquer());
//                System.out.println("outside threads...");
            });
        }
        return recombine(intermediateResults);
    }

}