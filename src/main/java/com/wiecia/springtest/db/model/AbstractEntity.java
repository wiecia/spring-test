package com.wiecia.springtest.db.model;

import com.wiecia.springtest.db.GenericEntity;

public abstract class AbstractEntity implements GenericEntity {

	private static final long serialVersionUID = -3902556131198005491L;

	@Override
	public boolean isSaved() {
		return getId() != null;
	}

}
