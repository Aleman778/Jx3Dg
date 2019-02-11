package jx3d.core;

import java.util.Date;
import java.util.logging.*;

public class Log {

    /**
     * The CORE logger is only used by the CORE api.
     */
    public static final Logger CORE;

    /**
     * The client logger is used by the client code.
     */
    public static final Logger CLIENT;

    /**
     * Private constructor, static class.
     */
    private Log() {
    }

    /**
     * Setting up loggers.
     */
    static {
        ConsoleHandler all = createLogHandler(Level.ALL, "\u001B[30m", true);
        ConsoleHandler info = createLogHandler(Level.INFO, "\u001B[36m", false);
        ConsoleHandler warning = createLogHandler(Level.WARNING, "\u001B[33m", false);
        ConsoleHandler severe = createLogHandler(Level.SEVERE, "\u001B[31m", false);

        CORE = Logger.getLogger("JX3DG");
        CORE.setLevel(Level.ALL);
        CORE.addHandler(all);
        CORE.addHandler(info);
        CORE.addHandler(warning);
        CORE.addHandler(severe);
        CORE.setUseParentHandlers(false);

        CLIENT = Logger.getLogger("APP");
        CLIENT.setLevel(Level.ALL);
        CLIENT.addHandler(all);
        CLIENT.addHandler(info);
        CLIENT.addHandler(warning);
        CLIENT.addHandler(severe);
        CLIENT.setUseParentHandlers(false);
    }

    private static ConsoleHandler createLogHandler(Level level, String prefix, boolean msgOnly) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private final String format = prefix + "[%1$tD %1$tT] %2$s: %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                if (msgOnly) {
                    return String.format(prefix + "%1$s %n", lr.getMessage());
                } else {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLoggerName(),
                            lr.getMessage()
                    );
                }
            }
        });
        handler.setLevel(level);
        handler.setFilter((LogRecord record) -> {
            return record.getLevel() == level;
        });
        return handler;
    }
}
