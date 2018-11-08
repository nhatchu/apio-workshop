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

import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.rest.type.RecipeType;
import com.liferay.recipes.rest.util.Utils;

import java.util.Date;
import java.util.List;

/**
 * @author Víctor Galán
 */
public class RecipeDTO implements RecipeType {

	private Date createDate;
	private Date modifiedDate;
	private Date publishedDate;
	private String region;
	private List<String> ingredients;
	private List<String> steps;
	private long userId;
	private long id;
	private String videoURL;
	private String cookTime;
	private String category;
	private List<String> recipeAssetTags;
	private String imageURL;

	public RecipeDTO(Recipe recipe) {
		id = recipe.getRecipeId();
		createDate = recipe.getCreateDate();
		modifiedDate = recipe.getModifiedDate();
		publishedDate = recipe.getPublishedDate();
		region = recipe.getRegion();
		ingredients = recipe.getIngredients();
		steps = recipe.getSteps();
		userId = recipe.getUserId();
		videoURL = recipe.getVideoURL();
		cookTime = Utils.getCookTime(recipe);
		category = Utils.getCategory(recipe);
		recipeAssetTags = Utils.getRecipeAssetTags(recipe);
		imageURL = Utils.getImageURL(recipe);
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public Date getPublishedDate() {
		return publishedDate;
	}

	@Override
	public String getRegion() {
		return region;
	}

	@Override
	public List<String> getIngredients() {
		return ingredients;
	}

	@Override
	public List<String> getSteps() {
		return steps;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getVideoURL() {
		return videoURL;
	}

	@Override
	public String getCookTime() {
		return cookTime;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public List<String> getRecipeAssetTags() {
		return recipeAssetTags;
	}

	@Override
	public String getImageURL() {
		return imageURL;
	}
}
