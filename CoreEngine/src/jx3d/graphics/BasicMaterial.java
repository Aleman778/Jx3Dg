package jx3d.graphics;

import static jx3d.core.Module.BUTT;
import static jx3d.core.Module.MITER;

/**
 * Basic material uses stroke, fill and tint.
 *
 * @author Aleman778
 * @since 1.0
 */
public class BasicMaterial extends Material {

    /**
     * Enable stroke.
     */
    private boolean stroke = true;

    /**
     * The stroke width (or weight).
     */
    private float strokeWidth = 1.0f;

    /**
     * The stroke color.
     */
    private Color strokeColor = Color.BLACK;

    /**
     * The cap stroke setting.
     */
    private int strokeCap = BUTT;

    /**
     * The join stroke setting.
     */
    private int strokeJoin = MITER;

    /**
     * Enable fill.
     */
    private boolean fill = true;

    /**
     * The fill color.
     */
    private Color fillColor = Color.WHITE;

    /**
     * Enable tint.
     */
    private boolean tint = false;

    /**
     * The tint color.
     */
    private Color tintColor = Color.TRANSPARENT;

    /**
     * Constructor.
     */
    public BasicMaterial() {
    }

}
