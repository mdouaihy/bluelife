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

import static org.bluelys.bluelife.predicates.BetweenPredicates.BETWEEN;
import static org.bluelys.bluelife.predicates.FromPredicates.DIFFERENT;
import static org.bluelys.bluelife.predicates.IntegerPredicates.*;
import static org.bluelys.bluelife.predicates.ThanPredicates.GREATER;
import static org.bluelys.bluelife.predicates.ThanPredicates.LOWER;
import static org.bluelys.bluelife.predicates.ToPredicates.EQUAL;
import static org.fest.assertions.Assertions.assertThat;


public class TestIntegerPredicates {

    @Test
    public void testOddAndEvenPredicates() {
        assertThat(ODD.apply(5)).isTrue();
        assertThat(ODD.apply(4)).isFalse();
        assertThat(ODD.apply(-5)).isTrue();
        assertThat(ODD.apply(-4)).isFalse();

        assertThat(EVEN.apply(5)).isFalse();
        assertThat(EVEN.apply(4)).isTrue();
        assertThat(EVEN.apply(-5)).isFalse();
        assertThat(EVEN.apply(-4)).isTrue();
    }

    @Test
    public void testNegativePredicates() {
        assertThat(NEGATIVE.apply(5)).isFalse();
        assertThat(NEGATIVE.apply(-5)).isTrue();
        assertThat(NEGATIVE.apply(0)).isTrue();

        assertThat(STRICTLY_NEGATIVE.apply(5)).isFalse();
        assertThat(STRICTLY_NEGATIVE.apply(-5)).isTrue();
        assertThat(STRICTLY_NEGATIVE.apply(0)).isFalse();
    }

    @Test
    public void testZeroPredicates() {
        assertThat(ZERO.apply(5)).isFalse();
        assertThat(ZERO.apply(-5)).isFalse();
        assertThat(ZERO.apply(0)).isTrue();
    }

    @Test
    public void testPositivePredicates() {
        assertThat(POSITIVE.apply(5)).isTrue();
        assertThat(POSITIVE.apply(-5)).isFalse();
        assertThat(POSITIVE.apply(0)).isTrue();

        assertThat(STRICTLY_POSITIVE.apply(5)).isTrue();
        assertThat(STRICTLY_POSITIVE.apply(-5)).isFalse();
        assertThat(STRICTLY_POSITIVE.apply(0)).isFalse();
    }

    @Test
    public void testThanPredicates() {
        assertThat(GREATER.than(3).apply(5)).isTrue();
        assertThat(GREATER.than(3).apply(1)).isFalse();

        assertThat(LOWER.than(3).apply(5)).isFalse();
        assertThat(LOWER.than(3).apply(1)).isTrue();
    }

    @Test
    public void testFromPredicates() {
        assertThat(DIFFERENT.from(3).apply(5)).isTrue();
        assertThat(DIFFERENT.from(3).apply(1)).isTrue();
        assertThat(DIFFERENT.from(3).apply(3)).isFalse();
    }

    @Test
    public void testToPredicates() {
        assertThat(EQUAL.to(3).apply(3)).isTrue();
        assertThat(EQUAL.to(3).apply(4)).isFalse();
        assertThat(EQUAL.to(3).apply(2)).isFalse();
    }

    @Test
    public void testBetweenPredicates() {
        assertThat(BETWEEN.lower(4).and(7).apply(5)).isTrue();
        assertThat(BETWEEN.lower(-4).and(7).apply(5)).isTrue();
        assertThat(BETWEEN.lower(10).and(17).apply(5)).isFalse();
    }

    @Test
    public void testCombining() {
        assertThat(ODD.and(EVEN).apply(4)).isFalse();
        assertThat(ODD.nand(EVEN).apply(4)).isTrue();
        assertThat(ODD.or(EVEN).apply(4)).isTrue();
        assertThat(ODD.nor(EVEN).apply(4)).isFalse();
        assertThat(ODD.xor(EVEN).apply(4)).isTrue();

        assertThat(GREATER.than(3).xor(GREATER.than(2)).apply(4)).isFalse();

        assertThat(ODD.and(GREATER.than(3)).apply(5)).isTrue();
        assertThat(ODD.nand(GREATER.than(3)).apply(5)).isFalse();

        assertThat(GREATER.than(3).and(DIFFERENT.from(15)).apply(5)).isTrue();
        assertThat(GREATER.than(3).and(DIFFERENT.from(15)).apply(15)).isFalse();
    }

}
