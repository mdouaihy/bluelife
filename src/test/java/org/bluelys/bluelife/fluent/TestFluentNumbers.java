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


import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import static org.bluelys.bluelife.predicates.ThanPredicates.GREATER;
import static org.bluelys.bluelife.predicates.ThanPredicates.LOWER;
import static org.fest.assertions.Assertions.assertThat;


public class TestFluentNumbers {

    @Test
    public void testFluentIs() {
        Assertions.assertThat(Fluent.of(4.0).is(GREATER.than(3.)).is()).isTrue();
        assertThat(Fluent.of(4.0).is(GREATER.than(5.)).is()).isFalse();

        assertThat(Fluent.of(4.0).is(LOWER.than(3.)).is()).isFalse();
        assertThat(Fluent.of(4.0).is(LOWER.than(5.)).is()).isTrue();

        assertThat(Fluent.of(4.0).is(GREATER.than(3.).and(LOWER.than(5.))).is()).isTrue();
        assertThat(Fluent.of(4.0).is(GREATER.than(5.).and(LOWER.than(3.))).is()).isFalse();
        assertThat(Fluent.of(4.0).is(GREATER.than(5.).nor(LOWER.than(3.))).is()).isTrue();
    }

    @Test
    public void testFluentIsnot() {
        assertThat(Fluent.of(4).isnot(GREATER.than(3)).is()).isFalse();
        assertThat(Fluent.of(4).isnot(GREATER.than(5)).is()).isTrue();

        assertThat(Fluent.of(4).isnot(LOWER.than(3)).is()).isTrue();
        assertThat(Fluent.of(4).isnot(LOWER.than(5)).is()).isFalse();

        assertThat(Fluent.of(4).isnot(GREATER.than(3).and(LOWER.than(5))).is()).isFalse();
        assertThat(Fluent.of(4).isnot(GREATER.than(5).and(LOWER.than(3))).is()).isTrue();
        assertThat(Fluent.of(4).isnot(GREATER.than(5).nor(LOWER.than(3))).is()).isFalse();
    }

    @Test
    public void testFluentChaining() {
        assertThat(Fluent.of(4.0)
                        .is(GREATER.than(5.))
                        .is(GREATER.than(2.))
                        .is()
        ).isFalse();

        assertThat(Fluent.of(4.0)
                        .isnot(GREATER.than(5.))
                        .is(GREATER.than(2.))
                        .is()
        ).isTrue();

        assertThat(Fluent.of(4.0)
                        .isnot(GREATER.than(5.), GREATER.than(7.))
                        .is(GREATER.than(2.), GREATER.than(-1.))
                        .is()
        ).isTrue();
    }

}
