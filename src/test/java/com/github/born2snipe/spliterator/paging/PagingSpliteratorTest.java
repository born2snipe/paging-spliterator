/**
 * Copyright to the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.born2snipe.spliterator.paging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PagingSpliteratorTest {
    @Mock
    private PageLookup<Integer> pageLookup;

    @Test
    public void can_lookup_multiple_pages() {
        List<Integer> firstPage = asList(1, 2);
        List<Integer> secondPage = asList(3);

        when(pageLookup.lookupPage()).thenReturn(
                firstPage,
                secondPage,
                EMPTY_LIST
        );

        List<Integer> actual = toStream().collect(toList());

        assertEquals(asList(1, 2, 3), actual);
    }

    @Test
    public void can_lookup_a_single_page() {
        List<Integer> pageData = asList(1, 2, 3);
        when(pageLookup.lookupPage()).thenReturn(
                pageData,
                EMPTY_LIST
        );

        List<Integer> actual = toStream().collect(toList());

        assertEquals(pageData, actual);
    }

    @Test
    public void nothing_was_found() {
        when(pageLookup.lookupPage()).thenReturn(EMPTY_LIST);

        List<Integer> actual = toStream().collect(toList());

        assertTrue(actual.isEmpty());
    }

    private Stream<Integer> toStream() {
        return stream(new PagingSpliterator<>(pageLookup), false);
    }
}
