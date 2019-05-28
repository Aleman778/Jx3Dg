package sandbox.layers;

import jx3d.core.JX3D;
import jx3d.core.Layer;
import jx3d.graphics.*;
import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.graphics.debug.gui.GuiValue;
import jx3d.graphics.opengl.GLTexture2D;
import jx3d.io.event.Event;
import jx3d.io.event.EventDispatcher;
import jx3d.io.event.EventType;
import jx3d.math.Transform;
import org.joml.Vector3f;
import sandbox.FreeMoving3DCamera;

import static jx3d.core.Module.*;

/**
 * Model viewer layer.
 */
public class ModelViewerLayer extends Layer {

    private static final int GUI_WIDTH = 400;

    private static final int HIGH = 0;
    private static final int MED = 1;
    private static final int LOW = 2;

    private VertexBuffer vbo;
    private IndexBuffer ibo;
    private VertexArray vao;
    private Shader shader;
    private Image image;
    private Texture2D tex;
    private Transform t;
    private PerspectiveCamera camera3D;
    private Graphics g;
    private EventDispatcher dispatcher;

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

    public ModelViewerLayer() {
        Mesh mesh = JX3D.files.loadShape("models/lamborghini/lambo.obj");
        image = JX3D.files.loadImage("models/lamborghini/lambo_diffuse.jpeg");


        vbo = VertexBuffer.create(mesh.vertexCount() * 5, STATIC_DRAW);
        vbo.bind();
        float[] vboData = new float[mesh.vertexCount() * 5];
        for (int i = 0; i < mesh.vertexCount(); i++) {
            Vector3f v = mesh.vertices[i];
            vboData[i * 5] = v.x;
            vboData[i * 5 + 1] = v.y;
            vboData[i * 5 + 2] = v.z;
        }
        for (int i = 0; i < mesh.uv.length; i++) {
            vboData[i * 5 + 3] = mesh.uv[i].x;
            vboData[i * 5 + 4] = mesh.uv[i].y;
        }
        vbo.set(vboData);


        ibo = IndexBuffer.create(mesh.indices.length, STATIC_DRAW);
        ibo.bind();
        ibo.set(mesh.indices);

        vao = VertexArray.create();

        VertexAttribute attribs = new VertexAttribute();
        attribs.add(0, 3, FLOAT, false, 20, 0);
        attribs.add(1, 2, FLOAT, false, 20, 12);

        vao.put(vbo, attribs);

        shader = JX3D.graphics.loadShader("shaders/basic_fragment.glsl", "shaders/basic_vertex.glsl");
        shader.setup();
        shader.enable();
        JX3D.gl30.activeTexture(0);

        shader.set("sampler", 0);

        tex = new GLTexture2D(false, false);
        tex.bind();
        tex.image(image);
        tex.setSample(LINEAR);

        //transformation
        t = new Transform();
        t.scale(new Vector3f(0.01f, 0.01f, 0.01f));

        camera3D = new FreeMoving3DCamera();

        shader.set("transform", t.getMapping());


        dispatcher = new EventDispatcher();
        dispatcher.addListener(EventType.MouseDragged, camera3D);
        dispatcher.addListener(EventType.MouseScrolled, camera3D);
    }

    @Override
    public void onUpdate() {
        JX3D.graphics.background(color.red(), color.green(), color.blue(), color.alpha());

        shader.enable();
        shader.set("transform", t.getMapping());
        shader.set("projection", camera3D.getMapping());

        vao.bind();
        ibo.bind();
        tex.bind();
        JX3D.graphics.viewport(0, 0, (int) JX3D.graphics.getWidth()-GUI_WIDTH, (int) JX3D.graphics.getHeight());
        JX3D.graphics.render(TRIANGLES, vao, ibo);
    }

    @Override
    public void onDebugGuiRender(GuiDebug gui) {
        if (gui.begin("Model Import Settings", gui.rect((int) JX3D.graphics.getWidth()-GUI_WIDTH, 0, GUI_WIDTH, (int) JX3D.graphics.getHeight()), gui.WINDOW_TITLE)) {
            gui.layoutRowDynamic(20, 1);
            gui.label("Select a model to import");
            //gui.layoutRowDynamic(30, 1);
            //gui.textField(text);

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
                gui.tooltip("Select a platform to run on");
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
        progress.incr(1);
    }

    @Override
    public void onEvent(Event event) {
        dispatcher.dispatch(event);
    }
}