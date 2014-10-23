/*
 * Copyright 2014 Mehrez DOUAIHY
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bluelys.bluelife.fluent;


import org.bluelys.bluelife.functions.Extractor2;
import org.bluelys.bluelife.functions.Function;
import org.bluelys.bluelife.iterators.FilteredIterable;
import org.bluelys.bluelife.predicates.Predicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FluentIterableImpl<E> implements FluentIterable<E> {

    private final Iterable<E> iterable;

    public FluentIterableImpl(Iterable<E> iterable) {
        this.iterable = iterable;
    }

    public FluentIterableImpl(Iterable<E> iterable, Predicate<E> predicate) {
        this.iterable = new FilteredIterable<>(iterable, predicate);
    }


    @Override
    public FluentIterable<E> filter(Predicate<E> predicate) {
        return new FluentIterableImpl<>(this.iterable, predicate);
    }

    @Override
    public <O> FluentIterable<O> mutate(Function<E, O> function) {
        List<O> list = new ArrayList<>();
        for (E element : this) {
            list.add(function.eval(element));
        }
        return FluentIterableImpl.newInstance(list);
    }

    @Override
    public <K, V> FluentMap<K, V> map(Extractor2<E, K, V> extractor) {
        return FluentMapImpl.newInstance(this, extractor);
    }

    @Override
    public Iterator<E> iterator() {
        return iterable.iterator();
    }

    @Override
    public int size() {
        int elements = 0;
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            elements++;
        }
        return elements;
    }

    public static <E> FluentIterable<E> newInstance(Iterable<E> iterable) {
        return new FluentIterableImpl<>(iterable);
    }

    public static <E> FluentIterable<E> newInstance(Iterable<E> iterable, Predicate<E> predicate) {
        return new FluentIterableImpl<>(iterable, predicate);
    }
}
