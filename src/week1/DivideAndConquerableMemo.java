package week1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {

    // NEW: add a class cache variable
    HashMap<Integer, Integer> fibonacciHashMap = new HashMap<>();


    /** default method to be overriden*/
    @Override
    default OutputType divideAndConquer() {
        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
        subcomponents.forEach(subcomponent -> intermediateResults.add(subcomponent.divideAndConquer()));

        return recombine(intermediateResults);
    }
}