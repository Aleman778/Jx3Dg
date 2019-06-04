package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.graphics.Image;
import jx3d.graphics.debug.gui.GuiRect;
import jx3d.graphics.debug.gui.GuiValue;
import org.lwjgl.nuklear.*;
import org.lwjgl.system.MemoryStack;

import static jx3d.core.Module.MOUSE_BUTTON_MIDDLE;
import static jx3d.core.Module.MOUSE_BUTTON_RIGHT;
import static org.lwjgl.nuklear.Nuklear.*;

public class NkGuiDebug extends GuiDebug {

    private NkContext ctx;
    private MemoryStack stack;
    private boolean rowLayout = false;
    private int colSize = 0;
    private boolean spaceLayout = false;
    private int widgetwidth = 0;
    private boolean baseLayout = false;


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

    @Override
    public void chartBegin(int type, int count, float min, float max) {
        nk_chart_begin(ctx, type, count, min, max);
    }

    @Override
    public void chartPush(float value) {
        nk_chart_push(ctx, value);
    }

    @Override
    public void chartEnd() {
        nk_chart_end(ctx);
    }

    @Override
    public boolean comboBegin(String label, int width, int height) {
        NkVec2 size = NkVec2.mallocStack(stack);
        nk_vec2(width, height, size);
        return nk_combo_begin_label(ctx, label, size);
    }

    @Override
    public boolean comboItem(String label) {
        return comboItem(label, LEFT);
    }

    @Override
    public boolean comboItem(String label, int align) {
        return nk_combo_item_label(ctx, label, align);
    }

    @Override
    public void comboEnd() {
        nk_combo_end(ctx);
    }

    @Override
    public boolean tooltipBegin(float width) {
        return nk_tooltip_begin(ctx, width);
    }

    @Override
    public void tooltip(String text) {
        nk_tooltip(ctx, text);
    }

    @Override
    public void tooltipEnd() {
        nk_tooltip_end(ctx);
    }

    @Override
    public boolean menuBegin(String label, float width, float height) {
        NkVec2 vec = NkVec2.mallocStack(stack);
        nk_vec2(width, height, vec);
        return nk_menu_begin_label(ctx, label, LEFT, vec);
    }

    @Override
    public boolean menuItem(String label) {
        return nk_menu_item_label(ctx, label, LEFT);
    }

    @Override
    public void menuEnd() {
        nk_menu_end(ctx);
    }

    @Override
    public void layoutRowDynamic(float height, int cols) {
        nk_layout_row_dynamic(ctx, height, cols);
        rowLayout = true;
    }

    @Override
    public void layoutRowStatic(float height, int colWidth, int cols) {
        nk_layout_row_static(ctx, height, colWidth, cols);
        rowLayout = true;
    }

    @Override
    public void layoutRowBegin(int format, float height, int cols) {
        nk_layout_row_begin(ctx, format, height, cols);
        rowLayout = true;
    }

    @Override
    public void layoutRowPush(float value) {
        nk_layout_row_push(ctx, value);
        rowLayout = true;
    }

    @Override
    public void layoutRowEnd() {
        nk_layout_row_end(ctx);
        rowLayout = false;
    }

    @Override
    public void layoutTemplateBegin(float height) {
        nk_layout_row_template_begin(ctx, height);
        rowLayout = true;
    }

    @Override
    public void layoutTemplatePushDynamic() {
        nk_layout_row_template_push_dynamic(ctx);
        rowLayout = true;
    }

    @Override
    public void layoutTemplatePushVariable(float minWidth) {
        nk_layout_row_template_push_variable(ctx, minWidth);
        rowLayout = true;
    }

    @Override
    public void layoutTemplatePushStatic(float width) {
        nk_layout_row_template_push_static(ctx, width);
        rowLayout = true;
    }

    @Override
    public void layoutTemplateEnd() {
        nk_layout_row_template_end(ctx);
        rowLayout = false;
    }

    @Override
    public void layoutSpaceBegin(int format, float height, int numWidgets) {
        nk_layout_space_begin(ctx, format, height, numWidgets);
        spaceLayout = true;
    }

    @Override
    public void layoutSpacePush(GuiRect bounds) {
        stack.push();
        NkRect rect = NkRect.mallocStack(stack);
        nk_rect(bounds.x, bounds.y, bounds.w, bounds.h, rect);
        nk_layout_space_push(ctx, rect);
        spaceLayout = true;
    }

    @Override
    public void layoutSpaceEnd() {
        nk_layout_space_end(ctx);
        spaceLayout = false;
    }

    @Override
    public void sameLine() {
        if (baseLayout) {
            float width = nk_widget_width(ctx);
            nk_layout_row_template_push_variable(ctx, 10);
        }
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
    public boolean isWidgetHovered() {
        return nk_widget_is_hovered(ctx);
    }

    @Override
    public boolean isWidgetClicked(int button) {
        int nkButton;
        switch (button) {
            case MOUSE_BUTTON_RIGHT:
                nkButton = NK_BUTTON_RIGHT;
                break;
            case MOUSE_BUTTON_MIDDLE:
                nkButton = NK_BUTTON_MIDDLE;
                break;
            default:
                nkButton = NK_BUTTON_LEFT;
        }
        return nk_widget_is_mouse_clicked(ctx, nkButton);
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

    private void layout() {
        boolean noLayout = false;
        if (rowLayout) {
            if (colSize > 0)
                colSize--;
            if (colSize == 0)
                rowLayout = false;
            noLayout = true;
        }
        if (spaceLayout) {
            spaceLayout = false;
            noLayout = true;
        }
        if (!baseLayout && noLayout) {
        }

        baseLayout = noLayout;

        if (noLayout) {


        }
    }
}
