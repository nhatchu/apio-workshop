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

import com.liferay.portal.kernel.model.Organization;
import com.liferay.recipes.rest.util.Utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Víctor Galán
 */

@XmlRootElement
public class OrganizationDTO {

	private long id;
	private String name;
	private String logoURL;
	private AddressDTO address;
	private Long chefId;

	public OrganizationDTO() {
	}

	public OrganizationDTO(Organization organization) {
		id = organization.getGroupId();
		name = organization.getName();
		logoURL = Utils.getLogoURL(organization);
		address = new AddressDTO(organization.getAddress());
		chefId = Utils.getChefId(organization);
	}

	@XmlElement
	public Long getChefId() {
		return chefId;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement
	public String getLogoURL() {
		return logoURL;
	}

	@XmlElement
	public AddressDTO getAddress() {
		return address;
	}
}
