package com.raphaelborborema;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.liferay.portal.kernel.exception.SystemException;
import com.raphaelborborema.dataModels.MobilePhoneDataModel;
import com.raphaelborborema.model.MobilePhone;
import com.raphaelborborema.service.MobilePhoneLocalServiceUtil;
import com.raphaelborborema.utils.FacesMessagesUtil;


@ManagedBean(name ="phoneViewMB")
@ViewScoped
public class PhoneViewMB implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<MobilePhone> model;

	@PostConstruct
	private void initParameters(){

	}

	public String editPhone(){
		return "MobilePhoneEdit";
	}
	
	public LazyDataModel<MobilePhone> getModel() {
		if(model==null){
			this.model = new MobilePhoneDataModel();
		}
		return model;
	}

	public void setModel(LazyDataModel<MobilePhone> model) {
		this.model = model;
	}
	
	public void delete(Long id) throws SystemException{
		try {
			MobilePhone mobilePhone = MobilePhoneLocalServiceUtil.deleteMobilePhone(id);
			if(mobilePhone!=null){
				FacesMessagesUtil.addGlobalInfoMessage("record_deleted");
			}else{
				FacesMessagesUtil.addGlobalErrorMessage("erro_action");
			}
		} catch (Exception e) {
			FacesMessagesUtil.addGlobalErrorMessage("erro_action");
			e.printStackTrace();
		}
		
	}



	

}




