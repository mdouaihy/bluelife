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


import org.testng.annotations.Test;

import static org.bluelys.bluelife.predicates.FromPredicates.DIFFERENT;
import static org.bluelys.bluelife.predicates.LongPredicates.*;
import static org.bluelys.bluelife.predicates.ThanPredicates.GREATER;
import static org.bluelys.bluelife.predicates.ThanPredicates.LOWER;
import static org.bluelys.bluelife.predicates.ToPredicates.EQUAL;
import static org.fest.assertions.Assertions.assertThat;


public class TestLongPredicates {

    @Test
    public void testNegativePredicates() {
        assertThat(NEGATIVE.apply(5L)).isFalse();
        assertThat(NEGATIVE.apply(-5L)).isTrue();
        assertThat(NEGATIVE.apply(0L)).isTrue();

        assertThat(STRICTLY_NEGATIVE.apply(5L)).isFalse();
        assertThat(STRICTLY_NEGATIVE.apply(-5L)).isTrue();
        assertThat(STRICTLY_NEGATIVE.apply(0L)).isFalse();
    }

    @Test
    public void testZeroPredicates() {
        assertThat(ZERO.apply(5L)).isFalse();
        assertThat(ZERO.apply(-5L)).isFalse();
        assertThat(ZERO.apply(0L)).isTrue();
    }

    @Test
    public void testPositivePredicates() {
        assertThat(POSITIVE.apply(5L)).isTrue();
        assertThat(POSITIVE.apply(-5L)).isFalse();
        assertThat(POSITIVE.apply(0L)).isTrue();

        assertThat(STRICTLY_POSITIVE.apply(5L)).isTrue();
        assertThat(STRICTLY_POSITIVE.apply(-5L)).isFalse();
        assertThat(STRICTLY_POSITIVE.apply(0L)).isFalse();
    }

    @Test
    public void testThanPredicates() {
        assertThat(GREATER.than(3L).apply(5L)).isTrue();
        assertThat(GREATER.than(3L).apply(1L)).isFalse();

        assertThat(LOWER.than(3L).apply(5L)).isFalse();
        assertThat(LOWER.than(3L).apply(1L)).isTrue();
    }

    @Test
    public void testFromPredicates() {
        assertThat(DIFFERENT.from(3L).apply(5L)).isTrue();
        assertThat(DIFFERENT.from(3L).apply(1L)).isTrue();
        assertThat(DIFFERENT.from(3L).apply(3L)).isFalse();
    }

    @Test
    public void testToPredicates() {
        assertThat(EQUAL.to(3L).apply(3L)).isTrue();
        assertThat(EQUAL.to(3L).apply(4L)).isFalse();
        assertThat(EQUAL.to(3L).apply(2L)).isFalse();
    }

    @Test
    public void testCombining() {
        assertThat(ZERO.and(POSITIVE).apply(4L)).isFalse();
        assertThat(ZERO.nand(POSITIVE).apply(4L)).isTrue();
        assertThat(ZERO.or(POSITIVE).apply(4L)).isTrue();
        assertThat(ZERO.nor(POSITIVE).apply(4L)).isFalse();
        assertThat(POSITIVE.xor(NEGATIVE).apply(4L)).isTrue();

        assertThat(GREATER.than(3L).xor(GREATER.than(2L)).apply(4L)).isFalse();

        assertThat(GREATER.than(3L).and(DIFFERENT.from(15L)).apply(5L)).isTrue();
        assertThat(GREATER.than(3L).and(DIFFERENT.from(15L)).apply(15L)).isFalse();
    }

}
