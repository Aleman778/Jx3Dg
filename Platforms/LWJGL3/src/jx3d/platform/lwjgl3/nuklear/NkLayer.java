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
    private NkDebugGui debugGui;
    private NkContext ctx = NkContext.create();

    private EventDispatcher dispatcher;


    public NkLayer() {
        nk_init(ctx, ALLOCATOR, null);

        font = NkFont.createFromFile("jx3d/platform/lwjgl3/Roboto-Regular.ttf");
        input = new NkInput(ctx);
        dispatcher = new EventDispatcher();
        dispatcher.addListener(Module.INPUT_EVENTS, input);
        renderer = new NkRenderer(ctx);
        debugGui = new NkDebugGui(ctx);

        nk_style_set_font(ctx, font.getUserFont());
    }

    public void beginInput() {
        nk_input_begin(ctx);
    }

    public void endInput() {
        nk_input_end(ctx);
    }

    public NkDebugGui getDebugGui() {
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
