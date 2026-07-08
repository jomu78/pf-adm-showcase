package de.muehlencord.pfadm.showcase.view;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * backing bean for the panelGrid / column width showcase page.
 *
 * @author Joern Muehlencord, 2026-07-08
 * @since 0.4.0
 */
@Named
@ViewScoped
@Getter
@Setter
public class PanelGridView implements Serializable {

  private String firstName = "Vito";
  private String lastName = "Corleone";
  private String email = "vito@corleone.dev";
  private String phone = "+39 091 123456";
  private String city = "Corleone";
  private String zip = "90034";
  private String country = "Italy";
  private String notes = "Prefers to be contacted in the morning.";
}
