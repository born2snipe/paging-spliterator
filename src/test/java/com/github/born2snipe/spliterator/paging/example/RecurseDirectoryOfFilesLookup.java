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
package com.github.born2snipe.spliterator.paging.example;

import com.github.born2snipe.spliterator.paging.PageLookup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class RecurseDirectoryOfFilesLookup implements PageLookup<File> {
    private final List<File> files = new ArrayList<>();
    private final List<File> directories = new ArrayList<>();

    public RecurseDirectoryOfFilesLookup(File directory) {
        directories.add(directory);
    }

    @Override
    public Collection<File> lookupPage() {
        if (files.isEmpty() && directories.isEmpty()) {
            return Collections.emptyList();
        }

        if (files.isEmpty()) {
            files.addAll(queryAnotherDirectoryForFiles());
            return lookupPage();
        }

        File fileOrDirectory = files.remove(0);
        if (fileOrDirectory.isDirectory()) {
            directories.add(fileOrDirectory);
            return lookupPage();
        }

        return asList(fileOrDirectory);
    }

    private Collection<File> queryAnotherDirectoryForFiles() {
        if (directories.isEmpty()) {
            return Collections.emptyList();
        }

        File directory = directories.remove(0);
        return asList(directory.listFiles());
    }
}
