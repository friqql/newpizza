package de.friqql.converter;


import de.friqql.model.Benutzer;
import de.friqql.controller.BenutzerController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Wandelt den User um so dass er verwendet werden kann
 * @author Teilnehmer
 */
@FacesConverter(forClass = de.friqql.model.Benutzer.class, value = "benutzerCNV")
public class BenutzerConverter implements Converter {

@Inject
BenutzerController usrController;

/**
 * Als Objekt
 * @param facesContext
 * @param component
 * @param submittedValue
 * @return 
 * Gibt das Objekt zurück
 */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session=request.getSession();
       
        BenutzerController uc =usrController;
        List<Benutzer> leute=uc.getBenutzerlist();
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Benutzer b : leute) {
                    if (b.getId() == number) {
                        return b;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }
/**
 * Als String
 * @param facesContext
 * @param component
 * @param value
 * @return 
 * Gibt den String zurück
 */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Benutzer) value).getId());
        }
    }
}
