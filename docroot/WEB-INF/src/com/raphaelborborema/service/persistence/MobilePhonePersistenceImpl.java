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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.raphaelborborema.NoSuchMobilePhoneException;

import com.raphaelborborema.model.MobilePhone;
import com.raphaelborborema.model.impl.MobilePhoneImpl;
import com.raphaelborborema.model.impl.MobilePhoneModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the mobile phone service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author raphaelborborema
 * @see MobilePhonePersistence
 * @see MobilePhoneUtil
 * @generated
 */
public class MobilePhonePersistenceImpl extends BasePersistenceImpl<MobilePhone>
	implements MobilePhonePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MobilePhoneUtil} to access the mobile phone persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MobilePhoneImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, MobilePhoneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, MobilePhoneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, MobilePhoneImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByname",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, MobilePhoneImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByname",
			new String[] { String.class.getName() },
			MobilePhoneModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByname",
			new String[] { String.class.getName() });

	/**
	 * Returns all the mobile phones where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findByname(String name) throws SystemException {
		return findByname(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile phones where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.raphaelborborema.model.impl.MobilePhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of mobile phones
	 * @param end the upper bound of the range of mobile phones (not inclusive)
	 * @return the range of matching mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findByname(String name, int start, int end)
		throws SystemException {
		return findByname(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile phones where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.raphaelborborema.model.impl.MobilePhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of mobile phones
	 * @param end the upper bound of the range of mobile phones (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findByname(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<MobilePhone> list = (List<MobilePhone>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MobilePhone mobilePhone : list) {
				if (!Validator.equals(name, mobilePhone.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MOBILEPHONE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MobilePhoneModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<MobilePhone>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MobilePhone>(list);
				}
				else {
					list = (List<MobilePhone>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first mobile phone in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile phone
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a matching mobile phone could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone findByname_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchMobilePhoneException, SystemException {
		MobilePhone mobilePhone = fetchByname_First(name, orderByComparator);

		if (mobilePhone != null) {
			return mobilePhone;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMobilePhoneException(msg.toString());
	}

	/**
	 * Returns the first mobile phone in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile phone, or <code>null</code> if a matching mobile phone could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone fetchByname_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<MobilePhone> list = findByname(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile phone in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile phone
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a matching mobile phone could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone findByname_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchMobilePhoneException, SystemException {
		MobilePhone mobilePhone = fetchByname_Last(name, orderByComparator);

		if (mobilePhone != null) {
			return mobilePhone;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMobilePhoneException(msg.toString());
	}

	/**
	 * Returns the last mobile phone in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile phone, or <code>null</code> if a matching mobile phone could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone fetchByname_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByname(name);

		if (count == 0) {
			return null;
		}

		List<MobilePhone> list = findByname(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the mobile phones before and after the current mobile phone in the ordered set where name = &#63;.
	 *
	 * @param id the primary key of the current mobile phone
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile phone
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone[] findByname_PrevAndNext(long id, String name,
		OrderByComparator orderByComparator)
		throws NoSuchMobilePhoneException, SystemException {
		MobilePhone mobilePhone = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			MobilePhone[] array = new MobilePhoneImpl[3];

			array[0] = getByname_PrevAndNext(session, mobilePhone, name,
					orderByComparator, true);

			array[1] = mobilePhone;

			array[2] = getByname_PrevAndNext(session, mobilePhone, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MobilePhone getByname_PrevAndNext(Session session,
		MobilePhone mobilePhone, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MOBILEPHONE_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MobilePhoneModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mobilePhone);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MobilePhone> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mobile phones where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByname(String name) throws SystemException {
		for (MobilePhone mobilePhone : findByname(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(mobilePhone);
		}
	}

	/**
	 * Returns the number of mobile phones where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByname(String name) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MOBILEPHONE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "mobilePhone.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "mobilePhone.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(mobilePhone.name IS NULL OR mobilePhone.name = '')";

	public MobilePhonePersistenceImpl() {
		setModelClass(MobilePhone.class);
	}

	/**
	 * Caches the mobile phone in the entity cache if it is enabled.
	 *
	 * @param mobilePhone the mobile phone
	 */
	@Override
	public void cacheResult(MobilePhone mobilePhone) {
		EntityCacheUtil.putResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneImpl.class, mobilePhone.getPrimaryKey(), mobilePhone);

		mobilePhone.resetOriginalValues();
	}

	/**
	 * Caches the mobile phones in the entity cache if it is enabled.
	 *
	 * @param mobilePhones the mobile phones
	 */
	@Override
	public void cacheResult(List<MobilePhone> mobilePhones) {
		for (MobilePhone mobilePhone : mobilePhones) {
			if (EntityCacheUtil.getResult(
						MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
						MobilePhoneImpl.class, mobilePhone.getPrimaryKey()) == null) {
				cacheResult(mobilePhone);
			}
			else {
				mobilePhone.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mobile phones.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MobilePhoneImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MobilePhoneImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mobile phone.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MobilePhone mobilePhone) {
		EntityCacheUtil.removeResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneImpl.class, mobilePhone.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MobilePhone> mobilePhones) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MobilePhone mobilePhone : mobilePhones) {
			EntityCacheUtil.removeResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
				MobilePhoneImpl.class, mobilePhone.getPrimaryKey());
		}
	}

	/**
	 * Creates a new mobile phone with the primary key. Does not add the mobile phone to the database.
	 *
	 * @param id the primary key for the new mobile phone
	 * @return the new mobile phone
	 */
	@Override
	public MobilePhone create(long id) {
		MobilePhone mobilePhone = new MobilePhoneImpl();

		mobilePhone.setNew(true);
		mobilePhone.setPrimaryKey(id);

		return mobilePhone;
	}

	/**
	 * Removes the mobile phone with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the mobile phone
	 * @return the mobile phone that was removed
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone remove(long id)
		throws NoSuchMobilePhoneException, SystemException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the mobile phone with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mobile phone
	 * @return the mobile phone that was removed
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone remove(Serializable primaryKey)
		throws NoSuchMobilePhoneException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MobilePhone mobilePhone = (MobilePhone)session.get(MobilePhoneImpl.class,
					primaryKey);

			if (mobilePhone == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMobilePhoneException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mobilePhone);
		}
		catch (NoSuchMobilePhoneException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MobilePhone removeImpl(MobilePhone mobilePhone)
		throws SystemException {
		mobilePhone = toUnwrappedModel(mobilePhone);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mobilePhone)) {
				mobilePhone = (MobilePhone)session.get(MobilePhoneImpl.class,
						mobilePhone.getPrimaryKeyObj());
			}

			if (mobilePhone != null) {
				session.delete(mobilePhone);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mobilePhone != null) {
			clearCache(mobilePhone);
		}

		return mobilePhone;
	}

	@Override
	public MobilePhone updateImpl(
		com.raphaelborborema.model.MobilePhone mobilePhone)
		throws SystemException {
		mobilePhone = toUnwrappedModel(mobilePhone);

		boolean isNew = mobilePhone.isNew();

		MobilePhoneModelImpl mobilePhoneModelImpl = (MobilePhoneModelImpl)mobilePhone;

		Session session = null;

		try {
			session = openSession();

			if (mobilePhone.isNew()) {
				session.save(mobilePhone);

				mobilePhone.setNew(false);
			}
			else {
				session.merge(mobilePhone);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MobilePhoneModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mobilePhoneModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mobilePhoneModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { mobilePhoneModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		EntityCacheUtil.putResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
			MobilePhoneImpl.class, mobilePhone.getPrimaryKey(), mobilePhone);

		return mobilePhone;
	}

	protected MobilePhone toUnwrappedModel(MobilePhone mobilePhone) {
		if (mobilePhone instanceof MobilePhoneImpl) {
			return mobilePhone;
		}

		MobilePhoneImpl mobilePhoneImpl = new MobilePhoneImpl();

		mobilePhoneImpl.setNew(mobilePhone.isNew());
		mobilePhoneImpl.setPrimaryKey(mobilePhone.getPrimaryKey());

		mobilePhoneImpl.setId(mobilePhone.getId());
		mobilePhoneImpl.setName(mobilePhone.getName());
		mobilePhoneImpl.setBrand(mobilePhone.getBrand());
		mobilePhoneImpl.setDescription(mobilePhone.getDescription());
		mobilePhoneImpl.setReleaseDate(mobilePhone.getReleaseDate());
		mobilePhoneImpl.setPrice(mobilePhone.getPrice());

		return mobilePhoneImpl;
	}

	/**
	 * Returns the mobile phone with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the mobile phone
	 * @return the mobile phone
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMobilePhoneException, SystemException {
		MobilePhone mobilePhone = fetchByPrimaryKey(primaryKey);

		if (mobilePhone == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMobilePhoneException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mobilePhone;
	}

	/**
	 * Returns the mobile phone with the primary key or throws a {@link com.raphaelborborema.NoSuchMobilePhoneException} if it could not be found.
	 *
	 * @param id the primary key of the mobile phone
	 * @return the mobile phone
	 * @throws com.raphaelborborema.NoSuchMobilePhoneException if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone findByPrimaryKey(long id)
		throws NoSuchMobilePhoneException, SystemException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the mobile phone with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mobile phone
	 * @return the mobile phone, or <code>null</code> if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		MobilePhone mobilePhone = (MobilePhone)EntityCacheUtil.getResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
				MobilePhoneImpl.class, primaryKey);

		if (mobilePhone == _nullMobilePhone) {
			return null;
		}

		if (mobilePhone == null) {
			Session session = null;

			try {
				session = openSession();

				mobilePhone = (MobilePhone)session.get(MobilePhoneImpl.class,
						primaryKey);

				if (mobilePhone != null) {
					cacheResult(mobilePhone);
				}
				else {
					EntityCacheUtil.putResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
						MobilePhoneImpl.class, primaryKey, _nullMobilePhone);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(MobilePhoneModelImpl.ENTITY_CACHE_ENABLED,
					MobilePhoneImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mobilePhone;
	}

	/**
	 * Returns the mobile phone with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the mobile phone
	 * @return the mobile phone, or <code>null</code> if a mobile phone with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MobilePhone fetchByPrimaryKey(long id) throws SystemException {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the mobile phones.
	 *
	 * @return the mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile phones.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.raphaelborborema.model.impl.MobilePhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile phones
	 * @param end the upper bound of the range of mobile phones (not inclusive)
	 * @return the range of mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile phones.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.raphaelborborema.model.impl.MobilePhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile phones
	 * @param end the upper bound of the range of mobile phones (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MobilePhone> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MobilePhone> list = (List<MobilePhone>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MOBILEPHONE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MOBILEPHONE;

				if (pagination) {
					sql = sql.concat(MobilePhoneModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MobilePhone>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MobilePhone>(list);
				}
				else {
					list = (List<MobilePhone>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the mobile phones from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (MobilePhone mobilePhone : findAll()) {
			remove(mobilePhone);
		}
	}

	/**
	 * Returns the number of mobile phones.
	 *
	 * @return the number of mobile phones
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MOBILEPHONE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the mobile phone persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.raphaelborborema.model.MobilePhone")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MobilePhone>> listenersList = new ArrayList<ModelListener<MobilePhone>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MobilePhone>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(MobilePhoneImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_MOBILEPHONE = "SELECT mobilePhone FROM MobilePhone mobilePhone";
	private static final String _SQL_SELECT_MOBILEPHONE_WHERE = "SELECT mobilePhone FROM MobilePhone mobilePhone WHERE ";
	private static final String _SQL_COUNT_MOBILEPHONE = "SELECT COUNT(mobilePhone) FROM MobilePhone mobilePhone";
	private static final String _SQL_COUNT_MOBILEPHONE_WHERE = "SELECT COUNT(mobilePhone) FROM MobilePhone mobilePhone WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mobilePhone.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MobilePhone exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MobilePhone exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MobilePhonePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static MobilePhone _nullMobilePhone = new MobilePhoneImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MobilePhone> toCacheModel() {
				return _nullMobilePhoneCacheModel;
			}
		};

	private static CacheModel<MobilePhone> _nullMobilePhoneCacheModel = new CacheModel<MobilePhone>() {
			@Override
			public MobilePhone toEntityModel() {
				return _nullMobilePhone;
			}
		};
}