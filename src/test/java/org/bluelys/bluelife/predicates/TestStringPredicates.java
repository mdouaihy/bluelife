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

import static org.bluelys.bluelife.predicates.StringPredicates.*;
import static org.fest.assertions.Assertions.assertThat;


public class TestStringPredicates {

    @Test
    public void testEmpty() {
        assertThat(ZERO.apply("")).isTrue();
        assertThat(ZERO.apply("0")).isFalse();

        assertThat(EMPTY.apply("")).isTrue();
        assertThat(EMPTY.apply("t")).isFalse();
    }

    @Test
    public void testUpperCase() {
        assertThat(UPPERCASE.apply("")).isTrue();
        assertThat(UPPERCASE.apply("TATA")).isTrue();
        assertThat(UPPERCASE.apply("tata")).isFalse();
        assertThat(UPPERCASE.apply("TaTa")).isFalse();
    }

    @Test
    public void testLowerCase() {
        assertThat(LOWERCASE.apply("")).isTrue();
        assertThat(LOWERCASE.apply("TATA")).isFalse();
        assertThat(LOWERCASE.apply("tata")).isTrue();
        assertThat(LOWERCASE.apply("TaTa")).isFalse();
    }

}
