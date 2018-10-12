package week1;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {

    OutputType getKey();



    // NEW divideAncConquer method with a parameter. Map is of form Map<ID, value>
    default OutputType divideAndConquer(Map<OutputType, OutputType> computedFiboValuesMap) {
        if (this.isBasic()) {
            return this.baseFunction();
        }

        // Return Value of an Index (key) if this Index exists in Map
        if (computedFiboValuesMap.containsKey(this.getKey())) return computedFiboValuesMap.get(this.getKey());

        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = (List<? extends DivideAndConquerableMemo<OutputType>>) this.decompose();

        List<OutputType> intermediateResults = new ArrayList<OutputType>(subcomponents.size());

        subcomponents.forEach(subcomponent -> {
            if (computedFiboValuesMap.containsKey(subcomponent.getKey())) {
                intermediateResults.add(computedFiboValuesMap.get(subcomponent.getKey()));
            } else {
                OutputType result = subcomponent.divideAndConquer(computedFiboValuesMap);
                intermediateResults.add(result);
                computedFiboValuesMap.put(subcomponent.getKey(), result);
            }
        });

        return recombine(intermediateResults);

    }
}
