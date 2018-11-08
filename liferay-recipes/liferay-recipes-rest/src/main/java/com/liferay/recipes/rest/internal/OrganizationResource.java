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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.RecipeService;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import java.util.List;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
public class OrganizationResource {

	public Organization retrieve(long id) throws PortalException {
		return  _organizationService.getOrganization(id);
	}

	@GET
	public List<Organization> retrieve(User user) throws PortalException {
		return _organizationService.getUserOrganizations(user.getUserId());
	}

	public List<Recipe> retrieveRecipes(long groupId, User user)
		throws PortalException {

		Group organizationGroup = _groupService.getOrganizationGroup(user.getCompanyId(), groupId);

		return _recipeService.getRecipesByGroupId(organizationGroup.getGroupId(), -1, -1);
	}

	@Reference
	private RecipeService _recipeService;

	@Reference
	private OrganizationService _organizationService;

	@Reference
	private GroupLocalService _groupService;

}