/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.raphaelborborema.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.raphaelborborema.model.MobilePhone;

import com.raphaelborborema.service.MobilePhoneLocalServiceUtil;

/**
 * @author raphaelborborema
 * @generated
 */
public abstract class MobilePhoneActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public MobilePhoneActionableDynamicQuery() throws SystemException {
		setBaseLocalService(MobilePhoneLocalServiceUtil.getService());
		setClass(MobilePhone.class);

		setClassLoader(com.raphaelborborema.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("id");
	}
}