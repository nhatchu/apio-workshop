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

package com.liferay.recipes.rest.type;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

import java.util.Date;
import java.util.List;

/**
 * @author Víctor Galán
 */
@Type("Recipe")
public interface RecipeType extends Identifier<Long> {

	@Field("createDate")
	public Date getCreateDate();

	@Field("modifiedDate")
	public Date getModifiedDate();

	@Field("publishedDate")
	public Date getPublishedDate();

	@Field("region")
	public String getRegion();

	@Field("ingredients")
	public List<String> getIngredients();

	@Field("steps")
	public List<String> getSteps();

	@Field("userId")
	public long getUserId();

	@Id
	public long getId();

	@Field("videoUrl")
	public String getVideoURL();

	@Field("cookTime")
	public String getCookTime();

	@Field("category")
	public String getCategory();

	@Field("recipeAssetTags")
	public List<String> getRecipeAssetTags();

	@Field("imageUrl")
	public String getImageURL();
}
