package io.sentry.spring.boot;

import io.sentry.core.SentryLevel;
import io.sentry.core.SentryOptions;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for Sentry in a shape of Spring Boot configuration bean. Example:
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
public class SentryProperties {
  private boolean enabled;

  private boolean globalHubMode;

  /**
   * The DSN tells the SDK where to send the events to. If this value is not provided, the SDK will
   * just not send any events.
   */
  private String dsn = "";

  /**
   * Controls how many seconds to wait before shutting down. Sentry SDKs send events from a
   * background queue and this queue is given a certain amount to drain pending events Default is
   * 2000 = 2s
   */
  private long shutdownTimeoutMills = 2000;

  /**
   * Turns debug mode on or off. If debug is enabled SDK will attempt to print out useful debugging
   * information if something goes wrong. Default is disabled.
   */
  private boolean debug;

  /** Turns NDK on or off. Default is enabled. */
  private boolean enableNdk = true;

  /** minimum LogLevel to be used if debug is enabled */
  private SentryLevel diagnosticLevel = SentryLevel.DEBUG;

  /**
   * Sentry client name used for the HTTP authHeader and userAgent eg
   * sentry.{language}.{platform}/{version} eg sentry.java.android/2.0.0 would be a valid case
   */
  private String sentryClientName;

  /** The cache dir. path for caching offline events */
  private String cacheDirPath;

  /** The cache dir. size for capping the number of events Default is 10 */
  private int cacheDirSize = 10;

  /**
   * This variable controls the total amount of breadcrumbs that should be captured Default is 100
   */
  private int maxBreadcrumbs = 100;

  /** Sets the release. SDK will try to automatically configure a release out of the box */
  private String release;

  /**
   * Sets the environment. This string is freeform and not set by default. A release can be
   * associated with more than one environment to separate them in the UI Think staging vs prod or
   * similar.
   */
  private String environment;

  /**
   * Configures the sample rate as a percentage of events to be sent in the range of 0.0 to 1.0. if
   * 1.0 is set it means that 100% of events are sent. If set to 0.1 only 10% of events will be
   * sent. Events are picked randomly. Default is null (disabled)
   */
  private Double sampleRate;

  /**
   * A list of string prefixes of module names that do not belong to the app, but rather third-party
   * packages. Modules considered not to be part of the app will be hidden from stack traces by
   * default.
   */
  private List<String> inAppExcludes = new ArrayList<>();

  /**
   * A list of string prefixes of module names that belong to the app. This option takes precedence
   * over inAppExcludes.
   */
  private List<String> inAppIncludes = new ArrayList<>();

  /** Sets the distribution. Think about it together with release and environment */
  private String dist;

  /** When enabled, threads are automatically attached to all logged events. */
  private boolean attachThreads = true;

  /**
   * When enabled, stack traces are automatically attached to all threads logged. Stack traces are
   * always attached to exceptions but when this is set stack traces are also sent with threads
   */
  private boolean attachStacktrace;

  /** The server name used in the Sentry messages. */
  private String serverName;

  /**
   * Applies configuration from this instance to the {@link SentryOptions} instance.
   *
   * @param options the instance of {@link SentryOptions} to apply the configuration to
   */
  public void applyTo(SentryOptions options) {
    options.setAttachStacktrace(this.isAttachStacktrace());
    options.setAttachThreads(this.isAttachThreads());
    options.setCacheDirPath(this.getCacheDirPath());
    options.setCacheDirSize(this.getCacheDirSize());
    options.setDebug(this.isDebug());
    options.setDiagnosticLevel(this.getDiagnosticLevel());
    options.setDist(this.getDist());
    options.setDsn(this.getDsn());
    options.setEnableNdk(this.isEnableNdk());
    options.setEnvironment(this.getEnvironment());
    options.setMaxBreadcrumbs(this.getMaxBreadcrumbs());
    options.setRelease(this.getRelease());
    options.setSampleRate(this.getSampleRate());
    options.setSentryClientName(this.getSentryClientName());
    options.setServerName(this.getServerName());
    options.setShutdownTimeout(this.getShutdownTimeoutMills());
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isGlobalHubMode() {
    return globalHubMode;
  }

  public void setGlobalHubMode(boolean globalHubMode) {
    this.globalHubMode = globalHubMode;
  }

  public String getDsn() {
    return dsn;
  }

  public void setDsn(String dsn) {
    this.dsn = dsn;
  }

  public long getShutdownTimeoutMills() {
    return shutdownTimeoutMills;
  }

  public void setShutdownTimeoutMills(long shutdownTimeoutMills) {
    this.shutdownTimeoutMills = shutdownTimeoutMills;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isEnableNdk() {
    return enableNdk;
  }

  public void setEnableNdk(boolean enableNdk) {
    this.enableNdk = enableNdk;
  }

  public SentryLevel getDiagnosticLevel() {
    return diagnosticLevel;
  }

  public void setDiagnosticLevel(SentryLevel diagnosticLevel) {
    this.diagnosticLevel = diagnosticLevel;
  }

  public String getSentryClientName() {
    return sentryClientName;
  }

  public void setSentryClientName(String sentryClientName) {
    this.sentryClientName = sentryClientName;
  }

  public String getCacheDirPath() {
    return cacheDirPath;
  }

  public void setCacheDirPath(String cacheDirPath) {
    this.cacheDirPath = cacheDirPath;
  }

  public int getCacheDirSize() {
    return cacheDirSize;
  }

  public void setCacheDirSize(int cacheDirSize) {
    this.cacheDirSize = cacheDirSize;
  }

  public int getMaxBreadcrumbs() {
    return maxBreadcrumbs;
  }

  public void setMaxBreadcrumbs(int maxBreadcrumbs) {
    this.maxBreadcrumbs = maxBreadcrumbs;
  }

  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public Double getSampleRate() {
    return sampleRate;
  }

  public void setSampleRate(Double sampleRate) {
    this.sampleRate = sampleRate;
  }

  public List<String> getInAppExcludes() {
    return inAppExcludes;
  }

  public void setInAppExcludes(List<String> inAppExcludes) {
    this.inAppExcludes = inAppExcludes;
  }

  public List<String> getInAppIncludes() {
    return inAppIncludes;
  }

  public void setInAppIncludes(List<String> inAppIncludes) {
    this.inAppIncludes = inAppIncludes;
  }

  public String getDist() {
    return dist;
  }

  public void setDist(String dist) {
    this.dist = dist;
  }

  public boolean isAttachThreads() {
    return attachThreads;
  }

  public void setAttachThreads(boolean attachThreads) {
    this.attachThreads = attachThreads;
  }

  public boolean isAttachStacktrace() {
    return attachStacktrace;
  }

  public void setAttachStacktrace(boolean attachStacktrace) {
    this.attachStacktrace = attachStacktrace;
  }

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }
}
