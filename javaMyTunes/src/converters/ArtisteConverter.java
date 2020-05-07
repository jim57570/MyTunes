package converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.BeanArtiste;
import objMetiers.Artiste;

@FacesConverter(value="artisteConverter")
public class ArtisteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String artisteId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory()
								.createValueExpression(ctx.getELContext(), "#{beanArtiste}", BeanArtiste.class);
		BeanArtiste artiste = (BeanArtiste)vex.getValue(ctx.getELContext());
		
		for(int i=0; i<artiste.getArtistes().size(); i++) {
			if(artiste.getArtistes().get(i).getId() == Integer.valueOf(artisteId))
				return artiste.getArtistes().get(i);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent uiComponent, Object artiste) {
		if(artiste == null)
			return "-1";
		else
			return String.valueOf(((Artiste)artiste).getId());
	}

}
