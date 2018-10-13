package ex5_mergesort;

import java.util.List;

/**
 * Created by yann on 13.10.18.
 */
public class InsertionSortInteger implements InsertionSortInterface<Integer> {

    private List<Integer> integers;

    public InsertionSortInteger(List<Integer> integers){
        this.integers = integers;
    }

    @Override
    public int getSize() {
        return integers.size();
    }

    @Override
    public Integer read(int j) {
        return integers.get(j);
    }

    @Override
    public void move(int i, int j) {
        Integer temp = integers.get(i);
        integers.set(i, integers.get(j));
        integers.set(j,temp);

    }

    @Override
    public void put(int i, Integer integer) {
        integers.set(i, integer);
    }
}
