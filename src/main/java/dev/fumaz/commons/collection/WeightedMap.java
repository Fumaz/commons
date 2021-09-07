package dev.fumaz.commons.collection;

import dev.fumaz.commons.math.Randoms;

import java.util.NavigableMap;
import java.util.TreeMap;

public class WeightedMap<E> {

    private final NavigableMap<Double, E> map = new TreeMap<>();
    private double total = 0.0D;

    public WeightedMap<E> put(E element, double weight) {
        if (weight <= 0) {
            return this;
        }

        total += weight;
        map.put(total, element);
        return this;
    }

    public boolean remove(E element) {
        return map.values().remove(element);
    }

    public E random() {
        double value = Randoms.nextDouble() * total;

        return map.higherEntry(value).getValue();
    }

    public NavigableMap<Double, E> getMap() {
        return map;
    }

}
