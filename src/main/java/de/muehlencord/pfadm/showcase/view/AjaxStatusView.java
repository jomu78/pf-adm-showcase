package de.muehlencord.pfadm.showcase.view;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

/**
 * backing bean for the AJAX status showcase page. The action deliberately
 * blocks for a few seconds so the global loading overlay (enabled via
 * pf-adm.render-ajax-status) stays visible long enough to observe.
 *
 * @author Joern Muehlencord, 2026-06-11
 * @since 0.4.0
 */
@Named
@ViewScoped
@Getter
@Setter
public class AjaxStatusView implements Serializable {

  private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

  private int seconds = 3;
  private int runCount = 0;
  private String lastRunFinishedAt;

  /**
   * blocks the request thread for the selected number of seconds to simulate a
   * long-running server task.
   */
  public void runSlowTask() {
    try {
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    runCount++;
    lastRunFinishedAt = LocalTime.now().format(TIME_FORMAT);
  }
}
