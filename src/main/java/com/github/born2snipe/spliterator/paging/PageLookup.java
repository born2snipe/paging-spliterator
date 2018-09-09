/**
 *
 * Copyright to the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.born2snipe.spliterator.paging;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@FunctionalInterface
public interface PageLookup<T> {
    /**
     * The method that supplies the spliterator with a page of data to be consumed.
     *
     * @return a page of data, when you return an empty collection that signals to the spliterator that we have reached
     * the last page of data
     */
    Collection<T> lookupPage();

    default Stream<T> stream() {
        return StreamSupport.stream(new PagingSpliterator<>(this), false);
    }
}
