package week1;

import java.util.List;
import java.util.ArrayList;

public interface DivideAndConquerable<OutputType> {

    // 4 basic methods to split the recursion into feasible smaller problems
    /** fibonacci example:
     * 1. do we have a base case? 'isBasic()'?
     * 2. if yes, return its value 'baseFunction()'
     * 3. if no, reduce case complexity, i.e. decompose()
     * 4. & recombine(): f(5) = f(4) + f(3)
     */
    boolean isBasic();
    OutputType baseFunction();
    List<? extends DivideAndConquerable<OutputType>> decompose();
    OutputType recombine(List<OutputType> intermediateResults);

    // what is this????
    default List<? extends DivideAndConquerable<OutputType>> stump() {
        return new ArrayList<DivideAndConquerable<OutputType>>(0);
    }


    // DEFAULT divideAndConquer method which returns a type of 'OutputType'
    // example: fibonacci will return an integer (i.e. 'int')
    default OutputType divideAndConquer() {
        // if 'this' is a basic case, return the 'basic result'
        // example: fibonacci(1)=1, f(0)=0
        if (this.isBasic()) {
            return this.baseFunction();
        }

        // if this is NOT a base case, do what needs to be done to reduce the problem size
        // example: f(5) = f(4) + f(3) which is what will need to be returned!
        // if the input is f(n), the output must be the list of [f(n-1), f(n-2)]
        List<? extends DivideAndConquerable<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

        subcomponents.forEach(subcomponent -> intermediateResults.add(subcomponent.divideAndConquer()));

        return recombine(intermediateResults);
    }
}