package jx3d.graphics.debug.gui;

public class GuiValue {

    public interface Float {

        float get();

        void set(float value);
    }

    public interface Int {

        int get();

        void set(int value);
    }

    public interface Progress {

        long max();

        long get();

        void set(long value);

        void incr(long value);

        void decr(long value);
    }

    public interface Text {

        int max();

        String get();

        void set(String value);

    }

    public interface Color {

        float red();

        float green();

        float blue();

        float alpha();

        void red(float value);

        void green(float value);

        void blue(float value);

        void alpha(float value);
    }
}
