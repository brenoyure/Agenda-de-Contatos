package br.albatross.agenda.beans.converters;

import br.albatross.agenda.models.entities.Setor;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("setorConverter")
public class SetorConverter implements Converter<Setor> {

	@Override
	public Setor getAsObject(FacesContext context, UIComponent component, String setor) {

		if (setor == null || setor.isBlank()) {
			return null;
		}
		Setor setorObj = new Setor();
		setorObj.setId(Short.valueOf(setor));
		return setorObj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Setor setor) {
		if (setor == null) {
			return null;
		}
		return setor.getId().toString();
	}

}
