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


import static org.bluelys.bluelife.predicates.ToPredicates.EQUAL;

public final class StringPredicates {
    public static final Predicate<String> ZERO = EQUAL.to("");
    public static final Predicate<String> EMPTY = new EmptyPredicate();
    public static final Predicate<String> UPPERCASE = new UpperCasePredicate();
    public static final Predicate<String> LOWERCASE = new LowerCasePredicate();

    /**
     * Private Constructor
     */
    private StringPredicates() {}

    private static class EmptyPredicate extends Predicates.BasePredicate<String> {
        @Override
        public boolean apply(String element) {
            return element.isEmpty();
        }
    }

    private static class UpperCasePredicate extends Predicates.BasePredicate<String> {
        @Override
        public boolean apply(String element) {
            return element.toUpperCase().equals(element);
        }
    }

    private static class LowerCasePredicate extends Predicates.BasePredicate<String> {
        @Override
        public boolean apply(String element) {
            return element.toLowerCase().equals(element);
        }
    }

}
