package com.wiecia.springtest.db;

import java.io.Serializable;

/**
 * @author wiecia
 */
public interface GenericEntity extends Serializable {

	/**
	 * Checks if entity is persisted.
	 * 
	 * @return true if object is persisted, false otherwise
	 */
	public boolean isSaved();

	/**
	 * Reads entity's id which is primary key in db.
	 * 
	 * @return entity's id
	 */
	public Long getId();

	/**
	 * Sets entity's id which is primary key id db.<br/>
	 * Generated by ORM, not to be set manually.
	 * 
	 * @param new entity's id
	 */
	public void setId(Long id);

}