package converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.BeanGenre;
import objMetiers.Genre;

@FacesConverter(value="genreConverter")
public class GenreConverter implements Converter {
	
	//TODO A refaire plus propre
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String genreId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory()
								.createValueExpression(ctx.getELContext(), "#{beanGenre}", BeanGenre.class);
		BeanGenre genre = (BeanGenre)vex.getValue(ctx.getELContext());
		
		for(int i=0; i<genre.getGenres().size(); i++) {
			if(genre.getGenres().get(i).getId() == Integer.valueOf(genreId))
				return genre.getGenres().get(i);
		}
		return null;		
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent uiComponent, Object genre) {
		if(genre == null)
			return "-1";
		else
			return String.valueOf(((Genre)genre).getId());
	}

}
