package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.debug.gui.GuiValue;
import jx3d.util.BufferUtils;
import org.lwjgl.PointerBuffer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class NkGuiValue extends GuiValue {

    public static class NkFloat implements Float {

        public FloatBuffer buffer;

        public NkFloat(float value) {
            buffer = BufferUtils.createFloatBuffer(value);
        }

        @Override
        public float get() {
            return buffer.get(0);
        }

        @Override
        public void set(float value) {
            buffer.put(0, value);
        }
    }

    public static class NkInt implements Int {

        public IntBuffer buffer;

        public NkInt(int value) {
            buffer = BufferUtils.createIntBuffer(value);
        }

        @Override
        public int get() {
            return buffer.get(0);
        }

        @Override
        public void set(int value) {
            buffer.put(0, value);
        }
    }

    public static class NkProgress implements Progress {

        public PointerBuffer buffer = org.lwjgl.BufferUtils.createPointerBuffer(1);

        public long max;

        public NkProgress(long max) {
            this.max = max;
        }

        @Override
        public long max() {
            return max;
        }

        @Override
        public long get() {
            return buffer.get(0);
        }

        @Override
        public void set(long value) {
            buffer.put(0, value);
        }

        @Override
        public void incr(long value) {
            long newValue = get() + value;
            if (newValue > max)
                newValue = max;

            buffer.put(0, newValue);
        }

        @Override
        public void decr(long value) {
            long newValue = get() - value;
            if (newValue < 0)
                newValue = 0;

            buffer.put(0, newValue);
        }
    }
}
