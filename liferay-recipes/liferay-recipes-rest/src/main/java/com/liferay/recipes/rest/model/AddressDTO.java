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

import com.liferay.portal.kernel.model.Address;
import com.liferay.recipes.rest.type.PostalAddressType;

/**
 * @author Víctor Galán
 */
public class AddressDTO implements PostalAddressType {

	private String street;
	private String zipCode;
	private String regionName;
	private String countryName;

	public AddressDTO(Address address) {
		countryName = address.getCountry().getName();
		zipCode = address.getZip();
		regionName = address.getRegion().getName();
		street = address.getStreet1();
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public String getRegionName() {
		return regionName;
	}

	@Override
	public String getCountryName() {
		return countryName;
	}
}
