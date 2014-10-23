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

package org.bluelys.bluelife.fluent;


import org.bluelys.bluelife.functions.Extractor2;
import org.bluelys.bluelife.predicates.ThanPredicates;
import org.bluelys.bluelife.util.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.bluelys.bluelife.iterators.Iterators.from;
import static org.bluelys.bluelife.predicates.IntegerPredicates.*;
import static org.fest.assertions.Assertions.assertThat;


public class TestIFluentIterables {

    @DataProvider(name = "instances")
    public static Object[][] instances() {
        return new Object[][] {
                //                           ODD, EVEN, POS, NEG,SNEG,SPOS,ZERO, ODD_POS
                {newArrayList(1, 11, 2, 334), 2 , 2, 4, 0, 0, 4, 0, 2, 2},
                {newArrayList(-1, -11, -2, -334), 2 , 2, 0, 4, 4, 0, 0, 0, 0},
                {newArrayList(-1, 11, 2, -334), 2 , 2, 2, 2, 2, 2, 0, 1, 1},
                {newArrayList(0, 0, 0, 0), 0 , 4, 4, 4, 0, 0, 4, 4, 0}
        };
    }

    @Test
    public void testFluentIterablesWithPredicates() {

        assertThat(from(newArrayList(1, 11, 2, 334)).filter(ODD).size()).isEqualTo(2);
        assertThat(from(newArrayList(1, 11, 2, 334)).filter(POSITIVE).size()).isEqualTo(4);
        assertThat(from(newArrayList(1, 11, 2, 334)).filter(NEGATIVE).size()).isEqualTo(0);

        assertThat(from(newArrayList(-1, -11, -2, -334)).filter(ODD).size()).isEqualTo(2);
        assertThat(from(newArrayList(-1, -11, -2, -334)).filter(POSITIVE).size()).isEqualTo(0);
        assertThat(from(newArrayList(-1, -11, -2, -334)).filter(NEGATIVE).size()).isEqualTo(4);

        assertThat(from(newArrayList(-1, 11, 2, -334)).filter(ODD).size()).isEqualTo(2);
        assertThat(from(newArrayList(-1, 11, 2, -334)).filter(POSITIVE).size()).isEqualTo(2);
        assertThat(from(newArrayList(-1, 11, 2, -334)).filter(NEGATIVE).size()).isEqualTo(2);
    }

    @Test
    public void testFluentIterableToFluentMap() {

        assertThat(from(newArrayList(1, 11, 2, 334))
                .filter(ODD)
                .map(new Extractor2<Integer, Integer, Integer>() {
                    @Override
                    public Pair<Integer, Integer> extract(final Integer input) {
                        return new Pair<Integer, Integer>() {
                            @Override
                            public Integer getFirst() {
                                return input;
                            }

                            @Override
                            public Integer getSecond() {
                                return 10*input;
                            }
                        };
                    }
                })
                .filterKey(ThanPredicates.GREATER.than(11))
                .iterable()
                .size()
        ).isEqualTo(1);
    }

    @Test
    public void testFluentIterableToFluentMapWithFilterOnValue() {

        assertThat(from(newArrayList(-1, 11, 2, 334))
                        .filter(POSITIVE)
                        .map(new Extractor2<Integer, Integer, Integer>() {
                            @Override
                            public Pair<Integer, Integer> extract(final Integer input) {
                                return new Pair<Integer, Integer>() {
                                    @Override
                                    public Integer getFirst() {
                                        return input;
                                    }

                                    @Override
                                    public Integer getSecond() {
                                        return 1 + input;
                                    }
                                };
                            }
                        })
                        .filterValue(ODD)
                        .iterable()
                        .size()
        ).isEqualTo(2);
    }

}
