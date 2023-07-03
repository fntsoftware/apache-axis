/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axis.tools.ant.wsdl;

import javax.wsdl.Service;

import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.toJava.Emitter;
import org.apache.axis.wsdl.toJava.JavaServiceWriter;

public class MyServiceWriter extends JavaServiceWriter {
    public MyServiceWriter(Emitter emitter, Service service, SymbolTable symbolTable) {
        super(emitter, service, symbolTable);
    }

    protected void setGenerators() {
        super.setGenerators();
        if (serviceImplWriter != null) {
            ServiceEntry sEntry = symbolTable.getServiceEntry(service.getQName());
            serviceImplWriter = new MyServiceImplWriter(emitter, sEntry, symbolTable);
        }
    }
}
