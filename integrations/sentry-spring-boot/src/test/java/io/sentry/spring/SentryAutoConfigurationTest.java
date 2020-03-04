package io.sentry.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.sentry.core.IHub;
import io.sentry.spring.boot.SentryAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SentryAutoConfigurationTest {
  @RunWith(SpringJUnit4ClassRunner.class)
  @SpringBootTest(classes = SentryAutoConfiguration.class)
  public static class AutoConfigurationBeanCreatesDisabledHubInstanceByDefault {

    @Autowired private ConfigurableApplicationContext context;

    @Test
    public void testAutoConfigurationBeanCreatesDisabledHubInstanceByDefault() {
      IHub hub = context.getBean("sentryHub", IHub.class);

      assertNotNull(hub);
      assertFalse(hub.isEnabled());
    }
  }

  @RunWith(SpringJUnit4ClassRunner.class)
  @SpringBootTest(classes = SentryAutoConfiguration.class, properties = "sentry.enabled=false")
  public static class AutoConfigurationBeanDoesntCreateHubIfDisabled {

    @Autowired private ConfigurableApplicationContext context;

    @Test
    public void testAutoConfigurationBeanDoesntCreateHubIfDisabled() {
      assertFalse(context.containsBean("sentryHub"));
    }
  }

  @RunWith(SpringJUnit4ClassRunner.class)
  @SpringBootTest(
      classes = SentryAutoConfiguration.class,
      properties = {"sentry.dsn=http://publicKey:secretKey@host/9", "sentry.cache-dir-path=./"})
  public static class AutoConfigurationBeanAppliesProperties {

    @Autowired private ConfigurableApplicationContext context;

    @Test
    public void testAutoConfigurationBeanDoesntCreateHubIfDisabled() {
      IHub hub = context.getBean("sentryHub", IHub.class);

      assertNotNull(hub);
      assertTrue(hub.isEnabled());
    }
  }
}
