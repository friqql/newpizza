package de.friqql.converter;


import de.friqql.model.Usr;
import de.friqql.controller.UsrController;
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
@FacesConverter(forClass = de.friqql.model.Usr.class, value = "userCNV")
public class UserConverter implements Converter {

@Inject
UsrController usrController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session=request.getSession();
       
        UsrController uc =usrController;
        List<Usr> leute=uc.getAllCustomers();
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Usr s : leute) {
                    if (s.getUId() == number) {
                        return s;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Usr) value).getUId());
        }
    }
}
