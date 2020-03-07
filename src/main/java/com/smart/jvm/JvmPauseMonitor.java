/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smart.jvm;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
public class JvmPauseMonitor {
    private static final String JVM_PAUSE_MONITOR_FEATURE_SWITCH_KEY = "jvm.pause.monitor";
    private long sleepTimeMs;

    public JvmPauseMonitor() {
        this.sleepTimeMs = 1000;
    }

    public void start() {
        Thread monitorThread = new Thread(new JVMMonitor());
        monitorThread.setName(JVM_PAUSE_MONITOR_FEATURE_SWITCH_KEY + "-thread");
        monitorThread.start();
    }

    private class JVMMonitor implements Runnable {
        @Override
        public void run() {
            while (true) {
                long startTime = Instant.now().toEpochMilli();
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException ie) {
                    return;
                }
                long endTime = Instant.now().toEpochMilli();
                long extraSleepTime = (endTime - startTime) - sleepTimeMs;

                log.info("program pause {} mills because of gc", extraSleepTime);
            }
        }
    }
}
