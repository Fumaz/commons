package dev.fumaz.commons.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A cache that stores objects for a certain amount of time
 *
 * @param <T> the type of the object
 */
public class Cooldown<T> {

    private final Cache<T, Long> cache;
    private final long delay;
    private final TimeUnit unit;

    public Cooldown(long delay, TimeUnit unit) {
        this.delay = delay;
        this.unit = unit;

        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(delay, unit)
                .build();
    }

    public Cooldown(long millis) {
        this(millis, TimeUnit.MILLISECONDS);
    }

    /**
     * Puts an object on cooldown
     *
     * @param object the object
     */
    public void put(T object) {
        cache.put(object, System.currentTimeMillis());
    }

    /**
     * Returns if an object is on cooldown
     *
     * @param object the object
     * @return whether the object is on cooldown
     */
    public boolean has(T object) {
        return cache.getIfPresent(object) != null;
    }

    /**
     * Removes an object from the cooldown
     *
     * @param object the object
     */
    public void invalidate(T object) {
        cache.invalidate(object);
    }

    /**
     * Clears all objects from cooldown
     */
    public void invalidateAll() {
        cache.invalidateAll();
    }

    /**
     * Clears all objects in the iterable from cooldown
     *
     * @param iterable the iterable
     */
    public void invalidateAll(Iterable<T> iterable) {
        cache.invalidateAll(iterable);
    }

    /**
     * @return the amount of objects on cooldown
     */
    public long size() {
        return cache.size();
    }

    /**
     * Returns how long an object has left on cooldown
     *
     * @param object the object
     * @param unit   the time unit
     * @return the amount of time left
     */
    public long get(T object, TimeUnit unit) {
        long time = getInsertionTime(object);

        if (time == 0) {
            return time;
        }

        long delay = unit.convert(this.delay, this.unit);
        return delay - unit.convert(System.currentTimeMillis() - time, TimeUnit.MILLISECONDS);
    }

    public long getMillis(T object) {
        return get(object, TimeUnit.MILLISECONDS);
    }

    public long getSeconds(T object) {
        return get(object, TimeUnit.SECONDS);
    }

    /**
     * @return the cache, as a map
     */
    public Map<T, Long> asMap() {
        return cache.asMap();
    }

    private long getInsertionTime(T object) {
        Long time = cache.getIfPresent(object);

        return time != null ? time : 0;
    }

}
