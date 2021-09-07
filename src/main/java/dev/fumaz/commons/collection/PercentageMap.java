package dev.fumaz.commons.collection;

import dev.fumaz.commons.math.Percentages;

import java.util.HashMap;
import java.util.Map;

public class PercentageMap<E> extends HashMap<E, Double> {

    public PercentageMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public PercentageMap(int initialCapacity) {
        super(initialCapacity);
    }

    public PercentageMap() {
        super();
    }

    public PercentageMap(Map<? extends E, ? extends Double> m) {
        super(m);
    }

    public E random() {
        return Percentages.random(this);
    }

}
