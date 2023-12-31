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
package org.apache.axis.test.interop.mock;

import java.util.HashMap;
import java.util.Map;

import jakarta.el.ValueExpression;
import jakarta.el.VariableMapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Variables extends VariableMapper {
    private static final Log log = LogFactory.getLog(Variables.class);
    
    private final Map<String,ValueExpression> variables =  new HashMap<String,ValueExpression>();
    
    public <T> void bind(String name, Class<T> clazz, T value) {
        if (log.isDebugEnabled()) {
            log.debug("Binding variable: " + name + "=" + value);
        }
        variables.put(name, new ValueExpressionLiteral<T>(clazz, value));
    }
      
    public ValueExpression resolveVariable(String name) {
        return variables.get(name);
    }
    
    public ValueExpression setVariable(String name, ValueExpression valueExpression) {
        return (variables.put(name, valueExpression));
    }
}