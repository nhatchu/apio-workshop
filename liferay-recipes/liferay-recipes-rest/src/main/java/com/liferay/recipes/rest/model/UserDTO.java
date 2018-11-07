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
import com.liferay.recipes.rest.util.Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Víctor Galán
 */
@XmlRootElement
public class UserDTO {

	private long id;
	private String screenName;
	private String email;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String fullName;
	private String portraitURL;

	public UserDTO() {}

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

	@XmlElement
	public String getPortraitURL() {
		return portraitURL;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getScreenName() {
		return screenName;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	@XmlElement
	public String getLastName() {
		return lastName;
	}

	@XmlElement
	public String getJobTitle() {
		return jobTitle;
	}

	@XmlElement
	public String getFullName() {
		return fullName;
	}
}
