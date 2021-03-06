/*
 * Copyright 2014 StormCloud Development Group
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.github.stormcloud_dev.stormcloud.event.game;

import io.github.stormcloud_dev.stormcloud.event.Cancellable;
import io.github.stormcloud_dev.stormcloud.event.Event;
import io.github.stormcloud_dev.stormcloud.object.StormCloudObject;

public class ObjectDestroyEvent extends Event implements Cancellable {

    private StormCloudObject object;
    private boolean cancelled;

    public ObjectDestroyEvent(StormCloudObject object) {
        this.object = object;
    }

    public StormCloudObject getObject() {
        return object;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
