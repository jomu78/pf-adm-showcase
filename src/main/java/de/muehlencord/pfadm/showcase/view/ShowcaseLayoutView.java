package de.muehlencord.pfadm.showcase.view;

import de.muehlencord.pfadm.template.config.PfAdmProperties;
import de.muehlencord.pfadm.template.config.SkinEnum;
import de.muehlencord.pfadm.template.view.LayoutView;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * bean controlling used layout options.
 * <p>
 * Note on injection: the showcase view beans are Spring-managed (joinfaces maps
 * {@code @Named}/{@code @ViewScoped} onto Spring scopes via component scanning),
 * so only Spring beans can be constructor-injected here. {@link PfAdmProperties}
 * is a Spring bean (registered by pf-adm's auto-configuration), while
 * {@code PfAdmConfig} and {@link LayoutView} are CDI beans from pf-adm's bean
 * archive - those must be resolved via EL at runtime instead.
 *
 * @author Joern Muehlencord, 2025-05-05
 * @since 0.1.0
 */
@ViewScoped
@Named
public class ShowcaseLayoutView implements Serializable {

  private static final String SIDEBAR_TEMPLATE = "/WEB-INF/template/template.xhtml";
  private static final String TOP_TEMPLATE = "/WEB-INF/template/template-top.xhtml";

  @Getter
  @Setter
  private boolean borderless = false;
  private final PfAdmProperties pfAdmProperties;

  @Inject
  public ShowcaseLayoutView(PfAdmProperties pfAdmProperties) {
    this.pfAdmProperties = pfAdmProperties;
  }

  public void setTopMenuLayout(Boolean topLayout) {
    layoutView().setTemplate(Boolean.TRUE.equals(topLayout) ? TOP_TEMPLATE : SIDEBAR_TEMPLATE);
  }

  public Boolean getTopMenuLayout() {
    return TOP_TEMPLATE.equals(layoutView().getTemplate());
  }

  public String getTemplate() {
    return layoutView().getTemplate();
  }

  private static LayoutView layoutView() {
    FacesContext fc = FacesContext.getCurrentInstance();
    return fc.getApplication().evaluateExpressionGet(fc, "#{layoutView}", LayoutView.class);
  }

  public List<SelectItem> getAvailableSkins() {
    return Arrays.stream(SkinEnum.values())
      .map(skin -> new SelectItem(skin.getLabel(), toDisplayName(skin.getLabel())))
      .toList();
  }

  public String getDefaultSkin() {
    return pfAdmProperties.getSkin().getLabel();
  }

  private String toDisplayName(String skinLabel) {
    return skinLabel.replace("skin-", "")
      .replace("-light", " light")
      .replace('-', ' ');
  }
}
