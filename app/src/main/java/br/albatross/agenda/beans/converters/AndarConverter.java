package br.albatross.agenda.beans.converters;

import br.albatross.agenda.models.Andar;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("andarConverter")
public class AndarConverter implements Converter<Andar> {

	@Override
	public Andar getAsObject(FacesContext context, UIComponent component, String andar) {

		if (andar == null || andar.isBlank()) {
			return null;
		}
		Andar andarObj = new Andar();
		andarObj.setId(Integer.valueOf(andar));
		return andarObj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Andar andar) {
		if (andar == null) {
			return null;
		}
		return andar.getId().toString();
	}

}
