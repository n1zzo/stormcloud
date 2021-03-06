/*
 *   Copyright 2014 StormCloud Development Group
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
package io.github.stormcloud_dev.stormcloud.frame.serverbound;

import io.github.stormcloud_dev.stormcloud.CrewMember;
import io.netty.buffer.ByteBuf;

public class AddPlayerServerBoundFrame extends ServerBoundFrame {

    private double x;
    private double y;
    private double mId;
    private int clazz;
    private int hostPlayer;
    private String name;

    public AddPlayerServerBoundFrame(double x, double y, double mId, int clazz, int hostPlayer, String name) {
        super((byte) 3);
        this.x = x;
        this.y = y;
        this.mId = mId;
        this.clazz = clazz;
        this.hostPlayer = hostPlayer;
        this.name = name;
    }

    public AddPlayerServerBoundFrame(double x, double y, double mId, CrewMember clazz, int hostPlayer, String name) {
        super((byte) 3);
        this.x = x;
        this.y = y;
        this.mId = mId;
        this.clazz = clazz.getId();
        this.hostPlayer = hostPlayer;
        this.name = name;
    }

    @Override
    public int getLength() {
        return 50 + getName().getBytes().length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMId() {
        return mId;
    }

    public int getClazz() {
        return clazz;
    }

    public int getHostPlayer() {
        return hostPlayer;
    }

    public String getName() {
        return name;
    }

    @Override
    public void writeData(ByteBuf buf) {
        super.writeData(buf);
        buf.writeDouble(getX());
        buf.writeDouble(getY());
        buf.writeDouble(getMId());
        buf.writeInt(getClazz());
        buf.writeInt(getHostPlayer());
        for (byte b : getName().getBytes()) {
            buf.writeByte(b);
        }
        buf.writeByte(0);
    }
}
