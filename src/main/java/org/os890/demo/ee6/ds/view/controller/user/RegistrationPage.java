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

import org.os890.demo.ee6.ds.backend.user.UserService;
import org.os890.demo.ee6.ds.domain.user.User;
import org.os890.demo.ee6.ds.view.config.Pages;
import org.os890.demo.ee6.ds.view.i18n.WebappMessageBundle;
import org.apache.deltaspike.core.api.scope.GroupedConversation;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.jsf.api.message.JsfMessage;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@GroupedConversationScoped
public class RegistrationPage implements Serializable {
    private static final long serialVersionUID = 3844502441069448490L;

    @Inject
    private UserService userService;

    @Inject
    private GroupedConversation conversation;

    @Inject
    private JsfMessage<WebappMessageBundle> webappMessages;

    private User user = new User();

    //cross-validation (e.g. repeated password) is possible with extval-constraints (or the bv-form-validator add-on)

    @Inject
    private UserHolder userHolder;

    public Class<? extends Pages.User> register() {
        if (userService.isRegistered(user.getUserName())) {
            webappMessages.addError().msgRegistrationFailed(user.getUserName());
            return Pages.User.Registration.class;
        }

        userService.save(user);
        webappMessages.addInfo().msgUserRegistered(user.getUserName());

        //in order to re-use the page-bean for the login-page
        conversation.close();

        return Pages.User.Login.class;
    }

    public Class<? extends Pages> login() {
        User foundUser = userService.findUser(user.getUserName());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            webappMessages.addInfo().msgLoginSuccessful();
            userHolder.setCurrentUser(foundUser);
            return Pages.InternalInfo.class;
        }

        webappMessages.addError().msgLoginFailed();
        userHolder.setCurrentUser(new User());

        return null;
    }

    public User getUser() {
        return user;
    }
}