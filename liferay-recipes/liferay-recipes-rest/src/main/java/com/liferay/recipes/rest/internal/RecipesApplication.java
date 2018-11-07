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


import org.osgi.service.component.annotations.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component(
	property = {
		"osgi.jaxrs.application.base=/rest",
		"osgi.jaxrs.name=recipes-application",
		"auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes=*"
	},
	service = Application.class
)
public class RecipesApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Produces(TEXT_PLAIN)
	@Path("hello")
	public String helloWorld() {
		return "Hello World";
	}

}