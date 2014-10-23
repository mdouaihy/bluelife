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


import static org.bluelys.bluelife.predicates.FromPredicates.DIFFERENT;
import static org.bluelys.bluelife.predicates.ThanPredicates.GREATER;
import static org.bluelys.bluelife.predicates.ThanPredicates.LOWER;
import static org.bluelys.bluelife.predicates.ToPredicates.EQUAL;

public final class LongPredicates {
    /**
     * Private constructor
     */
    private LongPredicates() {}

    public static final Predicate<Long> ZERO = EQUAL.to(0L);
    public static final Predicate<Long> POSITIVE = GREATER.than(0L);
    public static final Predicate<Long> STRICTLY_POSITIVE = GREATER.than(0L).and(DIFFERENT.from(0L));
    public static final Predicate<Long> NEGATIVE = LOWER.than(0L);
    public static final Predicate<Long> STRICTLY_NEGATIVE = LOWER.than(0L).and(DIFFERENT.from(0L));

}
