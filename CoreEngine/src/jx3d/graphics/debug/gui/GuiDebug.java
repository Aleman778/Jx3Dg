package jx3d.graphics.debug.gui;

import jx3d.graphics.Image;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class GuiDebug {

    private static GuiDebug instance;

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

    public final int
        UP    = 0,
        RIGHT = 1,
        DOWN  = 2,
        LEFT  = 3;

    public final int
        TEXT_ALIGN_LEFT     = 0x1,
        TEXT_ALIGN_CENTERED = 0x2,
        TEXT_ALIGN_RIGHT    = 0x4,
        TEXT_ALIGN_TOP      = 0x8,
        TEXT_ALIGN_MIDDLE   = 0x10,
        TEXT_ALIGN_BOTTOM   = 0x20;

//    public final int
//        FILTER_ASCII = 1,
//        ,

    public static final int
        RGB  = 0,
        RGBA = 1;

    public GuiDebug() {
        assert instance != null : "There already exists a debug gui!";
        this.instance = this;
    }

    /**
     * Creates a new window, this has to be called every frame for every
     * window (unless it is suppose to be hidden) or otherwise the window gets removed.
     * @param title a unique title of the window (and its identifier)
     * @param rect a window rectangle area
     * @param flags set window flags e.g. {@link #WINDOW_BORDER}
     * @return
     */
    public abstract boolean begin(String title, GuiRect rect, int flags);

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
    public abstract boolean begin(String name, String title, GuiRect rect, int flags);

    /**
     * Needs to be called at the end after the window is built.
     */
    public abstract void end();

    /**
     * Creates a label left aligned.
     * @param text the text to display in label
     */
    public final void label(String text) {
        label(text, LEFT);
    }

    /**
     * Creates a label with a specific alignment.
     * @param text the text to display in label
     */
    public abstract void label(String text, int align);

    /**
     * Creates a button with a label.
     * @param label the text to display
     * @return true if the button is pressed, false otherwise
     */
    public abstract boolean button(String label);

    /**
     * Creates a checkbox with a label.
     * @param label the text to display
     * @param active set true if the checkbox active, false otherwise
     * @return true if the checkbox is pressed, false otherwise
     */
    public abstract boolean checkbox(String label, boolean active);

    /**
     * Creates a radio button with a label.
     * @param label the text to display
     * @param active set true if the radio button is active, false otherwise
     * @return true if the radio button is pressed, false otherwise
     */
    public abstract boolean radioButton(String label, boolean active);

    /**
     * Creates a selectable widget with a label.
     * @param label the text to display
     * @param align the alignment of the label
     * @param selected set true if the widget is selected, false if not
     * @return true if the selectable widget is pressed, false otherwise
     */
    public abstract boolean selectable(String label, int align, boolean selected);

    /**
     * Creates a integer slider widget with specific min, max and step values.
     * @param value the value of the slider
     * @param min the minimum value
     * @param max the maximum value
     * @param step the change of value at each tick of the slider
     */
    public abstract void sliderInt(GuiValue.Int value, int min, int max, int step);

    /**
     * Creates a float slider widget with specific min, max and step values.
     * @param value the value of the slider
     * @param min the minimum value
     * @param max the maximum value
     * @param step the change of value at each tick of the slider
     */
    public abstract void sliderFloat(GuiValue.Float value, float min, float max, float step);

    /**
     * Creates a integer property widget with specific min, max and step values.
     * @param value the value of the slider
     * @param min the minimum value
     * @param max the maximum value
     * @param step the change of value at each tick of the property slider
     * @param incPerPixel the number of inces in one pixel
     */
    public abstract void propertyInt(String name, GuiValue.Int value, int min, int max, int step, float incPerPixel);

    /**
     * Creates a float property widget with specific min, max and step values.
     * @param value the value of the slider
     * @param min the minimum value
     * @param max the maximum value
     * @param step the change of value at each tick of the property slider
     * @param incPerPixel the number of inces in one pixel
     */
    public abstract void propertyFloat(String name, GuiValue.Float value, float min, float max, float step, float incPerPixel);

    /**
     * Creates a progress bar widget with specific max value.
     * @param value the current value of the progress
     * @param max the maximum value when the current value reaches this value then the progress is completed
     * @param modifyable the progress bar is modifyable by the user
     */
    public abstract void progress(GuiValue.Progress value, boolean modifiable);

    /**
     * Create a text field widget with specific text and maximum size.
     * @param text the text to display and edit
     * @param filter filter only certain types of characters e.g. ASCII, decimal, binary etc.
     */
    public abstract void textField(GuiValue.Text text, int filter);

    /**
     * Create a color picker widget with specific color format
     * @param color the color to set the picker to display
     * @param format the format either <code>RGB</code> or <code>RGBA</code>
     */
    public abstract void colorPicker(GuiValue.Color color, int format);


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
    public abstract void layoutSpacePush(GuiRect bounds);
    public abstract void layoutSpaceEnd();

    public abstract boolean groupBegin(String title, int flags);
    public abstract boolean groupBegin(String name, String title, int flags);
    public abstract boolean groupBegin(int offsetX, int offsetY, String title, int flags);
    public abstract void groupEnd();

    public abstract boolean treePush(int type, String title, boolean expanded);
    public abstract boolean treeImagePush(int type, String title, Image image, boolean expanded);
    public abstract void treePop();

    public GuiRect rect(float x, float y, float w, float h) {
        return new GuiRect(x, y, w, h);
    }

    public abstract GuiValue.Int valueInt(int value);

    public abstract GuiValue.Float valueFloat(float value);

    public abstract GuiValue.Progress valueProgress(long max);

    public abstract GuiValue.Text valueText(String value, int maxLength);

    public abstract GuiValue.Color valueColor(float r, float g, float b, float a);

    public static GuiValue.Int createValueInt(int value) {
        return getInstance().valueInt(value);
    }

    public static GuiValue.Float createValueFloat(float value) {
        return getInstance().valueFloat(value);
    }

    public static GuiValue.Progress createValueProgress(long value) {
        return getInstance().valueProgress(value);
    }

    public static GuiValue.Text createValueText(String value, int maxLength) {
        return getInstance().valueText(value, maxLength);
    }

    public static GuiValue.Color createValueColor(float r, float g, float b, float a) {
        return getInstance().valueColor(r, g, b, a);
    }

    public static GuiDebug getInstance() {
        return instance;
    }
}
