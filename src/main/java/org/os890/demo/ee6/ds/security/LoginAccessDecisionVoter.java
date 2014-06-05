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
package org.os890.demo.ee6.ds.security;

import org.apache.myfaces.extensions.cdi.core.api.security.AbstractAccessDecisionVoter;
import org.apache.myfaces.extensions.cdi.core.api.security.SecurityViolation;
import org.os890.demo.ee6.ds.view.controller.user.UserHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import java.util.Set;

@ApplicationScoped
public class LoginAccessDecisionVoter extends AbstractAccessDecisionVoter {
    private static final long serialVersionUID = -6332617547592896599L;

    @Inject
    private UserHolder userHolder;

    @Override
    protected void checkPermission(InvocationContext invocationContext,
                                   Set<SecurityViolation> violations) {
        if (!userHolder.isLoggedIn()) {
            violations.add(newSecurityViolation("{msgAccessDenied}"));
        }
    }
}
