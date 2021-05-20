package io.tictactoe.utils;

import javax.inject.Singleton;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

@Singleton
public class AppLogger extends Handler {
    public AppLogger() {}

    @Override
    public void publish(LogRecord record) {
        System.out.println(new StringBuilder()
                .append(record.getMillis())
                .append(" - ")
                .append(record.getSourceClassName())
                .append("#")
                .append(record.getSourceMethodName())
                .append(" - ")
                .append(record.getMessage()));
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
