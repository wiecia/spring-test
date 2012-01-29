package com.wiecia.springtest.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@XmlRootElement
public class Car extends AbstractEntity {

	private static final long serialVersionUID = -6146537281368286251L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 16)
	private String mark;

	@Column(length = 16)
	private String carModel;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String model) {
		this.carModel = model;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
