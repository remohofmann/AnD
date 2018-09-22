package week1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface DivideAndConquerableMemo<OutputType> {

    // 4 basic methods to split the recursion into feasible smaller problems

    boolean isBasic();
    OutputType getValue();

    OutputType baseFunction();

    List<? extends DivideAndConquerableMemo<Integer>> decompose();

    OutputType recombine(List<OutputType> intermediateResults);

    default List<? extends DivideAndConquerableMemo<OutputType>> stump() {
        return new ArrayList<DivideAndConquerableMemo<OutputType>>(0);
    }

    default OutputType divideAndConquer(HashMap map) {
        // if 'this' is a basic case, return the 'basic result'
        // example: fibonacci(1)=1, f(0)=0
        if (this.isBasic()) {
            return this.baseFunction();
        }

        if(map.containsValue(this.getValue())) return (OutputType) map.get(this.getValue());

        // if this is NOT a base case, do what needs to be done to reduce the problem size
        // example: f(5) = f(4) + f(3) which is what will need to be returned!
        // if the input is f(n), the output must be the list of [f(n-1), f(n-2)]
        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = (List<? extends DivideAndConquerableMemo<OutputType>>) this.decompose();

        /** make a list of intermediate results
         * splitting f(5) into f(4) and f(3) makes a list of the results (i.e. f(4) and f(3))
         * that can be looked up later.
         * -> avoid recalculating already calculated stuff!
         * This is JUST a result list of the intermediate results! */
        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

        // now add each subcomponent to the intermediateResults list
        // here 'add' is NOT a sum!
        // It ADDS the subcomponents to the list!
        subcomponents.forEach(subcomponent -> intermediateResults.add(subcomponent.divideAndConquer(map)));


        // Before returning store values in HashMap
        map.put(this.getValue(), recombine(intermediateResults));

        // since it's not a base case, we need to return the recombination, i.e. f(4)+f(3), here IT IS the sum!
        return recombine(intermediateResults);

    }
}