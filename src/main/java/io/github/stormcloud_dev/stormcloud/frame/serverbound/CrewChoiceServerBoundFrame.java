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
import io.netty.channel.ChannelHandlerContext;

public class CrewChoiceServerBoundFrame extends ServerBoundFrame {

    private short crewMember;

    public CrewChoiceServerBoundFrame(CrewMember crewMember) {
        super((byte) 45);
        this.crewMember = crewMember.getId();
    }

    public CrewChoiceServerBoundFrame(short crewMember) {
        super((byte) 45);
        this.crewMember = crewMember;
    }

    @Override
    public int getLength() {
        return 19;
    }

    public short getCrewMember() {
        return crewMember;
    }

    @Override
    public void writeData(ByteBuf buf) {
        super.writeData(buf);
        buf.writeShort(getCrewMember());
    }

}
