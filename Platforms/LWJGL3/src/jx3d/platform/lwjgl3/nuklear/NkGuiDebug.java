package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.graphics.Image;
import jx3d.graphics.debug.gui.GuiRect;
import jx3d.graphics.debug.gui.GuiValue;
import org.lwjgl.nuklear.*;
import org.lwjgl.system.MemoryStack;

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
    public void sliderInt(GuiValue.Int value, int min, int max, int step) {
        nk_slider_int(ctx, min, ((NkGuiValue.NkInt) value).buffer, max, step);
    }

    @Override
    public void sliderFloat(GuiValue.Float value, float min, float max, float step) {
        nk_slider_float(ctx, min, ((NkGuiValue.NkFloat) value).buffer, max, step);
    }

    @Override
    public void propertyInt(String name, GuiValue.Int value, int min, int max, int step, float incPerPixel) {
        nk_property_int(ctx, name, min, ((NkGuiValue.NkInt) value).buffer, max, step, incPerPixel);
    }

    @Override
    public void propertyFloat(String name, GuiValue.Float value, float min, float max, float step, float incPerPixel) {
        nk_property_float(ctx, name, min, ((NkGuiValue.NkFloat) value).buffer, max, step, incPerPixel);
    }

    @Override
    public void progress(GuiValue.Progress value, boolean modifiable) {
        nk_progress(ctx, ((NkGuiValue.NkProgress) value).buffer, value.max(), true);
    }

    @Override
    public void textField(GuiValue.Text text, int filter) {
        //ByteBuffer buffer = ((NkGuiValue.NkText) text).buffer;
        //nk_edit_string(ctx, NK_EDIT_FIELD, buffer, stack.ints(buffer.remaining()), text.max(), );
    }

    @Override
    public void colorPicker(GuiValue.Color color, int format) {
        nk_color_picker(ctx, ((NkGuiValue.NkColor) color).color, format);
    }

    public void chartBegin() {
        //nk_chart_begin
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
        nk_layout_row_begin(ctx, format, height, cols);
    }

    @Override
    public void layoutRowPush(float value) {
        nk_layout_row_push(ctx, value);
    }

    @Override
    public void layoutRowEnd() {
        nk_layout_row_end(ctx);
    }

    @Override
    public void layoutTemplateBegin(float height) {
        nk_layout_row_template_begin(ctx, height);
    }

    @Override
    public void layoutTemplatePushDynamic() {
        nk_layout_row_template_push_dynamic(ctx);
    }

    @Override
    public void layoutTemplatePushVariable(float minWidth) {
        nk_layout_row_template_push_variable(ctx, minWidth);
    }

    @Override
    public void layoutTemplatePushStatic(float width) {
        nk_layout_row_template_push_static(ctx, width);
    }

    @Override
    public void layoutTemplateEnd() {
        nk_layout_row_template_end(ctx);
    }

    @Override
    public void layoutSpaceBegin(int format, float height, int numWidgets) {
        nk_layout_space_begin(ctx, format, height, numWidgets);
    }

    @Override
    public void layoutSpacePush(GuiRect bounds) {
        stack.push();
        NkRect rect = NkRect.mallocStack(stack);
        nk_rect(bounds.x, bounds.y, bounds.w, bounds.h, rect);
        nk_layout_space_push(ctx, rect);
    }

    @Override
    public void layoutSpaceEnd() {
        nk_layout_space_end(ctx);
    }

    @Override
    public boolean groupBegin(String title, int flags) {
        return nk_group_begin(ctx, title, flags);
    }

    @Override
    public boolean groupBegin(String name, String title, int flags) {
        return nk_group_begin_titled(ctx, name, title, flags);
    }

    @Override
    public boolean groupBegin(int offsetX, int offsetY, String title, int flags) {
        return nk_group_scrolled_offset_begin(ctx, stack.ints(offsetX), stack.ints(offsetY), title, flags);
    }

    @Override
    public void groupEnd() {
        nk_group_end(ctx);
    }

    @Override
    public boolean treePush(int type, String title, boolean expanded) {
        return nk_tree_state_push(ctx, type, title, stack.ints(expanded ? 1 : 0));
    }

    @Override
    public boolean treeImagePush(int type, String title, Image image, boolean expanded) {
        throw new IllegalStateException("Not implemented yet!");
    }

    @Override
    public void treePop() {
        nk_tree_state_pop(ctx);
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

    @Override
    public GuiValue.Text valueText(String value, int maxLength) {
        return new NkGuiValue.NkText(value, maxLength);
    }

    @Override
    public GuiValue.Color valueColor(float r, float g, float b, float a) {
        return new NkGuiValue.NkColor(r, g, b, a);
    }
}
