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

package com.liferay.recipes.rest.internal;

import com.liferay.apio.architect.annotation.Actions.EntryPoint;
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.recipes.rest.model.OrganizationDTO;
import com.liferay.recipes.rest.type.RestaurantType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component
public class RestaurantActionRouter implements ActionRouter<RestaurantType> {

	@Retrieve
	public RestaurantType retrieve(@Id long id) throws PortalException {
		Organization organization = _organizationService.getOrganization(id);

		return new OrganizationDTO(organization);
	}

	@Retrieve
	@EntryPoint
	public List<RestaurantType> retrieve(User user) throws PortalException {
		List<Organization> organizations =
			_organizationService.getUserOrganizations(user.getUserId());

		return organizations.stream()
			.map(OrganizationDTO::new)
			.collect(Collectors.toList());
	}



	@Reference
	private OrganizationService _organizationService;

}