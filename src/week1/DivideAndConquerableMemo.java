package week1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {

    // NEW divideAncConquer method with a parameter.
    default OutputType divideAndConquer(Map<OutputType,OutputType> calculatedFibonaccis) {

        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerable<OutputType>> subcomponents =  this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
        subcomponents.forEach(subcomponent -> {
            /**TODO: here it should be checked if the fibonacciHashMap already contains the subcomponent and its
             * TODO: result. If yes, no need to calculate it. If no, calculate it and add it to the hashMap! */
            intermediateResults.add(subcomponent.divideAndConquer());
        });

        return recombine(intermediateResults);
    }
}