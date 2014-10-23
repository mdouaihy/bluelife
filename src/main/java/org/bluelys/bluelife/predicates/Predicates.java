/*
 *
 *  * Copyright 2014 Mehrez DOUAIHY
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.bluelys.bluelife.predicates;


public final class Predicates  {

    private Predicates() {}

    public static <E> Predicate<E> and(Predicate<E> left, Predicate<E> right) {
        return new AndPredicate<>(left, right);
    }

    public static <E> Predicate<E> or(Predicate<E> left, Predicate<E> right) {
        return new OrPredicate<>(left, right);
    }

    public static <E> Predicate<E> xor(Predicate<E> left, Predicate<E> right) {
        return new XorPredicate<>(left, right);
    }

    public static <E> Predicate<E> nand(Predicate<E> left, Predicate<E> right) {
        return new NandPredicate<>(left, right);
    }

    public static <E> Predicate<E> nor(Predicate<E> left, Predicate<E> right) {
        return new NorPredicate<>(left, right);
    }

    public static <E> Predicate<E> not(Predicate<E> left) {
        return new NotPredicate<>(left);
    }

    private static class OrPredicate<E> extends BasePredicate<E> {
        private final Predicate<E> firstPredicate;
        private final Predicate<E> secondPredicate;

        private OrPredicate(Predicate<E> firstPredicate, Predicate<E> secondPredicate) {
            this.firstPredicate = firstPredicate;
            this.secondPredicate = secondPredicate;
        }

        @Override
        public boolean apply(E element) {
            return firstPredicate.apply(element) || secondPredicate.apply(element);
        }

    }

    private static class AndPredicate<E> extends BasePredicate<E> {
        private final Predicate<E> firstPredicate;
        private final Predicate<E> secondPredicate;

        private AndPredicate(Predicate<E> firstPredicate, Predicate<E> secondPredicate) {
            this.firstPredicate = firstPredicate;
            this.secondPredicate = secondPredicate;
        }

        @Override
        public boolean apply(E element) {
            return firstPredicate.apply(element) && secondPredicate.apply(element);
        }
    }

    private static class NorPredicate<E> extends BasePredicate<E> {
        private final OrPredicate<E> orPredicate;

        private NorPredicate(Predicate<E> firstPredicate, Predicate<E> secondPredicate) {
            this.orPredicate = new OrPredicate<>(firstPredicate, secondPredicate);
        }

        @Override
        public boolean apply(E element) {
            return !orPredicate.apply(element);
        }
    }

    private static class XorPredicate<E> extends BasePredicate<E> {
        private final Predicate<E> firstPredicate;
        private final Predicate<E> secondPredicate;

        private XorPredicate(Predicate<E> firstPredicate, Predicate<E> secondPredicate) {
            this.firstPredicate = firstPredicate;
            this.secondPredicate = secondPredicate;
        }

        @Override
        public boolean apply(E element) {
            return firstPredicate.apply(element) ^ secondPredicate.apply(element);
        }
    }

    private static class NandPredicate<E> extends BasePredicate<E> {
        private final Predicate<E> andPredicate;

        private NandPredicate(Predicate<E> firstPredicate, Predicate<E> secondPredicate) {
            this.andPredicate = new AndPredicate<>(firstPredicate, secondPredicate);
        }

        @Override
        public boolean apply(E element) {
            return !andPredicate.apply(element);
        }
    }

    private static class NotPredicate<E> extends Predicates.BasePredicate<E> {
        private final Predicate<E> predicate;

        private NotPredicate(Predicate<E> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean apply(E element) {
            return !predicate.apply(element);
        }
    }

    public static abstract class BasePredicate<E> implements Predicate<E> {

        @Override
        public Predicate<E> and(Predicate<E> other) {
            return Predicates.and(this, other);
        }

        @Override
        public Predicate<E> or(Predicate<E> other) {
            return Predicates.or(this, other);
        }

        @Override
        public Predicate<E> xor(Predicate<E> other) {
            return Predicates.xor(this, other);
        }

        @Override
        public Predicate<E> nor(Predicate<E> other) {
            return Predicates.nor(this, other);
        }

        @Override
        public Predicate<E> nand(Predicate<E> other) {
            return Predicates.nand(this, other);
        }

        @Override
        public Predicate<E> not() {
            return Predicates.not(this);
        }
    }

}
