package jx3d.platform.lwjgl3.nuklear;

import jx3d.io.event.*;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkVec2;
import org.lwjgl.system.MemoryStack;

import static jx3d.core.Module.*;

import static org.lwjgl.nuklear.Nuklear.*;

/**
 * Nuklear input.
 */
public class NkInput extends EventAdapter {

    private NkContext ctx;

    public NkInput(NkContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void keyDown(KeyEvent event) {
        key(event, true);
    }

    @Override
    public void keyUp(KeyEvent event) {
        key(event, false);
    }

    public void key(KeyEvent event, boolean down) {
        nk_input_key(ctx, NK_KEY_SHIFT, event.isShiftDown());
        nk_input_key(ctx, NK_KEY_CTRL, event.isControlDown());

        if (event.isControlDown()) {
            switch (event.getKey()) {
                case KEY_C:
                    nk_input_key(ctx, NK_KEY_COPY, down);
                    break;
                case KEY_P:
                    nk_input_key(ctx, NK_KEY_PASTE, down);
                    break;
                case KEY_X:
                    nk_input_key(ctx, NK_KEY_CUT, down);
                    break;
                case KEY_Z:
                    nk_input_key(ctx, NK_KEY_TEXT_UNDO, down);
                    break;
                case KEY_R:
                    nk_input_key(ctx, NK_KEY_TEXT_REDO, down);
                    break;
                case KEY_LEFT:
                    nk_input_key(ctx, NK_KEY_TEXT_WORD_LEFT, down);
                    break;
                case KEY_RIGHT:
                    nk_input_key(ctx, NK_KEY_TEXT_WORD_RIGHT, down);
                    break;
                case KEY_HOME:
                    nk_input_key(ctx, NK_KEY_TEXT_START, down);
                    nk_input_key(ctx, NK_KEY_SCROLL_START, down);
                    break;
                case KEY_END:
                    nk_input_key(ctx, NK_KEY_TEXT_END, down);
                    nk_input_key(ctx, NK_KEY_SCROLL_END, down);
                    break;
                case KEY_A:
                    nk_input_key(ctx, NK_KEY_TEXT_SELECT_ALL, down);
                    break;
            }
        } else {
            switch (event.getKey()) {
                case KEY_DELETE:
                    nk_input_key(ctx, NK_KEY_DEL, down);
                    break;
                case KEY_ENTER:
                    nk_input_key(ctx, NK_KEY_ENTER, down);
                    break;
                case KEY_TAB:
                    nk_input_key(ctx, NK_KEY_TAB, down);
                    break;
                case KEY_BACKSPACE:
                    nk_input_key(ctx, NK_KEY_BACKSPACE, down);
                    break;
                case KEY_UP:
                    nk_input_key(ctx, NK_KEY_UP, down);
                    break;
                case KEY_DOWN:
                    nk_input_key(ctx, NK_KEY_DOWN, down);
                    break;
                case KEY_HOME:
                    nk_input_key(ctx, NK_KEY_TEXT_LINE_START, down);
                    break;
                case KEY_END:
                    nk_input_key(ctx, NK_KEY_TEXT_LINE_END, down);
                    break;
                case KEY_PAGE_DOWN:
                    nk_input_key(ctx, NK_KEY_SCROLL_DOWN, down);
                    break;
                case KEY_PAGE_UP:
                    nk_input_key(ctx, NK_KEY_SCROLL_UP, down);
                    break;
                case KEY_LEFT_CONTROL:
                case KEY_RIGHT_CONTROL:
                    if (!down) {
                        nk_input_key(ctx, NK_KEY_COPY, false);
                        nk_input_key(ctx, NK_KEY_PASTE, false);
                        nk_input_key(ctx, NK_KEY_CUT, false);
                        nk_input_key(ctx, NK_KEY_SHIFT, false);
                        nk_input_key(ctx, NK_KEY_TEXT_WORD_LEFT, false);
                        nk_input_key(ctx, NK_KEY_TEXT_WORD_RIGHT, false);
                        nk_input_key(ctx, NK_KEY_TEXT_START, false);
                        nk_input_key(ctx, NK_KEY_SCROLL_START, false);
                        nk_input_key(ctx, NK_KEY_TEXT_END, false);
                        nk_input_key(ctx, NK_KEY_SCROLL_END, false);
                        nk_input_key(ctx, NK_KEY_TEXT_SELECT_ALL, false);
                    }
                    break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        nk_input_motion(ctx, (int) (event.getX() / NkLayer.SCALE), (int) (event.getY() / NkLayer.SCALE));
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        nk_input_motion(ctx, (int) (event.getX() / NkLayer.SCALE), (int) (event.getY() / NkLayer.SCALE));
    }

    @Override
    public void character(CharacterEvent event) {
        nk_input_unicode(ctx, event.getUnicode());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        mouse(event, true);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mouse(event, false);
    }

    public void mouse(MouseEvent event, boolean pressed) {
        int nkButton;
        switch (event.getButton()) {
            case MOUSE_BUTTON_RIGHT:
                nkButton = NK_BUTTON_RIGHT;
                break;
            case MOUSE_BUTTON_MIDDLE:
                nkButton = NK_BUTTON_MIDDLE;
                break;
            default:
                nkButton = NK_BUTTON_LEFT;
        }
        nk_input_button(ctx, nkButton, (int) (event.getX() / NkLayer.SCALE), (int) (event.getY() / NkLayer.SCALE), pressed);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            NkVec2 vec = NkVec2.mallocStack(stack);
            nk_vec2(event.getScrollX(), event.getScrollY(), vec);
            nk_input_scroll(ctx, vec);
        }
    }
}
