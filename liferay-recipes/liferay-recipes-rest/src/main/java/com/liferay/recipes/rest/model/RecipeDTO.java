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
import com.liferay.recipes.rest.util.Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author Víctor Galán
 */

@XmlRootElement
public class RecipeDTO {

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

	public RecipeDTO() {

	}

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

	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	@XmlElement
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@XmlElement
	public Date getPublishedDate() {
		return publishedDate;
	}

	@XmlElement
	public String getRegion() {
		return region;
	}

	@XmlElement
	public List<String> getIngredients() {
		return ingredients;
	}

	@XmlElement
	public List<String> getSteps() {
		return steps;
	}

	@XmlElement
	public long getUserId() {
		return userId;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public String getVideoURL() {
		return videoURL;
	}

	@XmlElement
	public String getCookTime() {
		return cookTime;
	}

	@XmlElement
	public String getCategory() {
		return category;
	}

	@XmlElement
	public List<String> getRecipeAssetTags() {
		return recipeAssetTags;
	}

	@XmlElement
	public String getImageURL() {
		return imageURL;
	}
}
