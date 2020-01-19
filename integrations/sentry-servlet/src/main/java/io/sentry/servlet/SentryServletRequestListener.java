package io.sentry.servlet;

import io.sentry.core.IHub;
import io.sentry.core.Integration;
import io.sentry.core.Sentry;
import io.sentry.SentryClient;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Request listener in charge of capturing {@link HttpServletRequest} to allow {@link
 * io.sentry.event.helper.HttpEventBuilderHelper} to provide details on the current HTTP session in
 * the event sent to Sentry.
 */
public class SentryServletRequestListener implements ServletRequestListener {
  private static final ThreadLocal<HttpServletRequest> THREAD_REQUEST = new ThreadLocal<>();

  public static HttpServletRequest getServletRequest() {
    return THREAD_REQUEST.get();
  }

  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    THREAD_REQUEST.remove();

    try {
      Sentry.clearBreadcrumbs();
    } catch (RuntimeException e) {
      logger.error("Error clearing Context state.", e);
    }
  }

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    if (servletRequest instanceof HttpServletRequest) {
      THREAD_REQUEST.set((HttpServletRequest) servletRequest);
    }
  }

  /**
   * Used for resetting the state of the thread-local storing the current HttpServletRequest.
   *
   * <p>This method exists solely for testing purposes.
   */
  static void reset() {
    THREAD_REQUEST.remove();
  }
}
