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
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.RecipeService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
@Path("recipe")
public class RecipeResource {

	@GET
	@Path("{id}")
	public String retrieve(@PathParam("id") long id) throws PortalException {
		return _recipeService.getRecipe(id).getName();
	}

	@Reference
	private RecipeService _recipeService;

}