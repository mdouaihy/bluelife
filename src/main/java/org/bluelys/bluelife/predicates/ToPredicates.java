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


public enum ToPredicates {
    EQUAL {
        @Override
        public Predicate<Integer> to(int value) {
            return new EqualTo<>(value);
        }

        @Override
        public Predicate<Double> to(double value) {
            return new EqualTo<>(value);
        }

        @Override
        public Predicate<Long> to(long value) {
            return new EqualTo<>(value);
        }

        @Override
        public Predicate<String> to(String value) {
            return new EqualTo<>(value);
        }

        @Override
        public Predicate<Float> to(float value) {
            return new EqualTo<>(value);
        }

        @Override
        public Predicate<Byte> to(byte value) {
            return new EqualTo<>(value);
        }
    };

    private ToPredicates() {}

    public abstract Predicate<Integer> to(int value);

    public abstract Predicate<Double> to(double value);

    public abstract Predicate<Long> to(long value);

    public abstract Predicate<String> to(String value);

    public abstract Predicate<Float> to(float value);

    public abstract Predicate<Byte> to(byte value);


    private static class EqualTo<V extends Comparable<V>> extends Predicates.BasePredicate<V> {

        private final V value;

        private EqualTo(V value) {
            this.value = value;
        }

        @Override
        public boolean apply(V element) {
            return element.compareTo(value) == 0;
        }
    }

}
