package io.sentry.spring.boot;

import io.sentry.core.EventProcessor;
import io.sentry.core.HubAdapter;
import io.sentry.core.IHub;
import io.sentry.core.Integration;
import io.sentry.core.Sentry;
import io.sentry.core.SentryOptions.BeforeBreadcrumbCallback;
import io.sentry.core.SentryOptions.BeforeSendCallback;
import io.sentry.spring.SentryExceptionResolver;
import java.util.List;
import java.util.Optional;
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

  /**
   * Initializes a {@link io.sentry.core.IHub}.
   *
   * <p>Note that the supplied {@code properties} are modified during this call with the values
   * supplied in the other parameters.
   *
   * @return a configured instance of {@link io.sentry.core.IHub}.
   */
  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  @Bean
  @ConditionalOnMissingBean(IHub.class)
  @ConditionalOnProperty(
      name = "sentry.enabled",
      havingValue = "true",
      matchIfMissing = true)
  public IHub sentryHub(SentryProperties properties,
      Optional<BeforeSendCallback> beforeSendCallback,
      Optional<BeforeBreadcrumbCallback> beforeBreadcrumbCallback,
      Optional<List<EventProcessor>> eventProcessors,
      Optional<List<Integration>> integrations) {

    beforeSendCallback.ifPresent(properties::setBeforeSend);
    beforeBreadcrumbCallback.ifPresent(properties::setBeforeBreadcrumb);
    eventProcessors.ifPresent(ps -> ps.forEach(properties::addEventProcessor));
    integrations.ifPresent(is -> is.forEach(properties::addIntegration));

    Sentry.init(properties, false);
    return HubAdapter.getInstance();
  }
}
