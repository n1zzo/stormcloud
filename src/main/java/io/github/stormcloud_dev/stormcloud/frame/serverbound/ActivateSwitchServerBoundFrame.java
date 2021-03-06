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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ActivateSwitchServerBoundFrame extends ServerBoundFrame {

    private short x;
    private short y;

    public ActivateSwitchServerBoundFrame(short x, short y) {
        super((byte) 47);
        this.x = x;
        this.y = y;

    }

    @Override
    public int getLength() {
        return 21;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    @Override
    public void writeData(ByteBuf buf) {
        super.writeData(buf);
        buf.writeShort(getX());
        buf.writeShort(getY());
    }

}
