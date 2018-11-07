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
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.rest.model.OrganizationDTO;
import com.liferay.recipes.rest.model.RecipeDTO;
import com.liferay.recipes.service.RecipeService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=recipes-application)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("organization")
public class OrganizationResource {

	@GET
	@Path("{id}")
	public OrganizationDTO retrieve(@PathParam("id") long id) throws PortalException {
		Organization organization = _organizationService.getOrganization(id);

		return new OrganizationDTO(organization);
	}

	@GET
	public List<OrganizationDTO> retrieve(@Context User user) throws PortalException {
		List<Organization> organizations =
			_organizationService.getUserOrganizations(user.getUserId());

		return organizations.stream()
			.map(OrganizationDTO::new)
			.collect(Collectors.toList());
	}

	@GET
	@Path("{id}/recipes")
	public List<RecipeDTO> retrieveRecipes(@PathParam("id") long groupId) {
		return _recipeService.getRecipesByGroupId(groupId, -1, -1)
			.stream()
			.map(RecipeDTO::new)
			.collect(Collectors.toList());
	}

	@Reference
	private RecipeService _recipeService;
	@Reference
	private OrganizationService _organizationService;

}