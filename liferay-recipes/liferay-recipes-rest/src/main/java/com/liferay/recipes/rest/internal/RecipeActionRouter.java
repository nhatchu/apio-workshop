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

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.ParentId;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.recipes.rest.model.RecipeDTO;
import com.liferay.recipes.rest.type.RecipeType;
import com.liferay.recipes.rest.type.RestaurantType;
import com.liferay.recipes.service.RecipeService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component
public class RecipeActionRouter implements ActionRouter<RecipeType> {

	@Retrieve
	public List<RecipeType> retrieveRecipes(
		@ParentId(RestaurantType.class) long groupId) {

		return _recipeService.getRecipesByGroupId(groupId, -1, -1)
			.stream()
			.map(RecipeDTO::new)
			.collect(Collectors.toList());
	}

	@Retrieve
	public RecipeType retrieveRecipe(@Id long id) throws PortalException {
		return new RecipeDTO(_recipeService.getRecipe(id));
	}

	@Reference
	private RecipeService _recipeService;

}