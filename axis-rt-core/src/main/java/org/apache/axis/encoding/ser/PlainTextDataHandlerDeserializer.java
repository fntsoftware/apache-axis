/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

package org.apache.axis.encoding.ser;

import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.encoding.DeserializationContext;
import org.apache.commons.logging.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import jakarta.activation.DataHandler;
import java.io.IOException;

/**
 * text/plain DataHandler Deserializer
 * Modified by Russell Butek (butek@us.ibm.com)
 */
public class PlainTextDataHandlerDeserializer extends JAFDataHandlerDeserializer {
    protected static Log log =
            LogFactory.getLog(PlainTextDataHandlerDeserializer.class.getName());

    public void startElement(String namespace, String localName,
                             String prefix, Attributes attributes,
                             DeserializationContext context)
        throws SAXException {

        super.startElement(namespace, localName, prefix, attributes, context);

        if (getValue() instanceof DataHandler) {
            try {
                DataHandler dh = (DataHandler) getValue();
                setValue(dh.getContent());
            }
            catch (IOException ioe) {
            }
        }
    } // startElement
} // class PlainTextDataHandlerDeserializer
