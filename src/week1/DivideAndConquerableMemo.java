package week1;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public interface DivideAndConquerableMemo<OutputType> extends DivideAndConquerable<OutputType> {

    OutputType getKey();


    @Override
    List<? extends DivideAndConquerableMemo<OutputType>> decompose();


    // NEW divideAncConquer method with a parameter. Map is of form Map<ID, value>
    default OutputType divideAndConquer(Map<OutputType, OutputType> computedFiboValuesMap) {

        if (this.isBasic()) {
            return this.baseFunction();
        }

        if (computedFiboValuesMap.containsKey(this.getKey())) return computedFiboValuesMap.get(this.getKey());

        List<? extends DivideAndConquerableMemo<OutputType>> subcomponents = this.decompose();

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

        // Before returning store values in HashMap
        // by commenting out this line, we omit the possibility to save f(5) when calculating f(5) in to the
        // map -> omitting the top most value is ok!
//        computedFiboValuesMap.put(this.getKey(), recombine(intermediateResults));

        return recombine(intermediateResults);
    }
}
