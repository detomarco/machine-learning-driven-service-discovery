package it.univaq.disim.discovery.common.utils;

import com.google.common.collect.Iterables;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static <T> T getRandomElement(Iterable<T> iterable) {
        int randomIndex = ThreadLocalRandom.current().nextInt(Iterables.size(iterable));
        return Iterables.get(iterable, randomIndex);
    }

}
