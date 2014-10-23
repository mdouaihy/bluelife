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

package org.bluelys.bluelife.iterators;


import com.google.common.collect.Lists;
import org.bluelys.bluelife.predicates.Predicates;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.bluelys.bluelife.iterators.Iterators.from;
import static org.bluelys.bluelife.predicates.StringPredicates.LOWERCASE;
import static org.bluelys.bluelife.predicates.StringPredicates.UPPERCASE;
import static org.fest.assertions.Assertions.assertThat;


public class TestIterators {

    @DataProvider(name = "instances")
    public static Object[][] instances() {
        return new Object[][] {
                {Lists.newArrayList("1", "11", "2", "334"), 2 },
                {Lists.newArrayList(), 0 },
                {Lists.newArrayList("1", "1", "2", "4"), 4 },
                {Lists.newArrayList("1", "1", "2", "44"), 3 }
        };
    }

    @DataProvider(name = "string_predicates")
    public static Object[][] stringPredicates() {
        return new Object[][] {
                {Lists.newArrayList("1", "11", "2", "334"), 4, 4 },
                {Lists.newArrayList(), 0, 0},
                {Lists.newArrayList("A", "a", "Bc", "BB"), 2, 1},
                {Lists.newArrayList("BC", "a", "zzz", "BVe"), 1, 2 }
        };
    }

    private static int size(Iterator<String> iterator) {
        int elements = 0;
        while (iterator.hasNext()) {
            iterator.next();
            elements++;
        }
        return elements;
    }

    @Test(dataProvider = "instances")
    public void testFilter(List<String> instance, Integer expectedResult) {
        Iterator<String> iterator = from(instance).filter(new Predicates.BasePredicate<String>() {
            @Override
            public boolean apply(String element) {
                return element.length() == 1;
            }
        }).iterator();
        assertThat(size(iterator)).isEqualTo(expectedResult);
    }

    @Test(dataProvider = "string_predicates")
    public void testFilter(List<String> instance, Integer expectedUpperCases, Integer expectedLowerCases) {
        Iterator<String> upperCaseIterator = from(instance).filter(UPPERCASE).iterator();
        Iterator<String> upperLowerIterator = from(instance).filter(LOWERCASE).iterator();
        Iterator<String> upperLowerCaseIterator = from(instance).filter(UPPERCASE).filter(LOWERCASE).iterator();

        assertThat(size(upperCaseIterator)).isEqualTo(expectedUpperCases);
        assertThat(size(upperLowerIterator)).isEqualTo(expectedLowerCases);
        assertThat(size(upperLowerIterator)).isEqualTo(0);
    }
}
