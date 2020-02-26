package io.sentry.servlet;

import io.sentry.core.Sentry;
import io.sentry.core.protocol.Request;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * This request listener pushes a new scope into sentry that enriches a Sentry event with the
 * details about the current request upon sending.
 */
public class SentryServletRequestListener implements ServletRequestListener {
  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    Sentry.clearBreadcrumbs();
  }

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    Sentry.pushScope();
    Sentry.configureScope(
        scope ->
            scope.addEventProcessor(
                (event, __) -> {
                  ServletRequest sreq = servletRequestEvent.getServletRequest();
                  if (!(sreq instanceof HttpServletRequest)) {
                    return event;
                  }

                  if (event.getRequest() != null) {
                    // the application code already set the request - don't overwrite it then
                    return event;
                  }

                  HttpServletRequest httpReq = (HttpServletRequest) sreq;

                  Request req = new Request();
                  req.setCookies(cookiesToString(httpReq.getHeaders("Cookie")));
                  req.setHeaders(allHeaders(httpReq));
                  req.setMethod(httpReq.getMethod());
                  req.setQueryString(httpReq.getQueryString());
                  req.setUrl(httpReq.getRequestURI());
                  // TODO add request attributes as "envs" of the sentry request or some other attr?

                  event.setRequest(req);
                  return event;
                }));
  }

  private static String cookiesToString(Enumeration<String> cookies) {
    if (cookies == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    while (cookies.hasMoreElements()) {
      sb.append(cookies.nextElement());
      sb.append("; ");
    }

    if (sb.length() > 2) {
      sb.setLength(sb.length() - 2);
    }

    return sb.toString();
  }

  private static Map<String, String> allHeaders(HttpServletRequest req) {
    Map<String, String> headers = new HashMap<>();
    Enumeration<String> names = req.getHeaderNames();
    StringBuilder sb = new StringBuilder();
    while (names.hasMoreElements()) {
      sb.setLength(0);
      String name = names.nextElement();
      Enumeration<String> values = req.getHeaders(name);
      while (values.hasMoreElements()) {
        sb.append(values.nextElement()).append(",");
      }
      if (sb.length() > 0) {
        sb.setLength(sb.length() - 1);
      }
      headers.put(name, sb.toString());
    }

    return headers;
  }
}
