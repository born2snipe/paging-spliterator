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
package com.github.born2snipe.spliterator.paging.example;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecurseDirectoryOfFilesLookupTest {
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void crazy_nesting_of_directories() throws IOException {
        File dir = tmp.newFolder("nest");
        for (int i = 0; i < 100; i++) {
            dir = new File(dir, String.valueOf(i));
            dir.mkdirs();
        }
        new File(dir, "1.txt").createNewFile();

        List<String> filenames = lookupFilenames();

        assertEquals(asList("1.txt"), filenames);
    }

    @Test
    public void only_a_directory_with_files() throws IOException {
        File dir = tmp.newFolder("1");
        new File(dir, "1.txt").createNewFile();
        new File(dir, "2.txt").createNewFile();

        List<String> filenames = lookupFilenames();

        assertEquals(asList("1.txt", "2.txt"), filenames);
    }

    @Test
    public void only_directories() throws IOException {
        tmp.newFolder("1");
        tmp.newFolder("2");

        List<String> filenames = lookupFilenames();

        assertTrue(filenames.isEmpty());
    }

    @Test
    public void stream_over_files_in_directory() throws IOException {
        tmp.newFile("1.txt");
        tmp.newFile("2.txt");
        tmp.newFile("3.txt");
        tmp.newFile("4.txt");

        List<String> filenames = lookupFilenames();

        assertEquals(asList("1.txt", "2.txt", "3.txt", "4.txt"), filenames);
    }

    private List<String> lookupFilenames() {
        return new RecurseDirectoryOfFilesLookup(tmp.getRoot())
                .stream()
                .map(File::getName)
                .sorted()
                .collect(toList());
    }
}
