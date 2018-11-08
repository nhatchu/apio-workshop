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
import com.liferay.apio.architect.annotation.Vocabulary;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.LinkedModel;
import com.liferay.apio.architect.annotation.Vocabulary.RelatedCollection;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

/**
 * @author Alejandro Hernández
 */
@Type("Restaurant")
public interface RestaurantType extends Identifier<Long> {

	@Field("chefId")
	@LinkedModel(PersonType.class)
	public Long getChefId();

	@Id
	public long getId();

	@Field("name")
	public String getName();

	@Field("logoUrl")
	public String getLogoURL();

	@Field("address")
	public PostalAddressType getAddress();

	@Field("recipes")
	@RelatedCollection(RecipeType.class)
	public default long getRecipes() {
		return getId();
	}
}