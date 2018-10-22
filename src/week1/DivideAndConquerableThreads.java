package week1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public interface DivideAndConquerableThreads<OutputType> extends DivideAndConquerable<OutputType> {


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
            List<Future<OutputType>> futures = new ArrayList<>();
            subcomponents.forEach(subcomponent -> {
                try {
                    Callable<OutputType> concurrentCode = () -> subcomponent.divideAndConquer(threadPoolExecutor);
                    Future<OutputType> future = threadPoolExecutor.submit(concurrentCode);
                    futures.add(future);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


            // TODO : check if this is correctly executed for every case!
            for (Future<OutputType> future : futures)
                try {
                    // TODO: instead of waiting until future ends to get the value, check if it has ended and if not check the other one!
                    intermediateResults.add(future.get());
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