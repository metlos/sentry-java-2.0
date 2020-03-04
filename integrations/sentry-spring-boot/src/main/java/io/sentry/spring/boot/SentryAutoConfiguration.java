package io.sentry.spring.boot;

import static java.util.Collections.emptyList;

import io.sentry.core.EventProcessor;
import io.sentry.core.HubAdapter;
import io.sentry.core.IHub;
import io.sentry.core.Integration;
import io.sentry.core.Sentry;
import io.sentry.core.Sentry.OptionsConfiguration;
import io.sentry.core.SentryOptions;
import io.sentry.core.SentryOptions.BeforeBreadcrumbCallback;
import io.sentry.core.SentryOptions.BeforeSendCallback;
import io.sentry.core.transport.ITransportGate;
import io.sentry.spring.SentryExceptionResolver;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

/** Spring Auto Configuration for Sentry. */
@Configuration
@ConditionalOnClass({HandlerExceptionResolver.class, SentryExceptionResolver.class})
@EnableConfigurationProperties(SentryProperties.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "sentry.enabled", havingValue = "true", matchIfMissing = true)
public class SentryAutoConfiguration {

  /**
   * Resolves a {@link HandlerExceptionResolver}.
   *
   * @return a new instance of {@link HandlerExceptionResolver}.
   */
  @Bean
  @ConditionalOnMissingBean(SentryExceptionResolver.class)
  public HandlerExceptionResolver sentryExceptionResolver() {
    return new SentryExceptionResolver();
  }

  /**
   * Initializes a {@link ServletContextInitializer}.
   *
   * @return a new instance of {@link SentryServletContextInitializer}.
   */
  @Bean
  @ConditionalOnMissingBean(SentryServletContextInitializer.class)
  public ServletContextInitializer sentryServletContextInitializer() {
    return new SentryServletContextInitializer();
  }

  @Bean
  @ConditionalOnMissingBean(BeforeSendCallback.class)
  public BeforeSendCallback defaultBeforeSendCallback() {
    return (event, __) -> event;
  }

  @Bean
  @ConditionalOnMissingBean(BeforeBreadcrumbCallback.class)
  public BeforeBreadcrumbCallback defaultBeforeBreadcrumbCallback() {
    return (breadcrumb, __) -> breadcrumb;
  }

  @Bean
  @ConditionalOnMissingBean(EventProcessor.class)
  public List<EventProcessor> defaultEventProcessors() {
    return emptyList();
  }

  @Bean
  @ConditionalOnMissingBean(Integration.class)
  public List<Integration> defaultIntegrations() {
    return emptyList();
  }

  @Bean
  @ConditionalOnMissingBean(ITransportGate.class)
  public ITransportGate defaultTransportGate() {
    return () -> true;
  }

  @Bean
  @ConditionalOnMissingBean(OptionsConfiguration.class)
  public OptionsConfiguration<SentryOptions> defaultOptionsConfigurator(
      SentryProperties properties,
      BeforeSendCallback beforeSendCallback,
      BeforeBreadcrumbCallback beforeBreadcrumbCallback,
      ITransportGate transportGate,
      List<EventProcessor> eventProcessors,
      List<Integration> integrations) {

    return options -> {
      properties.applyTo(options);
      options.setBeforeBreadcrumb(beforeBreadcrumbCallback);
      options.setBeforeSend(beforeSendCallback);
      eventProcessors.forEach(options::addEventProcessor);
      integrations.forEach(options::addIntegration);
      options.setTransportGate(transportGate);
    };
  }

  /**
   * Initializes a {@link io.sentry.core.IHub}.
   *
   * @return a configured instance of {@link io.sentry.core.IHub}.
   */
  @Bean
  @ConditionalOnMissingBean(IHub.class)
  @ConditionalOnProperty(name = "sentry.enabled", havingValue = "true", matchIfMissing = true)
  public IHub sentryHub(
      OptionsConfiguration<SentryOptions> configurator, SentryProperties properties) {
    Sentry.init(configurator, properties.isGlobalHubMode());
    return HubAdapter.getInstance();
  }
}
