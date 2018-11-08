/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.recipes.rest.model;

import com.liferay.portal.kernel.model.User;
import com.liferay.recipes.rest.type.PersonType;
import com.liferay.recipes.rest.util.Utils;

/**
 * @author Víctor Galán
 */
public class UserDTO implements PersonType {

	private long id;
	private String screenName;
	private String email;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String fullName;
	private String portraitURL;

	public UserDTO(User user) {
		id = user.getUserId();
		screenName = user.getScreenName();
		email = user.getEmailAddress();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		jobTitle = user.getJobTitle();
		fullName = user.getFullName();
		portraitURL = Utils.getPortraitURL(user);
	}

	@Override
	public String getPortraitURL() {
		return portraitURL;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getScreenName() {
		return screenName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getJobTitle() {
		return jobTitle;
	}

	@Override
	public String getFullName() {
		return fullName;
	}
}
