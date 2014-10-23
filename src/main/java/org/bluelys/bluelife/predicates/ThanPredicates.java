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


public enum ThanPredicates {
    GREATER {
        @Override
        public Predicate<Integer> than(int value) {
            return new GreaterThan<>(value);
        }

        @Override
        public Predicate<Double> than(double value) {
            return new GreaterThan<>(value);
        }

        @Override
        public Predicate<Long> than(long value) {
            return new GreaterThan<>(value);
        }

        @Override
        public Predicate<String> than(String value) {
            return new GreaterThan<>(value);
        }

        @Override
        public Predicate<Float> than(float value) {
            return new GreaterThan<>(value);
        }

        @Override
        public Predicate<Byte> than(byte value) {
            return new GreaterThan<>(value);
        }

        @Override
        public <V extends Comparable<V>> Predicate<V> than(V value) {
            return new GreaterThan<>(value);
        }
    },
    LOWER {
        @Override
        public Predicate<Integer> than(int value) {
            return new LowerThan<>(value);
        }

        @Override
        public Predicate<Double> than(double value) {
            return new LowerThan<>(value);
        }

        @Override
        public Predicate<Long> than(long value) {
            return new LowerThan<>(value);
        }

        @Override
        public Predicate<String> than(String value) {
            return new LowerThan<>(value);
        }

        @Override
        public Predicate<Float> than(float value) {
            return new LowerThan<>(value);
        }

        @Override
        public Predicate<Byte> than(byte value) {
            return new LowerThan<>(value);
        }

        @Override
        public <V extends Comparable<V>> Predicate<V> than(V value) {
           return new LowerThan<>(value);
        }
    };

    ThanPredicates() {

    }

    public abstract Predicate<Integer> than(int value);

    public abstract Predicate<Double> than(double value);

    public abstract Predicate<Long> than(long value);

    public abstract Predicate<String> than(String value);

    public abstract Predicate<Float> than(float value);

    public abstract Predicate<Byte> than(byte value);

   public abstract <V extends Comparable<V>> Predicate<V> than(V value);


    private static class GreaterThan<V extends Comparable<V>> extends Predicates.BasePredicate<V> {

        private final V value;

        private GreaterThan(V value) {
            this.value = value;
        }

        @Override
        public boolean apply(V element) {
            return value.compareTo(element) <= 0;
        }
    }

    private static class LowerThan<V extends Comparable<V>> extends Predicates.BasePredicate<V> {

        private final V value;

        private LowerThan(V value) {
            this.value = value;
        }

        @Override
        public boolean apply(V element) {
            return value.compareTo(element) >= 0;
        }
    }


}
