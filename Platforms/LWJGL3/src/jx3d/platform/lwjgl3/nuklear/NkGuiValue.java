package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.debug.gui.GuiValue;
import jx3d.util.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.nuklear.NkColorf;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
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

    public static class NkText implements Text {

        public ByteBuffer buffer;

        private int max;

        public NkText(String text, int maxLength) {
            buffer = BufferUtils.createEmptyByteBuffer(maxLength);
            max = maxLength;
            set(text);
        }

        @Override
        public int max() {
            return max;
        }

        @Override
        public String get() {
            return new String(buffer.array());
        }

        @Override
        public void set(String value) {
            buffer.put(value.getBytes(), 0, Math.min(value.length(), max - 1));
            buffer.put((byte) 0);
            buffer.flip();
        }
    }

    public static class NkColor implements Color {

        public NkColorf color = NkColorf.create();

        public NkColor(float red, float green, float blue, float alpha) {
            color.r(red);
            color.g(green);
            color.b(blue);
            color.a(alpha);
        }

        @Override
        public float red() {
            return color.r();
        }

        @Override
        public float green() {
            return color.g();
        }

        @Override
        public float blue() {
            return color.b();
        }

        @Override
        public float alpha() {
            return color.a();
        }

        @Override
        public void red(float value) {
            color.r(value);
        }

        @Override
        public void green(float value) {
            color.g(value);
        }

        @Override
        public void blue(float value) {
            color.b(value);
        }

        @Override
        public void alpha(float value) {
            color.a(value);
        }
    }
}
