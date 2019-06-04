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
        }
        gui.end();
    }

    @Override
    public void onEvent(Event event) {
        dispatcher.dispatch(event);
    }
}