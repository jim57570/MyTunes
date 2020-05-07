package beans;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import objMetiers.Album;

@FacesConverter(value="albumConverter")
public class AlbumConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String albumId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory()
								.createValueExpression(ctx.getELContext(), "#{beanAlbum}", BeanAlbum.class);
		BeanAlbum album = (BeanAlbum)vex.getValue(ctx.getELContext());
		
		for(int i=0; i<album.getAlbums().size(); i++) {
			if(album.getAlbums().get(i).getId() == Integer.valueOf(albumId))
				return album.getAlbums().get(i);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent uiComponent, Object album) {
		return String.valueOf(((Album)album).getId());
	}

}
