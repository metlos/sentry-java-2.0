package io.sentry.log4j;

import io.sentry.core.SentryEvent;
import io.sentry.core.SentryLevel;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class SentryAppender extends AppenderSkeleton {
  /** Name of the {@link SentryEvent#extra} property containing NDC details. */
  public static final String LOG4J_NDC = "log4J-NDC";
  /** Name of the {@link SentryEvent#extra} property containing the Thread name. */
  public static final String THREAD_NAME = "Sentry-Threadname";

  /** Creates an instance of SentryAppender. */
  public SentryAppender() {
    this.addFilter(new DropSentryFilter());
  }

  /**
   * Transforms a {@link Level} into an {@link SentryLevel}.
   *
   * @param level original level as defined in log4j.
   * @return log level used within sentry.
   */
  protected static SentryLevel formatLevel(Level level) {
    if (level.isGreaterOrEqual(Level.FATAL)) {
      return SentryLevel.FATAL;
    } else if (level.isGreaterOrEqual(Level.ERROR)) {
      return SentryLevel.ERROR;
    } else if (level.isGreaterOrEqual(Level.WARN)) {
      return SentryLevel.WARNING;
    } else if (level.isGreaterOrEqual(Level.INFO)) {
      return SentryLevel.INFO;
    } else if (level.isGreaterOrEqual(Level.ALL)) {
      return SentryLevel.DEBUG;
    } else {
      return null;
    }
  }

  /**
   * Transforms the location info of a log into a stacktrace element (stackframe).
   *
   * @param location details on the location of the log.
   * @return a stackframe.
   */
  protected static StackTraceElement asStackTraceElement(LocationInfo location) {
    String fileName =
        (LocationInfo.NA.equals(location.getFileName())) ? null : location.getFileName();
    int line =
        (LocationInfo.NA.equals(location.getLineNumber()))
            ? -1
            : Integer.parseInt(location.getLineNumber());
    return new StackTraceElement(location.getClassName(), location.getMethodName(), fileName, line);
  }

  @Override
  protected void append(LoggingEvent loggingEvent) {
    //    // Do not log the event if the current thread is managed by sentry
    //    if (SentryEnvironment.isManagingThread()) {
    //      return;
    //    }
    //
    //    SentryEnvironment.startManagingThread();
    //    try {
    //      EventBuilder eventBuilder = createEventBuilder(loggingEvent);
    //      Sentry.capture(eventBuilder);
    //    } catch (RuntimeException e) {
    //      getErrorHandler()
    //          .error(
    //              "An exception occurred while creating a new event in Sentry",
    //              e,
    //              ErrorCode.WRITE_FAILURE);
    //    } finally {
    //      SentryEnvironment.stopManagingThread();
    //    }
  }

  //  /**
  //   * Builds an EventBuilder based on the logging event.
  //   *
  //   * @param loggingEvent Log generated.
  //   * @return EventBuilder containing details provided by the logging system.
  //   */
  //  protected EventBuilder createEventBuilder(LoggingEvent loggingEvent) {
  //    EventBuilder eventBuilder =
  //        new EventBuilder()
  //            .withSdkIntegration("log4j")
  //            .withTimestamp(new Date(loggingEvent.getTimeStamp()))
  //            .withMessage(loggingEvent.getRenderedMessage())
  //            .withLogger(loggingEvent.getLoggerName())
  //            .withLevel(formatLevel(loggingEvent.getLevel()))
  //            .withExtra(THREAD_NAME, loggingEvent.getThreadName());
  //
  //    ThrowableInformation throwableInformation = null;
  //    try {
  //      throwableInformation = loggingEvent.getThrowableInformation();
  //    } catch (NullPointerException expected) {
  //      // `throwableInformation` is already set.
  //    }
  //
  //    if (throwableInformation != null) {
  //      Throwable throwable = throwableInformation.getThrowable();
  //      eventBuilder.withSentryInterface(new ExceptionInterface(throwable));
  //    } else if (loggingEvent.getLocationInformation().fullInfo != null) {
  //      LocationInfo location = loggingEvent.getLocationInformation();
  //      if (!LocationInfo.NA.equals(location.getFileName())
  //          && !LocationInfo.NA.equals(location.getLineNumber())) {
  //        StackTraceElement[] stackTrace = {asStackTraceElement(location)};
  //        eventBuilder.withSentryInterface(new StackTraceInterface(stackTrace));
  //      }
  //    }
  //
  //    if (loggingEvent.getNDC() != null) {
  //      eventBuilder.withExtra(LOG4J_NDC, loggingEvent.getNDC());
  //    }
  //
  //    Set<String> extraTags = Sentry.getStoredClient().getMdcTags();
  //    @SuppressWarnings("unchecked")
  //    Map<String, Object> properties = (Map<String, Object>) loggingEvent.getProperties();
  //    for (Map.Entry<String, Object> mdcEntry : properties.entrySet()) {
  //      if (extraTags.contains(mdcEntry.getKey())) {
  //        eventBuilder.withTag(mdcEntry.getKey(), mdcEntry.getValue().toString());
  //      } else {
  //        eventBuilder.withExtra(mdcEntry.getKey(), mdcEntry.getValue());
  //      }
  //    }
  //
  //    return eventBuilder;
  //  }

  @Override
  public void close() {
    //    SentryEnvironment.startManagingThread();
    //    try {
    //      if (this.closed) {
    //        return;
    //      }
    //      this.closed = true;
    //      Sentry.close();
    //    } catch (RuntimeException e) {
    //      getErrorHandler()
    //          .error(
    //              "An exception occurred while closing the Sentry connection",
    //              e,
    //              ErrorCode.CLOSE_FAILURE);
    //    } finally {
    //      SentryEnvironment.stopManagingThread();
    //    }
  }

  @Override
  public boolean requiresLayout() {
    return false;
  }

  private class DropSentryFilter extends Filter {
    @Override
    public int decide(LoggingEvent event) {
      String loggerName = event.getLoggerName();
      if (loggerName != null && loggerName.startsWith("io.sentry")) {
        return Filter.DENY;
      }
      return Filter.NEUTRAL;
    }
  }
}
