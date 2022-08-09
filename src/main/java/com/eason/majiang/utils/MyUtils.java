package com.eason.majiang.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils {
    private MyUtils()
    {

    }

    public static <E, C extends Collection<E>> Stream<C> combStream(Collection<E> coll, int size,
                                                                    Function<Collection<E>, C> combCollFactory, Predicate<E> elementFilter,
                                                                    BiPredicate<E, E> elementInCombFilter)
    {
       if(size == 0)
       {
           return Stream.of(combCollFactory.apply(Collections.emptyList()));
       }

       if(coll.isEmpty() || size> coll.size() || size < 0)
       {
           return Stream.empty();
       }

       if(size == 1)
       {
           if(elementFilter == null)
           {
               return  coll.stream().map(e->combCollFactory.apply(Arrays.asList(e)));
           }
           else
           {
               return coll.stream().filter(elementFilter).map(e->combCollFactory.apply(Arrays.asList(e)));
           }
       }

        return Stream.empty();
    }



}
