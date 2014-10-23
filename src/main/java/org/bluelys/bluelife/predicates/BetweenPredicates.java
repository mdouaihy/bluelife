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

package org.bluelys.bluelife.predicates;


public enum BetweenPredicates {
    BETWEEN {
        @Override
        public BetweenPredicate<Integer> lower(int value) {
            return new BetweenPredicates.BetweenPredicate<>(value);
        }
    };

    private BetweenPredicates() {}

    public abstract BetweenPredicate<Integer> lower(int value);


    public static class BetweenPredicate<V extends Comparable<V>> {

        private final V lowerValue;

        private BetweenPredicate(V lowerValue) {
            this.lowerValue = lowerValue;
        }

        public Predicate<V> and(V upperValue) {
           return ThanPredicates.GREATER.than(lowerValue).and(ThanPredicates.LOWER.than(upperValue));
        }

    }

}
