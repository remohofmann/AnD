package week1;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {


    // NEW divideAncConquer method with a parameter.
    default OutputType divideAndConquer(OutputType[] computedFiboValuesArray) {

        if (this.isBasic()) {
            return this.baseFunction();
        }

        List<? extends DivideAndConquerable<OutputType>> subcomponents = this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());
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