package com.raphaelborborema.dataModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.util.comparator.GroupNameComparator;
import com.raphaelborborema.model.MobilePhone;
import com.raphaelborborema.model.MobilePhoneModel;
import com.raphaelborborema.model.impl.MobilePhoneImpl;
import com.raphaelborborema.service.ClpSerializer;
import com.raphaelborborema.service.MobilePhoneLocalServiceUtil;

public class MobilePhoneDataModel extends LazyDataModel<MobilePhone> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MobilePhoneDataModel() {

	}

	

	@Override
	public MobilePhone getRowData(String rowKey) {

		try {
			
			return MobilePhoneLocalServiceUtil.getMobilePhone(new Long(rowKey));
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Object getRowKey(MobilePhone mobilePhone) {
		return mobilePhone.getId();
	}

	@Override
	public List<MobilePhone> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {


		int from = first;
		int to = (first + pageSize);

		List<MobilePhone> mobilePhones = new ArrayList<MobilePhone>();

		try {
			

			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MobilePhone.class);
			
			

		

			// name
			if (filters.containsKey("name")) {

				String value = filters.get("name");

				if (value != null && !value.isEmpty()) {
					dynamicQuery.add(RestrictionsFactoryUtil.ilike("name", "%" + value + "%"));
				}

			}

			/**
			 * Sorting of results
			 */

			Boolean ascending = true;
			if (sortOrder.name().equals("DESCENDING")) {
				ascending = false;
			}

			//OrderByComparator order = new GroupNameComparator(ascending);
			 OrderByComparator comparator = OrderByComparatorFactoryUtil.create(MobilePhoneImpl.TABLE_NAME, "name", ascending);
			
			
			mobilePhones = MobilePhoneLocalServiceUtil.dynamicQuery(dynamicQuery, -1, -1,comparator);

			this.setRowCount(mobilePhones.size());

			if (mobilePhones.size() < to) {
				mobilePhones = mobilePhones.subList(from, mobilePhones.size());
			} else {
				mobilePhones = mobilePhones.subList(from, to);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return mobilePhones;
	}

}
