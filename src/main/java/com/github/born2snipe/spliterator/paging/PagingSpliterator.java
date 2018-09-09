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

import java.util.Collection;
import java.util.Spliterator;
import java.util.function.Consumer;

public class PagingSpliterator<T> implements Spliterator<T> {
    private final PageLookup<T> pageLookup;

    public PagingSpliterator(PageLookup<T> pageLookup) {
        this.pageLookup = pageLookup;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        Collection<T> pageOfData = pageLookup.lookupPage();
        pageOfData.forEach(action);
        return pageOfData.size() > 0;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
