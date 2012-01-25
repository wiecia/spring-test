package com.wiecia.springtest.db;

import java.util.Date;

public interface SoftDeleteable {

	void setDeletedDate(Date deleteDate);

	Date getDeletedDate();

}