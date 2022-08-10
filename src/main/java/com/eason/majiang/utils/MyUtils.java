package com.eason.majiang.utils;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        List<E> list;

       if(elementFilter == null) {
           if (elementInCombFilter == null && size == coll.size()) {
               return Stream.of(combCollFactory.apply(coll));
           }
           list = (coll instanceof List) ? (List<E>) coll : new ArrayList<>(coll);
       }
       else
       {
           list = coll.stream().filter(elementFilter).collect(Collectors.toList());

           if(list.isEmpty() || size > list.size())
           {
               return Stream.empty();
           }
           if(elementInCombFilter == null && size == list.size())
           {
               return Stream.of(combCollFactory.apply(list));
           }

       }
        return IntStream.rangeClosed(0, list.size() - size).boxed().flatMap(index -> {
            E first = list.get(index);
            List<E> others = list.subList(index + 1, list.size());
            Predicate<E> othersElementFilter = elementFilter;
            if (elementInCombFilter != null) {
                Predicate<E> othersWithFirstFilter = e -> elementInCombFilter.test(first, e);
                othersElementFilter = othersElementFilter == null ? othersWithFirstFilter
                        : othersElementFilter.and(othersWithFirstFilter);
            }
            return combStream(others, size - 1, combCollFactory, othersElementFilter, elementInCombFilter)
                    .peek(comb -> comb.add(first));
        });

    }



}
