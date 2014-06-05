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
package org.os890.demo.ee6.ds.view.controller.user;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.Conversation;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.Jsf;
import org.apache.myfaces.extensions.cdi.message.api.MessageContext;
import org.apache.myfaces.extensions.cdi.message.api.payload.MessageSeverity;
import org.os890.demo.ee6.ds.backend.user.UserService;
import org.os890.demo.ee6.ds.domain.user.User;
import org.os890.demo.ee6.ds.view.config.Pages;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class RegistrationPage implements Serializable {
    private static final long serialVersionUID = 3844502441069448490L;

    @Inject
    private UserService userService;

    @Inject
    private Conversation conversation;

    @Inject
    private @Jsf MessageContext messageContext;

    private User user = new User();

    //cross-validation (e.g. repeated password) is possible with extval-constraints (or the bv-form-validator add-on)

    @Inject
    private UserHolder userHolder;

    public Class<? extends Pages.User> register() {
        if (userService.isRegistered(user.getUserName())) {
            messageContext.message().text("{msgRegistrationFailed}").namedArgument("userName", user.getUserName()).payload(MessageSeverity.ERROR).add();
            return Pages.User.Registration.class;
        }

        userService.save(user);
        messageContext.message().text("{msgUserRegistered}").namedArgument("userName", user.getUserName()).payload(MessageSeverity.INFO).add();

        //in order to re-use the page-bean for the login-page
        conversation.close();

        return Pages.User.Login.class;
    }

    public Class<? extends Pages> login() {
        User foundUser = userService.findUser(user.getUserName());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            messageContext.message().text("{msgLoginSuccessful}").payload(MessageSeverity.INFO).add();
            userHolder.setCurrentUser(foundUser);
            return Pages.InternalInfo.class;
        }

        messageContext.message().text("{msgLoginFailed}").payload(MessageSeverity.ERROR).add();
        userHolder.setCurrentUser(new User());

        return null;
    }

    public User getUser() {
        return user;
    }
}