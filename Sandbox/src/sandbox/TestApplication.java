package sandbox;

import jx3d.core.ApplicationModule;
import jx3d.core.JX3D;
import jx3d.core.Node;
import jx3d.graphics.*;
import jx3d.graphics.opengl.GLTexture2D;
import jx3d.io.event.KeyEvent;
import jx3d.io.event.MouseEvent;
import jx3d.io.event.MouseScrollEvent;
import jx3d.io.event.WindowEvent;
import jx3d.math.Transform;
import org.joml.Vector3f;

/**
 * Test application.
 *
 * @author Aleman778
 */
public class TestApplication extends ApplicationModule {

    private VertexBuffer vbo;
    private IndexBuffer ibo;
    private VertexArray vao;
    private Shader shader;
    private Image image;
    private Texture2D tex;
    private Transform t;
    private OrthographicCamera camera2D;
    private PerspectiveCamera camera3D;

    @Override
    public void setup() {
        Mesh mesh = loadShape("models/lamborghini/lambo.obj");
        image = loadImage("models/lamborghini/lambo_diffuse.jpeg");


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
        attribs.add(0, 3, false, 5, 0);
        attribs.add(1, 2, false, 5, 3);

        vao.put(vbo, attribs);

        shader = loadShader("shaders/basic_fragment.glsl", "shaders/basic_vertex.glsl");
        shader.setup();
        shader.enable();
        JX3D.gl30.activeTexture(0);

        shader.set("sampler", 0);

        tex = new GLTexture2D(false, false);
        tex.bind();
        tex.image(image);
        tex.setSample(LINEAR);

        //Transformation
        t = new Transform();
        t.scale(new Vector3f(0.01f, 0.01f, 0.01f));
        //t.setOrigin(new Vector3f(209, 209, 0));
        //t.translate(new Vector3f(getWidth()/2.0f, getHeight()/2.0f, 0));
        //t.setScale(new Vector3f(0.1f, 1.0f, 1.0f));

        //Free Moving 3D Camera
        camera3D = new FreeMoving3DCamera();

        //camera3D.setAspectRatio(getAspectRatio());
        //camera3D.useWindowAspectRatio();

        //Perspective camera
        //camera3D = new PerspectiveCamera();
        //camera3D.useWindowAspectRatio();

        //Orthographic camera
        camera2D = new OrthographicCamera();
        camera2D.setOrtho(0, 640, 480, 0);

        //shader.set("projection", perspective);
        viewport(0, 0, 640, 480);

        //APPLY TRANSFORMATION
        shader.set("transform", t.getMapping());

        Node hello = new Node() {

        };
    }

    @Override
    public void draw() {
        JX3D.graphics.background(0.0f, 0.5f, 1.0f, 1.0f);

        shader.set("transform", t.getMapping());
        shader.set("projection", camera3D.getMapping());
        shader.enable();

        tex.bind();
        JX3D.graphics.render(TRIANGLES, vao, ibo);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.isShiftDown()) {
            System.out.println("Mouse button " + event.getButton() + " pressed with shift key down");
        }
    }

    @Override
    public void keyDown(KeyEvent event) {
        if (event.isControlDown()) {
            if (event.isRepeated()) {
                System.out.println("Key pressed " + event.getKey() + " pressed with control key down (Ctrl-" + event.getKeyChar() + ")");
            } else {
                System.out.println("Repeated key pressed " + event.getKey() + " pressed with control key down (Ctrl-" + event.getKeyChar() + ")");
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        camera3D.mouseDragged(event);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        camera3D.mouseScrolled(event);
    }

    @Override
    public void windowClose(WindowEvent event) {
        System.out.println("Closing window now!");
    }
}
