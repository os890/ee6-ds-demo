/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.os890.demo.ee6.ds.view.menu;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowContext;
import org.os890.demo.ee6.ds.view.config.Pages;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class MenuBean {
    @Inject
    private WindowContext windowContext;

    public Class<? extends Pages> login() {
        windowContext.closeConversations();
        return Pages.User.Login.class;
    }

    public Class<? extends Pages> logout() {
        windowContext.close();
        return Pages.User.Login.class;
    }

    public Class<? extends Pages> register() {
        windowContext.closeConversations();
        return Pages.User.Registration.class;
    }

    public Class<? extends Pages> internalInfo() {
        windowContext.closeConversations();
        return Pages.InternalInfo.class;
    }
}
