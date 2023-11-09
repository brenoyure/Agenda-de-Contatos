package br.albatross.agenda.beans.converters;

import br.albatross.agenda.models.UnidadeAdministrativa;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("unidadeAdministrativaConverter")
public class UnidadeAdministrativaConverter implements Converter<UnidadeAdministrativa> {

	@Override
	public UnidadeAdministrativa getAsObject(FacesContext context, UIComponent component, String unidadeAdmin) {

		if (unidadeAdmin == null || unidadeAdmin.isBlank()) {
			return null;
		}
		UnidadeAdministrativa unidade = new UnidadeAdministrativa();
		unidade.setId(Short.valueOf(unidadeAdmin));
		return unidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, UnidadeAdministrativa unidadeAdmin) {
		if (unidadeAdmin == null) {
			return null;
		}
		return unidadeAdmin.getId().toString();
	}

}
