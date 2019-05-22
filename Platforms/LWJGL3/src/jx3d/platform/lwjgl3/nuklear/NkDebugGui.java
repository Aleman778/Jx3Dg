package jx3d.platform.lwjgl3.nuklear;

import jx3d.graphics.DebugGui;
import jx3d.graphics.Image;
import org.lwjgl.nuklear.*;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.nuklear.Nuklear.*;

public class NkDebugGui extends DebugGui {

    private NkContext ctx;
    private MemoryStack stack;


    public NkDebugGui(NkContext ctx) {
        this.ctx = ctx;
        this.stack = MemoryStack.stackGet();
    }

    @Override
    public boolean begin(String title, Rect rect, int flags) {
        stack.push();
        NkRect bounds = NkRect.mallocStack(stack);
        nk_rect(rect.x, rect.y, rect.w, rect.h, bounds);
        return nk_begin(ctx, title, bounds, flags);
    }

    @Override
    public boolean begin(String name, String title, Rect rect, int flags) {
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
    public boolean button(String label) {
        return nk_button_label(ctx, label);
    }

    @Override
    public void sameLine(float position, float spacing) {
        throw new IllegalStateException("Not implemented yet!");
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
    public void layoutSpacePush(Rect bounds) {

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
}
