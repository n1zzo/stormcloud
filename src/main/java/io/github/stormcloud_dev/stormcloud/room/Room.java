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
package io.github.stormcloud_dev.stormcloud.room;

import io.github.stormcloud_dev.stormcloud.object.StormCloudObject;
import io.github.stormcloud_dev.stormcloud.object.StormCloudObjectFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class Room {

    private Set<StormCloudObject> objects;

    public Room() {
        objects = new HashSet<>();
    }

    public static Room load(String roomName) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        Room room = new Room();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(Room.class.getResourceAsStream(format("/rooms/%s.xml", roomName)));
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList instances = (NodeList) xPath.evaluate("//room/instances/instance", document, XPathConstants.NODESET);
        for (int i = 0; i < instances.getLength(); i++) {
            Node node = instances.item(i);
            NamedNodeMap attributes = node.getAttributes();
            String objName = attributes.getNamedItem("objName").getNodeValue();
            int x = parseInt(attributes.getNamedItem("x").getNodeValue());
            int y = parseInt(attributes.getNamedItem("y").getNodeValue());
            String name = attributes.getNamedItem("name").getNodeValue();
            boolean locked = attributes.getNamedItem("locked").getNodeValue().equals("1");
            String code = attributes.getNamedItem("code").getNodeValue();
            double scaleX = parseDouble(attributes.getNamedItem("scaleX").getNodeValue());
            double scaleY = parseDouble(attributes.getNamedItem("scaleY").getNodeValue());
            int colour = parseInt(attributes.getNamedItem("colour").getNodeValue());
            double rotation = parseDouble(attributes.getNamedItem("rotation").getNodeValue());
            room.addObject(StormCloudObjectFactory.createObject(objName, x, y, name, locked, code, scaleX, scaleY, colour, rotation));
        }
        return room;
    }

    public void addObject(StormCloudObject object) {
        if (object == null || objects.contains(object)) return;
        objects.add(object);
    }

}