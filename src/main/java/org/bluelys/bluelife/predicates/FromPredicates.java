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


public enum FromPredicates {
    DIFFERENT {
        @Override
        public Predicate<Integer> from(int value) {
            return new DifferentFrom<>(value);
        }

        @Override
        public Predicate<Double> from(double value) {
            return new DifferentFrom<>(value);
        }

        @Override
        public Predicate<Long> from(long value) {
            return new DifferentFrom<>(value);
        }

        @Override
        public Predicate<String> from(String value) {
            return new DifferentFrom<>(value);
        }

        @Override
        public Predicate<Float> from(float value) {
            return new DifferentFrom<>(value);
        }

        @Override
        public Predicate<Byte> from(byte value) {
            return new DifferentFrom<>(value);
        }
    };

    private FromPredicates() {}

    public abstract Predicate<Integer> from(int value);

    public abstract Predicate<Double> from(double value);

    public abstract Predicate<Long> from(long value);

    public abstract Predicate<String> from(String value);

    public abstract Predicate<Float> from(float value);

    public abstract Predicate<Byte> from(byte value);

    private static class DifferentFrom<V extends Comparable<V>> extends Predicates.BasePredicate<V> {

        private final V value;

        private DifferentFrom(V value) {
            this.value = value;
        }

        @Override
        public boolean apply(V element) {
            return element.compareTo(value)!=0;
        }
    }

}
