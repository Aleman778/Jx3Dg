package sandbox.layers;

import jx3d.core.JX3D;
import jx3d.core.Layer;
import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.graphics.debug.gui.GuiValue;

public class GuiDebugLayer extends Layer {

    private static final int HIGH = 0;
    private static final int MED = 1;
    private static final int LOW = 2;

    private boolean preprocessingNode;
    private boolean chartNode;
    private boolean comboNode;
    private boolean contextualNode;
    private boolean backgroundTab;
    private boolean triangulateFacecs;
    private int lod = HIGH;
    private int lod2 = HIGH;
    private GuiValue.Float sliderF = GuiDebug.createValueFloat(0.5f);
    private GuiValue.Int sliderI = GuiDebug.createValueInt(50);
    private GuiValue.Float propertyF = GuiDebug.createValueFloat(0.5f);
    private GuiValue.Int propertyI = GuiDebug.createValueInt(50);
    private GuiValue.Progress progress = GuiDebug.createValueProgress(1000);
    private GuiValue.Text text = GuiDebug.createValueText("models/lamborghini/lambo.obj", 999);
    private GuiValue.Color color = GuiDebug.createValueColor(0.0f, 0.5f, 1.0f, 1.0f);
    private String platform = "Desktop";

    @Override
    public void onUpdate() {
        JX3D.graphics.background(color.red(), color.green(), color.blue(), color.alpha());
        JX3D.graphics.viewport(0, 0, (int) JX3D.graphics.getWidth(), (int) JX3D.graphics.getHeight());
    }

    @Override
    public void onDebugGuiRender(GuiDebug gui) {
        int flags = gui.WINDOW_TITLE | gui.WINDOW_MINIMIZABLE | gui.WINDOW_SCALABLE |gui.WINDOW_MOVABLE;
        if (gui.begin("Debug GUI Demo", gui.rect(50, 50, 400, 600), flags)) {
            gui.layoutRowDynamic(24, 1);
            if (gui.menuBegin("File", 120, 24*7)) {
                gui.layoutRowDynamic(24, 1);
                gui.menuItem("New");
                gui.layoutRowDynamic(24, 1);
                gui.menuItem("Open");
                gui.layoutRowDynamic(24, 1);
                if (gui.menuBegin("Open Recent", 48, 24)) {
                    gui.layoutRowDynamic(24, 1);
                    gui.menuItem("Test Project 1");
                    gui.layoutRowDynamic(24, 1);
                    gui.menuItem("Test Project 2");
                    gui.layoutRowDynamic(24, 1);
                    gui.menuItem("Test Project 3");
                    gui.menuEnd();
                }
                gui.layoutRowDynamic(24, 1);
                gui.menuItem("Exit");
                gui.menuEnd();
            }

            if (preprocessingNode = gui.treePush(gui.TREE_NODE, "Toggle widgets", preprocessingNode)) {
                gui.layoutRowDynamic(20, 1);
                gui.label("Level of detail");

                gui.layoutRowDynamic(20, 3);
                if (gui.radioButton("Low", lod == LOW)) { lod = LOW; }
                if (gui.radioButton("Medium", lod == MED)) { lod = MED; }
                if (gui.radioButton("High", lod == HIGH)) { lod = HIGH; }

                gui.layoutRowDynamic(20, 1);
                gui.label("Selectable level of detail");

                gui.layoutRowDynamic(25, 3);
                if (gui.selectable("Low", gui.TEXT_ALIGN_CENTERED, lod2 == LOW)) { lod2 = LOW; }
                if (gui.selectable("Medium", gui.TEXT_ALIGN_CENTERED, lod2 == MED)) { lod2 = MED; }
                if (gui.selectable("High", gui.TEXT_ALIGN_CENTERED, lod2 == HIGH)) { lod2 = HIGH; }
                gui.treePop();


                gui.layoutRowDynamic(20, 1);
                gui.label("Assimp preprocessing flags");

                gui.layoutRowDynamic(20, 1);
                if (gui.checkbox("Triangulate faces", triangulateFacecs)) {
                    triangulateFacecs = !triangulateFacecs;
                }
            }

            gui.layoutRowDynamic(150, 1);
            if (gui.groupBegin("Actions", gui.WINDOW_TITLE | gui.WINDOW_BORDER)) {
                gui.layoutRowDynamic(25, 1);
                gui.button("Import model");

                gui.layoutRowDynamic(25, 1);
                gui.button("Export model");

                gui.layoutRowDynamic(25, 1);
                gui.button("Convert model");

                gui.groupEnd();
            }



            if (chartNode = gui.treePush(gui.TREE_NODE, "Show charts", chartNode)) {
                gui.layoutRowDynamic(20, 1);
                gui.label("Chart type: CHART_LINES");

                gui.layoutRowDynamic(32, 1);
                gui.chartBegin(gui.CHART_LINES, 5, 0, 100);
                gui.chartPush(50);
                gui.chartPush(65);
                gui.chartPush(10);
                gui.chartPush(95);
                gui.chartPush(20);
                gui.chartEnd();

                gui.layoutRowDynamic(20, 1);
                gui.label("Chart type: CHART_MAX");

                gui.layoutRowDynamic(32, 1);
                gui.chartBegin(gui.CHART_MAX, 5, 0, 100);
                gui.chartPush(50);
                gui.chartPush(65);
                gui.chartPush(10);
                gui.chartPush(95);
                gui.chartPush(20);
                gui.chartEnd();

                gui.layoutRowDynamic(20, 1);
                gui.label("Chart type: CHART_COLUMN");

                gui.layoutRowDynamic(32, 1);
                gui.chartBegin(gui.CHART_COLUMN, 5, 0, 100);
                gui.chartPush(50);
                gui.chartPush(65);
                gui.chartPush(10);
                gui.chartPush(95);
                gui.chartPush(20);
                gui.chartEnd();

                gui.treePop();
            }
            if (comboNode = gui.treePush(gui.TREE_NODE, "Show combo boxes", comboNode)) {
                if (gui.comboBegin(platform, 160, 155)) {
                    gui.layoutRowDynamic(25, 1);
                    if (gui.comboItem("Desktop")) platform = "Desktop";
                    gui.layoutRowDynamic(25, 1);
                    if (gui.comboItem("HTML5 (WebGL)")) platform = "HTML5 (WebGL)";
                    gui.layoutRowDynamic(25, 1);
                    if (gui.comboItem("Android")) platform = "Android";
                    gui.layoutRowDynamic(25, 1);
                    if (gui.comboItem("iOS")) platform = "iOS";
                    gui.layoutRowDynamic(25, 1);
                    if (gui.comboItem("Nintendo Switch")) platform = "Nintendo Switch";
                    gui.comboEnd();
                }
                gui.treePop();
            }


            gui.layoutRowDynamic(20, 2);
            gui.sliderFloat(sliderF, 0.0f, 1.0f, 0.01f);
            gui.label("= " + sliderF.get() + " (float)");

            gui.layoutRowDynamic(20, 2);
            gui.sliderInt(sliderI, 0, 100, 1);
            gui.label("= " + sliderI.get() + " (integer)");

            gui.layoutRowDynamic(20, 1);
            gui.propertyFloat("Float property", propertyF, 0.0f, 1.0f, 0.01f, 0.01f);

            gui.layoutRowDynamic(20, 1);
            gui.propertyInt("Integer property", propertyI, 0, 100, 1, 1);


            gui.layoutRowDynamic(20, 1);
            gui.label("Progress bar");

            gui.layoutRowDynamic(20, 1);
            gui.progress(progress, false);

            gui.layoutRowDynamic(20, 1);
            if (backgroundTab = gui.treePush(gui.TREE_TAB, "Change background color", backgroundTab)) {
                gui.layoutRowDynamic(270, 1);
                gui.colorPicker(color, gui.RGB);
                gui.treePop();
            }
        }
        gui.end();
    }
}
