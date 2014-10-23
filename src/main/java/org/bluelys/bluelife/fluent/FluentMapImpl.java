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
import org.bluelys.bluelife.predicates.Predicate;
import org.bluelys.bluelife.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class FluentMapImpl<K, V> implements FluentMap<K, V> {

    private final Map<K, V> map;

    private FluentMapImpl(Map<K, V> map) {
        this.map = map;
    }

    public static <E, K, V> FluentMap<K, V> newInstance(Map<K, V> map) {
        return new FluentMapImpl<>(new HashMap<>(map));
    }

    public static <E, K, V> FluentMap<K, V> newInstance(Iterable<E> iterable, Extractor2<E, K, V> extractor) {
        Map<K, V> map = new HashMap<>();
        for (E element : iterable) {
            Pair<K, V> pair = extractor.extract(element);
            map.put(pair.getFirst(), pair.getSecond());
        }
        return new FluentMapImpl<>(map);
    }

    @Override
    public FluentMap<K, V> filterKey(Predicate<K> predicate) {
        Map<K, V> localMap = new HashMap<>();
        for (Map.Entry<K, V> element : map.entrySet()) {
            if (predicate.apply(element.getKey())) {
                localMap.put(element.getKey(), element.getValue());
            }
        }
        return new FluentMapImpl<>(localMap);
    }

    @Override
    public FluentMap<K, V> filterValue(Predicate<V> predicate) {
        Map<K, V> localMap = new HashMap<>();
        for (Map.Entry<K, V> element : map.entrySet()) {
            if (predicate.apply(element.getValue())) {
                localMap.put(element.getKey(), element.getValue());
            }
        }
        return new FluentMapImpl<>(localMap);
    }

    @Override
    public FluentIterable<K> keys() {
        return FluentIterableImpl.newInstance(map.keySet());
    }

    @Override
    public FluentIterable<V> values() {
        return FluentIterableImpl.newInstance(map.values());
    }

    @Override
    public FluentIterable<Map.Entry<K, V>> iterable() {
        return FluentIterableImpl.newInstance(map.entrySet());
    }
}
