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

package org.bluelys.bluelife.iterators;


import org.bluelys.bluelife.predicates.Predicate;

import java.util.Iterator;

public final class FilteredIterator<E> implements Iterator<E> {

    private final Predicate<E> predicate;
    private final Iterator<E> iterator;
    private volatile E next;

    public FilteredIterator(Iterator<E> iterator, Predicate<E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        computeAndStoreNext();
    }

    private E computeNext() {
        while (this.iterator.hasNext()) {
            E element = this.iterator.next();
            if (predicate.apply(element)) {
                return element;
            }
        }
        return null;
    }

    private void computeAndStoreNext() {
        next = computeNext();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        E available = next;
        computeAndStoreNext();
        return available;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }

}
