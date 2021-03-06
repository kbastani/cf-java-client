/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.operations.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities for dealing with {@link Date}s
 */
public final class Dates {

    private static final SimpleDateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    private static final Object MONITOR = new Object();

    private Dates() {
    }

    /**
     * Parses a string in {@code ISO8601} format to a {@link Date} object
     *
     * @param s the string to parse
     * @return the parsed {@link Date}
     * @throws ParseException if the string cannot be parsed according to {@code ISO8601}
     */
    public static Date parse(String s) throws ParseException {
        synchronized (MONITOR) {
            return ISO8601.parse(s);
        }
    }

}
