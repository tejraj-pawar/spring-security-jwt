/*
 * This class is required for creating a response containing the JWT to be returned to the user.
 * */
package com.yolo.springsecurityjwt.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwt;

	public JwtResponse(String jwttoken) {
		this.jwt = jwttoken;
	}

	public String getToken() {
		return this.jwt;
	}
}