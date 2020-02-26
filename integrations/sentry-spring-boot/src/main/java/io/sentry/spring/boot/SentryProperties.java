package io.sentry.spring.boot;

import io.sentry.core.SentryOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for Sentry, example:
 *
 * <pre>
 * sentry:
 *     enabled: true
 *     dsn: https://00059966e6224d03a77ea5eca10fbe18@sentry.mycompany.com/14
 *     release: "1.0.1"
 *     dist: x86
 *     environment: staging
 *     server-name: megaServer
 * </pre>
 */
@ConfigurationProperties("sentry")
public class SentryProperties extends SentryOptions {
  private boolean enabled;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
