package week1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {


    // NEW: add a class cache variable
    // TODO: cannot use OutputType in a static context! What other means are there to have a cache?
    HashMap<Integer, OutputType> fibonacciHashMap = new HashMap<>();

    /**
     * default method to be overriden
     */
    @Override
    default OutputType divideAndConquer() {

//        fibonacciHashMap.computeIfAbsent()

        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
        subcomponents.forEach(subcomponent -> {
            /**TODO: here it should be checked if the fibonacciHashMap already contains the subcomponent and its
             * TODO: result. If yes, no need to calculate it. If no, calculate it and add it to the hashMap! */
            intermediateResults.add(subcomponent.divideAndConquer());
        });

        return recombine(intermediateResults);
    }
}