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
package org.os890.demo.ee6.ds.view.config;

import org.apache.myfaces.extensions.cdi.core.api.config.view.DefaultErrorView;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.security.Secured;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PageBean;
import org.os890.demo.ee6.ds.security.LoginAccessDecisionVoter;
import org.os890.demo.ee6.ds.view.controller.user.RegistrationPage;

import static org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page.NavigationMode.REDIRECT;

@Page(navigation = REDIRECT)
public interface Pages extends ViewConfig {
    @Secured(LoginAccessDecisionVoter.class)
    interface Secure {}

    interface User extends Pages {
        @PageBean(RegistrationPage.class)
        @Page class Login extends DefaultErrorView implements User {}

        @Page class Registration implements User {}
    }

    @Page class InternalInfo implements Secure, Pages {}
}
