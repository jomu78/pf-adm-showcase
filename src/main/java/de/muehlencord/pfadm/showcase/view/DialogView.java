package de.muehlencord.pfadm.showcase.view;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * backing bean for the dialog showcase page.
 *
 * @author Joern Muehlencord, 2026-07-08
 * @since 0.4.0
 */
@Named
@ViewScoped
@Getter
@Setter
public class DialogView implements Serializable {

  private String firstName = "Vito";
  private String lastName = "Corleone";
  private String email = "vito@corleone.dev";

  public void save() {
    addMessage(FacesMessage.SEVERITY_INFO, "Profile saved",
      firstName + " " + lastName + " (" + email + ")");
  }

  public void confirmDelete() {
    addMessage(FacesMessage.SEVERITY_WARN, "Record deleted",
      "The selected record has been removed.");
  }

  private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
    FacesContext.getCurrentInstance().addMessage(null,
      new FacesMessage(severity, summary, detail));
  }
}
