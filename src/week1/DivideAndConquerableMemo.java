package week1;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {

    @Override
    List<? extends DivideAndConquerableMemo<OutputType>> decompose();



    // NEW divideAncConquer method with a parameter. Map is of form Map<ID, value>
    default OutputType divideAndConquer(Map<OutputType,OutputType> computedFiboValuesArray) {

        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());


        /** TODO: hier happert's: eine Fibonacci - "Funktion" hat einen Input und einen Output.
         * f(2) = 1
         * Ich habe keinen Access zum Input, der mir mit dem Array-index behilflich sein könnte!
         * Wie weiter?
          */
        subcomponents.forEach(subcomponent -> {
            if (computedFiboValuesArray.containsKey(subcomponent)) {
                intermediateResults.add(computedFiboValuesArray.get(subcomponent));
            } else {
                // TODO: check if the order (intermediateResult then computedFiboValuesMap)
                // TODO: is relevant or not. Maybe order needs to be changed.
                OutputType subComponentResult = subcomponent.divideAndConquer();
                intermediateResults.add(subComponentResult);
                computedFiboValuesArray.put(subcomponent, subComponentResult);
            }
        });



        return recombine(intermediateResults);
    }
}