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


import org.bluelys.bluelife.predicates.Predicate;

/**
 * Represents a Fluent Value.
 *
 * A Fluent Value can be considered as an extension to any normal value as it allows more operations on it.
 * Those operation are accessible through a Fluent API.
 */
public final class FluentValue<T> {
    private final T value;
    private final boolean is;

    public FluentValue(T value) {
        this(value, true);
    }

    private FluentValue(T value, boolean is) {
        this.value = value;
        this.is = is;
    }

    /**
     * Verifies if the value verifies the predicate
     * @param predicates the predicates to be verified for the value
     * @return Returns Fluent value for Fluent calls.
     */
    @SafeVarargs
    public final FluentValue<T> is(Predicate<T>... predicates) {
        if (is) {
            for (Predicate<T> predicate : predicates) {
                if (!predicate.apply(value)) {
                    return new FluentValue<>(value, false);
                }
            }
        }
        return this;
    }

    /**
     * Verifies if the value does not verify the predicate
     * @param predicates the predicates to be verified for the value
     * @return Returns Fluent value for Fluent calls.
     */
    @SafeVarargs
    public final FluentValue<T> isnot(Predicate<T>... predicates) {
        if (is) {
            for (Predicate<T> predicate : predicates) {
                if (predicate.apply(value)) {
                    return new FluentValue<>(value, false);
                }
            }
        }
        return this;
    }

    /**
     * Returns if the Fluent value verifies/doesn't verify all the predicates so far
     *
     * @return  a boolean to indicate if the Fluent value verifies or not, as expected, all the predicates so far.
     */
    public boolean is() {
        return is;
    }
}
