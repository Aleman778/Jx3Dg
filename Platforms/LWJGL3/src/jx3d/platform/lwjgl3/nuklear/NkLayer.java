package jx3d.platform.lwjgl3.nuklear;

import jx3d.core.Layer;
import jx3d.core.Module;
import jx3d.io.event.Event;
import jx3d.io.event.EventDispatcher;
import org.lwjgl.nuklear.*;

import java.util.Objects;

import static org.lwjgl.nuklear.Nuklear.*;
import static org.lwjgl.system.MemoryUtil.*;

public class NkLayer extends Layer {

    public static final float SCALE = 1.8f;

    private static final int BUFFER_INITIAL_SIZE = 4 * 1024;

    /**
     * The nuklear allocator object.
     */
    static final NkAllocator ALLOCATOR;

    static {
        ALLOCATOR = NkAllocator.create()
                .alloc((handle, old, size) -> nmemAllocChecked(size))
                .mfree((handle, ptr) -> nmemFree(ptr));
    }

    private NkFont font;
    private NkInput input;
    private NkRenderer renderer;
    private NkGuiDebug debugGui;
    private NkContext ctx = NkContext.create();

    //The size of the GUI layer area, if less than the window size it will be
    //automatically resized to fit the window size same if larger.
    private int width = 640;
    private int height = 480;

    private EventDispatcher dispatcher;


    public NkLayer() {
        nk_init(ctx, ALLOCATOR, null);

        font = NkFont.createFromFile("jx3d/platform/lwjgl3/Roboto-Regular.ttf");
        input = new NkInput(ctx);
        dispatcher = new EventDispatcher();
        dispatcher.addListener(Module.INPUT_EVENTS, input);
        renderer = new NkRenderer(ctx);
        debugGui = new NkGuiDebug(ctx);


        nk_style_set_font(ctx, font.getUserFont());
//        try (MemoryStack stack = MemoryStack.stackPush()) {
//            NkStyle style = ctx.style();
//            NkStyleToggle checkbox = style.checkbox();
//            NkStyleItem item = NkStyleItem.callocStack(stack);
//            NkColor color = NkColor.callocStack(stack);
//            color.r((byte) 255);
//            color.a((byte) 255);
//            nk_style_item_color(color, item);
//            checkbox.normal(item);
//        }
//        NkColor.Buffer table = NkColor.create(28);
//        table.put(NK_COLOR_TEXT,                    NkColor.create().set((byte) (255*1.00f), (byte) (255*1.00f), (byte) (255*1.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_WINDOW,                  NkColor.create().set((byte) (255*0.18f), (byte) (255*0.18f), (byte) (255*0.18f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_HEADER,                  NkColor.create().set((byte) (255*0.16f), (byte) (255*0.29f), (byte) (255*0.48f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_BORDER,                  NkColor.create().set((byte) (255*0.42f), (byte) (255*0.43f), (byte) (255*0.50f), (byte) (255*0.50f)));
//        table.put(NK_COLOR_BUTTON,                  NkColor.create().set((byte) (255*0.10f), (byte) (255*0.24f), (byte) (255*0.40f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_BUTTON_HOVER,            NkColor.create().set((byte) (255*0.26f), (byte) (255*0.49f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_BUTTON_ACTIVE,           NkColor.create().set((byte) (255*0.06f), (byte) (255*0.53f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_TOGGLE,                  NkColor.create().set((byte) (255*0.10f), (byte) (255*0.24f), (byte) (255*0.40f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_TOGGLE_HOVER,            NkColor.create().set((byte) (255*0.26f), (byte) (255*0.49f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_TOGGLE_CURSOR,           NkColor.create().set((byte) (255*1.00f), (byte) (255*1.00f), (byte) (255*1.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SELECT,                  NkColor.create().set((byte) (255*0.10f), (byte) (255*0.24f), (byte) (255*0.40f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SELECT_ACTIVE,           NkColor.create().set((byte) (255*0.06f), (byte) (255*0.53f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SLIDER,                  NkColor.create().set((byte) (255*0.10f), (byte) (255*0.24f), (byte) (255*0.40f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SLIDER_CURSOR,           NkColor.create().set((byte) (255*0.26f), (byte) (255*0.59f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SLIDER_CURSOR_HOVER,     NkColor.create().set((byte) (255*0.26f), (byte) (255*0.59f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SLIDER_CURSOR_ACTIVE,    NkColor.create().set((byte) (255*0.06f), (byte) (255*0.53f), (byte) (255*0.98f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_PROPERTY,                NkColor.create().set((byte) (255*0.10f), (byte) (255*0.24f), (byte) (255*0.40f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_EDIT,                    NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_EDIT_CURSOR,             NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_COMBO,                   NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_CHART,                   NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_CHART_COLOR,             NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_CHART_COLOR_HIGHLIGHT,   NkColor.create().set((byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*0.00f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SCROLLBAR,               NkColor.create().set((byte) (255*0.02f), (byte) (255*0.02f), (byte) (255*0.02f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SCROLLBAR_CURSOR,        NkColor.create().set((byte) (255*0.31f), (byte) (255*0.31f), (byte) (255*0.31f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SCROLLBAR_CURSOR_HOVER,  NkColor.create().set((byte) (255*0.41f), (byte) (255*0.41f), (byte) (255*0.41f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_SCROLLBAR_CURSOR_ACTIVE, NkColor.create().set((byte) (255*0.51f), (byte) (255*0.51f), (byte) (255*0.51f), (byte) (255*1.00f)));
//        table.put(NK_COLOR_TAB_HEADER,              NkColor.create().set((byte) (255*0.16f), (byte) (255*0.29f), (byte) (255*0.48f), (byte) (255*1.00f)));
//        nk_style_from_table(ctx, table);
    }

    public void beginInput() {
        nk_input_begin(ctx);
    }

    public void endInput() {
        nk_input_end(ctx);
    }

    public NkGuiDebug getDebugGui() {
        return debugGui;
    }

    public void render() {
        renderer.present();
    }

    @Override
    public void onEvent(Event event) {
        dispatcher.dispatch(event);
    }

    @Override
    public void dispose() {
        //Objects.requireNonNull(ctx.clip().copy()).free();
        //Objects.requireNonNull(ctx.clip().paste()).free();

        nk_free(ctx);
        Objects.requireNonNull(font.getUserFont().query()).free();
        Objects.requireNonNull(font.getUserFont().width()).free();

        renderer.dispose();

        Objects.requireNonNull(ALLOCATOR.alloc()).free();
        Objects.requireNonNull(ALLOCATOR.mfree()).free();
    }
}
