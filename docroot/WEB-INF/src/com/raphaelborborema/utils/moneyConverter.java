package com.raphaelborborema.utils;

import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "moneyConverter")
public class moneyConverter implements Converter {
	
	private  String removeFormatDecimalNumber(String valor) {
		String retorno = null;
		if ((valor != null) && !valor.isEmpty()) {
			retorno = valor.replaceAll("\\.", "").replaceAll(",", ".");
		}
		return retorno;
	}
	
	private  NumberFormat getCurrencyFormat() {
		
			NumberFormat currencyFormat = NumberFormat.getInstance();
			currencyFormat.setGroupingUsed(true);
			currencyFormat.setMinimumFractionDigits(2);
			currencyFormat.setMaximumFractionDigits(2);
		
		return currencyFormat;
	}
	
	private  String formatDoubleToMaskValue(Double valor) {
		String valorMascara = "";
		if (valor != null) {
			valorMascara = getCurrencyFormat().format(valor);
		}
		return valorMascara;
	}
	
	private  Double convertMaskValueToDouble(String mascaraValor) {
		Double valor = null;
		String valorStr = removeFormatDecimalNumber(mascaraValor);
		if (valorStr != null) {
			try {
				valor = Double.parseDouble(valorStr);
			} catch (Exception e) {}
		}
		return valor;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		Object valor = null;
		if (value != null) {
			valor = convertMaskValueToDouble(value);
		}
		return valor;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		String valor = null;
		if ((value != null)) {
			valor = formatDoubleToMaskValue((Double) value);
		}
		return valor;
	}

}