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
package org.os890.demo.ee6.ds.domain.user;

import org.os890.demo.ee6.ds.domain.AbstractDomainObject;

import javax.enterprise.inject.Typed;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Typed()
public class User extends AbstractDomainObject {
    private static final long serialVersionUID = 3810638653455000233L;

    @NotNull
    @Size(min = 2, max = 9, message = "invalid user name")
    private String userName;

    @NotNull
    @Size(min = 2, max = 255, message = "invalid first name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255, message = "invalid last name")
    private String lastName;

    //use e.g. a custom constraint in addition e.g. @Password
    private String password; //usually only a hash-value should be stored here

    public User() {
    }

    public User(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}