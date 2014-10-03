package com.raphaelborborema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.raphaelborborema.model.MobilePhone;
import com.raphaelborborema.service.MobilePhoneLocalServiceUtil;
import com.raphaelborborema.service.persistence.MobilePhoneUtil;
import com.raphaelborborema.utils.FacesMessagesUtil;
import com.raphaelborborema.utils.JsfUtil;


@ManagedBean(name ="phoneEditMB")
@ViewScoped
public class PhoneEditMB implements Serializable{

	private MobilePhone phone;
	private transient String action;
	private transient Long beanId;

	private static final long serialVersionUID = -2653055197059240828L;


	public PhoneEditMB() {
		System.out.println("passo1");
	}

	public void initBean() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			String id = paramMap.get(JsfUtil.ID);
			if(id==null){
				setBeanId(CounterLocalServiceUtil.increment());
				setPhone(MobilePhoneUtil.create(getBeanId()));
				setAction(JsfUtil.INSERT_ACTION);
			}else{
				setBeanId(Long.valueOf(id));
				setPhone( MobilePhoneLocalServiceUtil.getMobilePhone(getBeanId()));
				setAction(paramMap.get(JsfUtil.ACTION));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@PostConstruct
	private void initParameters(){

		initBean();
	}


	public String save() throws SystemException{
		try {

			if(validatePhone()){
				if(isInsertAction()){
					MobilePhoneLocalServiceUtil.addMobilePhone(getPhone());
				}else{
					MobilePhoneLocalServiceUtil.updateMobilePhone(getPhone());
				}
				FacesMessagesUtil.addGlobalInfoMessage("record_saved");
				return listPhones();
			}else{
				return"";
			}
		} catch (Exception e) {
			FacesMessagesUtil.addGlobalErrorMessage("erro_action");
			return"";
		}

	}


	public boolean validatePhone(){

		if(getPhone().getName().length()>75){
			FacesMessagesUtil.addGlobalErrorMessage("erro_validation_size", "Name","75");
			return false;
		}
		if(getPhone().getBrand().length()>75){
			FacesMessagesUtil.addGlobalErrorMessage("erro_validation_size","Brand","75");
			return false;
		}
		if(getPhone().getDescription().length()>255){
			FacesMessagesUtil.addGlobalErrorMessage("erro_validation_size","Description","75");
			return false;
		}

		return true;
	}

	public MobilePhone getPhone() {
		return phone;
	}

	public void setPhone(MobilePhone phone) {
		this.phone = phone;
	}



	public String listPhones(){
		return "MobilePhoneList";
	}



	public boolean isViewAction(){
		return getAction().equals(JsfUtil.VIEW_ACTION);
	}
	public boolean isInsertAction(){
		return getAction().equals(JsfUtil.INSERT_ACTION);
	}
	public boolean isUpdateAction(){
		return getAction().equals(JsfUtil.UPDATE_ACTION);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public long getBeanId() {
		return beanId;
	}

	public void setBeanId(Long beanId) {
		this.beanId = beanId;
	}

	//FacesContext facesContext = FacesContext.getCurrentInstance();
	//facesContext.getExternalContext().getFlash().put("id", phone.getname());
}




