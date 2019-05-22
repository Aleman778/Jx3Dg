package jx3d.graphics;

public abstract class DebugGui {

    /**
     * Window flags.
     */
    public final int
        WINDOW_BORDER           = 1 << 0,
        WINDOW_MOVABLE          = 1 << 1,
        WINDOW_SCALABLE         = 1 << 2,
        WINDOW_CLOSABLE         = 1 << 3,
        WINDOW_MINIMIZABLE      = 1 << 4,
        WINDOW_NO_SCROLLBAR     = 1 << 5,
        WINDOW_TITLE            = 1 << 6,
        WINDOW_SCROLL_AUTO_HIDE = 1 << 7,
        WINDOW_BACKGROUND       = 1 << 8,
        WINDOW_SCALE_LEFT       = 1 << 9,
        WINDOW_NO_INPUT         = 1 << 10;

    public final int
        MINIMIZED = 0,
        MAXIMIZED = 1;

    public final int
        DYNAMIC = 0,
        STATIC  = 1;

    public final int
        TREE_NODE = 0,
        TREE_TAB  = 1;

    public DebugGui() {

    }

    /**
     * Creates a new window, this has to be called every frame for every
     * window (unless it is suppose to be hidden) or otherwise the window gets removed.
     * @param title a unique title of the window (and its identifier)
     * @param rect a window rectangle area
     * @param flags set window flags e.g. {@link #WINDOW_BORDER}
     * @return
     */
    public abstract boolean begin(String title, Rect rect, int flags);

    /**
     * Creates a new window with a specific name, this has to be called every frame for every
     * window (unless it is suppose to be hidden) or otherwise the window gets removed.
     * The name is a unique identifier instead of using the window title as the identifier.
     * @param name a unique name of this window (the identifier)
     * @param title a title of the window
     * @param rect the window rectangle area
     * @param flags the window flags e.g. {@link #WINDOW_BORDER}
     * @return
     */
    public abstract boolean begin(String name, String title, Rect rect, int flags);

    /**
     * Needs to be called at the end after the window is built.
     */
    public abstract void end();

    /**
     * Create a button with a label.
     * @param label
     * @return
     */
    public abstract boolean button(String label);



    /**
     * NOTE: This is an inspiration from ImGui and is not directly supported by Nuklear.
     * @param position
     * @param spacing
     */
    public abstract void sameLine(float position, float spacing);

    /**
     * Set the current row layout to share horizontal column space between the widgets evenly.
     * @param height holds height of each widget or zero for auto height
     * @param cols the number of columns inside this row
     * @see #layoutRowStatic(float, int, int)
     */
    public abstract void layoutRowDynamic(float height, int cols);

    /**
     * Set the current row layout to fill the column space of widgets with the same width.
     * @param height holds height of each widget or zero for auto height
     * @param colWidth holds the pixel width of each widget in the row
     * @param cols the number of columns inside this row
     * @see #layoutRowDynamic(float, int)
     */
    public abstract void layoutRowStatic(float height, int colWidth, int cols);

    /**
     * Starts a new dynamic of static row with given height and number of columns.
     * @param format either {@link #DYNAMIC} (for window ratio) or {@link #STATIC} (for fixed size columns)
     * @param height holds height of each widget or zero for auto height
     * @param cols the number of columns inside this row
     * @see #layoutRowPush(float)
     * @see #layoutRowEnd()
     */
    public abstract void layoutRowBegin(int format, float height, int cols);

    /**
     * Specifies either window ratio (for {@link #DYNAMIC} layout)
     * or the width of a single column  (for {@link #STATIC} layout).
     * @param value either window ratio layout or the width of a single column
     * @see #layoutRowBegin(int, float, int)
     * @see #layoutRowEnd()
     */
    public abstract void layoutRowPush(float value);

    /**
     * Finish previous started row.
     * @see #layoutRowBegin(int, float, int)
     * @see #layoutRowPush(float)
     */
    public abstract void layoutRowEnd();

    public abstract void layoutTemplateBegin(float height);
    public abstract void layoutTemplatePushDynamic();
    public abstract void layoutTemplatePushVariable(float minWidth);
    public abstract void layoutTemplatePushStatic(float width);
    public abstract void layoutTemplateEnd();

    public abstract void layoutSpaceBegin(int format, float height, int numWidgets);
    public abstract void layoutSpacePush(Rect bounds);
    public abstract void layoutSpaceEnd();

    public abstract void groupBegin(String title, int flags);
    public abstract void groupBegin(String name, String title, int flags);
    public abstract void groupBegin(int offsetX, int offsetY, String title, int flags);
    public abstract void groupEnd();
    public abstract void group();

    public abstract void treePush(int type, String title, int state);
    public abstract void treePushLoop(int type, String title, int state, int level);
    //void treePushHashed();
    public abstract void treeImagePush(int type, String title, Image image, int state);
    public abstract void treeImagePush();
    //void treeImagePushHashed();
    public abstract void treePop();
    //void treeStatePush();
    //void treeStateImagePush();
    //void treeStatePop();




    public Rect rect(float x, float y, float w, float h) {
        return new Rect(x, y, w, h);
    }


    /**
     * Rectangle class.
     */
    protected class Rect {

        public float x, y, w, h;

        protected Rect(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }
}
