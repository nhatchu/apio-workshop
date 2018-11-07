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

package com.liferay.recipes.rest.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.UserGroupRoleModel;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.recipes.model.Recipe;

import java.util.List;

/**
 * @author Víctor Galán
 */
public class Utils {

	public static String getLogoURL(Organization organization) {
		long logoId = organization.getLogoId();

		return String.format(
			"%s/organization_logo?img_id=%s&t=%s",
			PortalUtil.getPathImage(), String.valueOf(logoId),
			WebServerServletTokenUtil.getToken(logoId));
	}

	public static Long getChefId(Organization organization) {
		Role role = RoleLocalServiceUtil.fetchRole(
			organization.getCompanyId(), "Chef");

		if (role == null) {
			return null;
		}

		long groupId = organization.getGroupId();

		List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
			.getUserGroupRolesByGroupAndRole(groupId, role.getRoleId());

		if (userGroupRoles.size() > 0) {
			UserGroupRoleModel userGroupRole = userGroupRoles.get(0);

			return userGroupRole.getUserId();
		}

		return null;
	}

	public static String getPortraitURL(User user) {
		String pathImage = PortalUtil.getPathImage();

		try {
			return UserConstants.getPortraitURL(
				pathImage, user.isMale(), user.getPortraitId(), user.getUserUuid());
		} catch (Exception e) {
			return null;
		}
	}

	public static String getCookTime(Recipe recipe) {
		return String.format(
			"PT%dH%dM", recipe.getHoursToMake(), recipe.getMinutesToMake());
	}

	public static List<String> getRecipeAssetTags(Recipe recipe) {
		List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Recipe.class.getName(), recipe.getRecipeId());

		return ListUtil.toList(assetTags, AssetTag::getName);
	}

	public static String getCategory(Recipe recipe) {
		List<AssetCategory> categories =
			AssetCategoryLocalServiceUtil.getCategories(
				Recipe.class.getName(), recipe.getRecipeId());

		if (categories.isEmpty()) {
			return null;
		}

		AssetCategory assetCategory = categories.get(0);

		return assetCategory.getName();
	}

	public static String getImageURL(Recipe recipe) {

		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				recipe.getImageFileEntryId());

			return DLUtil.getDownloadURL(
				fileEntry, fileEntry.getFileVersion(), null, "", false, false);
		}
		catch (PortalException e) {
			return null;
		}
	}
}
