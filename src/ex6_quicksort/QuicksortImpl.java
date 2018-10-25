package ex6_quicksort;

import java.util.Arrays;

public class QuicksortImpl implements Quicksort<Integer> {

    private Integer[] data;

    public QuicksortImpl(Integer[] data){
        this.data = data;
    }

    @Override
    public Integer read(int index) {
        return data[index];
    }

    @Override
    public void swap(int medianOfThree, int right) {
        int tmp = this.data[medianOfThree];
        this.data[medianOfThree] = this.data[right];
        this.data[right] = tmp;
    }

    @Override
    public String toString() {
        int[] array = new int[data.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = data[i];
        }
        return Arrays.toString(array);
    }

    public Integer[] getData(){
        return this.data;
    }
}
