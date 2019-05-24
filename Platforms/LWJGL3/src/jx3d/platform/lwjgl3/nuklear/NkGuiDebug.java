package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.graphics.Image;
import jx3d.graphics.debug.gui.GuiRect;
import jx3d.graphics.debug.gui.GuiValue;
import org.lwjgl.nuklear.*;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.nuklear.Nuklear.*;

public class NkGuiDebug extends GuiDebug {

    private NkContext ctx;
    private MemoryStack stack;


    public NkGuiDebug(NkContext ctx) {
        this.ctx = ctx;
        this.stack = MemoryStack.stackGet();
    }

    @Override
    public boolean begin(String title, GuiRect rect, int flags) {
        stack.push();
        NkRect bounds = NkRect.mallocStack(stack);
        nk_rect(rect.x, rect.y, rect.w, rect.h, bounds);
        return nk_begin(ctx, title, bounds, flags);
    }

    @Override
    public boolean begin(String name, String title, GuiRect rect, int flags) {
        stack.push();
        NkRect bounds = NkRect.mallocStack(stack);
        nk_rect(rect.x, rect.y, rect.w, rect.h, bounds);
        return nk_begin_titled(ctx, name, title, bounds, flags);
    }

    @Override
    public void end() {
        nk_end(ctx);
        stack.pop();
    }

    @Override
    public void label(String text, int align) {
        nk_label(ctx, text, align);
    }

    @Override
    public boolean button(String label) {
        return nk_button_label(ctx, label);
    }

    @Override
    public boolean checkbox(String label, boolean active) {
        return nk_checkbox_label(ctx, label, stack.ints(active ? 1 : 0));
    }

    @Override
    public boolean radioButton(String label, boolean active) {
        return nk_radio_label(ctx, label, stack.ints(active ? 1 : 0));
    }

    @Override
    public boolean selectable(String label, int align, boolean selected) {
        return nk_selectable_label(ctx, label, align, stack.ints(selected ? 1 : 0));
    }

    @Override
    public void sliderInt(GuiValue.Int value, int minValue, int maxValue, int step) {
        nk_slider_int(ctx, minValue, ((NkGuiValue.NkInt) value).buffer, maxValue, step);
    }

    @Override
    public void sliderFloat(GuiValue.Float value, float minValue, float maxValue, float step) {
        nk_slider_float(ctx, minValue, ((NkGuiValue.NkFloat) value).buffer, maxValue, step);
    }

    @Override
    public void progress(GuiValue.Progress value, boolean modifiable) {
        nk_progress(ctx, ((NkGuiValue.NkProgress) value).buffer, value.max(), true);
    }

    @Override
    public void layoutRowDynamic(float height, int cols) {
        nk_layout_row_dynamic(ctx, height, cols);
    }

    @Override
    public void layoutRowStatic(float height, int colWidth, int cols) {
        nk_layout_row_static(ctx, height, colWidth, cols);
    }

    @Override
    public void layoutRowBegin(int format, float height, int cols) {

    }

    @Override
    public void layoutRowPush(float value) {

    }

    @Override
    public void layoutRowEnd() {

    }

    @Override
    public void layoutTemplateBegin(float height) {

    }

    @Override
    public void layoutTemplatePushDynamic() {

    }

    @Override
    public void layoutTemplatePushVariable(float minWidth) {

    }

    @Override
    public void layoutTemplatePushStatic(float width) {

    }

    @Override
    public void layoutTemplateEnd() {

    }

    @Override
    public void layoutSpaceBegin(int format, float height, int numWidgets) {

    }

    @Override
    public void layoutSpacePush(GuiRect bounds) {

    }

    @Override
    public void layoutSpaceEnd() {

    }

    @Override
    public void groupBegin(String title, int flags) {

    }

    @Override
    public void groupBegin(String name, String title, int flags) {

    }

    @Override
    public void groupBegin(int offsetX, int offsetY, String title, int flags) {

    }

    @Override
    public void groupEnd() {

    }

    @Override
    public void group() {

    }

    @Override
    public void treePush(int type, String title, int state) {
    }

    @Override
    public void treePushLoop(int type, String title, int state, int level) {

    }

    @Override
    public void treeImagePush(int type, String title, Image image, int state) {

    }

    @Override
    public void treeImagePush() {

    }

    @Override
    public void treePop() {

    }

    @Override
    public GuiValue.Float valueFloat(float value) {
        return new NkGuiValue.NkFloat(value);
    }

    @Override
    public GuiValue.Int valueInt(int value) {
        return new NkGuiValue.NkInt(value);
    }

    @Override
    public GuiValue.Progress valueProgress(long max) {
        return new NkGuiValue.NkProgress(max);
    }
}
