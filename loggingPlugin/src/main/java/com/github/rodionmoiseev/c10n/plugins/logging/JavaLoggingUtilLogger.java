/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package com.github.rodionmoiseev.c10n.plugins.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger implementation based on the default Java logging utility.
 */
public class JavaLoggingUtilLogger implements LoggerImplementation {
    private static final Map<LoggingLevel, Level> levelMapping = new HashMap<>();

    static {
        levelMapping.put(LoggingLevel.TRACE, Level.FINER);
        levelMapping.put(LoggingLevel.DEBUG, Level.FINE);
        levelMapping.put(LoggingLevel.INFO, Level.INFO);
        levelMapping.put(LoggingLevel.WARN, Level.WARNING);
        levelMapping.put(LoggingLevel.ERROR, Level.SEVERE);
    }

    @Override
    public void log(String logger,
                    LoggingLevel level,
                    String message,
                    Throwable cause,
                    LoggingPlugin.InvocationDetails details) {
        java.util.logging.Logger lgr = Logger.getLogger(logger);
        lgr.log(
                levelMapping.getOrDefault(level, Level.ALL),
                message,
                cause
        );
    }
}
