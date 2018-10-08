package ex5_mergesort;

import week1.DivideAndConquerable;

import java.util.ArrayList;
import java.util.List;

public interface DnCDummy<OutputType> {

    boolean isBasic();

    OutputType baseFunction();

    List<? extends DivideAndConquerable<OutputType>> decompose();

    OutputType recombine(List<OutputType> intermediateResults);



    default OutputType divideAndConquer() {
        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerable<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

        subcomponents.forEach(subcomponent -> intermediateResults.add(subcomponent.divideAndConquer()));

        return recombine(intermediateResults);
    }
}
