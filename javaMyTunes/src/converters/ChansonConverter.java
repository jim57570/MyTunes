package converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.BeanChanson;
import objMetiers.Chanson;

@FacesConverter(value="chansonConverter")
public class ChansonConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String chansonId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory()
								.createValueExpression(ctx.getELContext(), "#{beanChanson}", BeanChanson.class);
		BeanChanson chanson = (BeanChanson)vex.getValue(ctx.getELContext());
		
		for(int i=0; i<chanson.getChansons().size(); i++) {
			if(chanson.getChansons().get(i).getId() == Integer.valueOf(chansonId))
				return chanson.getChansons().get(i);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent uiComponent, Object chanson) {
		if(chanson == null)
			return "-1";
		else
			return String.valueOf(((Chanson)chanson).getId());
	}

}
