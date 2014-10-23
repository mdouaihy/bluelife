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
import org.bluelys.bluelife.predicates.Predicate;

/**
 * Main interface to represent a fluent iterable.
 */
public interface FluentIterable<E> extends Iterable<E> {

    FluentIterable<E> filter(Predicate<E> predicate);

    <O> FluentIterable<O> mutate(Function<E, O> function);

    /**
     * Transform the fluent iterable to a fluent Map using an extractor.
     * @param extractor is an Extractor that will be called on each element of
     *                  the fluent iterable to extract a pair of (Key, Value)
     * @return a Fluent Map, the result of applying the extractor on the iterable
     */
    <K, V> FluentMap<K, V> map(Extractor2<E, K, V> extractor);


    public int size();
}
