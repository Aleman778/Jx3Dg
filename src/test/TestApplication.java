package test;

import java.io.InputStream;
import org.joml.Vector3f;

import jx3d.graphics.*;
import jx3d.graphics.opengl.*;
import jx3d.lwjgl3.Lwjgl3GL30;
import jx3d.lwjgl3.Lwjgl3Window;
import jx3d.math.*;

/**
 * Test application.
 * @author Aleman778
 */
public class TestApplication extends Lwjgl3Window {
	
	private VertexBuffer vbo;
	private IndexBuffer ibo;
	private VertexArray vao;
	private Shader shader;
	private Image image;
	private Texture2D tex;
	private Transform t;
	private OrthographicCamera camera2D;
	private PerspectiveCamera camera3D;
	
	
	public TestApplication(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	public void setup() {
		float[] cube_vert = {
			 0.5f, -0.5f,  0.5f, 1.0f, 0.0f,
			 0.5f,  0.5f,  0.5f, 1.0f, 1.0f,
			-0.5f,  0.5f,  0.5f, 0.0f, 1.0f,
			-0.5f, -0.5f,  0.5f, 0.0f, 0.0f,
			
			 0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
			 0.5f,  0.5f, -0.5f, 1.0f, 1.0f,
			 0.5f,  0.5f,  0.5f, 0.0f, 1.0f,
			 0.5f, -0.5f,  0.5f, 0.0f, 0.0f,
			 
			-0.5f, -0.5f,  0.5f, 1.0f, 0.0f,
			-0.5f,  0.5f,  0.5f, 1.0f, 1.0f,
			-0.5f,  0.5f, -0.5f, 0.0f, 1.0f,
			-0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
			
			 0.5f,  0.5f,  0.5f, 1.0f, 0.0f,
			 0.5f,  0.5f, -0.5f, 1.0f, 1.0f,
			-0.5f,  0.5f, -0.5f, 0.0f, 1.0f,
			-0.5f,  0.5f,  0.5f, 0.0f, 0.0f,
			
			 0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
			 0.5f, -0.5f,  0.5f, 1.0f, 1.0f,
			-0.5f, -0.5f,  0.5f, 0.0f, 1.0f,
			-0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

			 0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
			 0.5f,  0.5f, -0.5f, 1.0f, 1.0f,
			-0.5f,  0.5f, -0.5f, 0.0f, 1.0f,
			-0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
		};
		float[] vertices = {
				0, 0, 2, 0, 0,
				1, 0, 2, 1, 0,
				1, 1, 2, 1, 1,
				0, 1, 2, 0, 1
		};
//		float[] vertices = {
//					-1.0f, -1.0f, 0.0f, 0.0f,
//					 1.0f, -1.0f, 4.0f, 0.0f,
//					 1.0f,  1.0f, 4.0f, 3.0f,
//					-1.0f,  1.0f, 0.0f, 3.0f
//		};
		
		short[] indices = {
			0, 1, 3,
			1, 2, 3
		};
		
		Mesh mesh = loadShape("test/models/lamborghini/lambo.obj");
		image = loadImage("test/models/lamborghini/lambo_diffuse.jpeg");
		
		
		vbo = graphics.createVBO(STATIC_DRAW);
		vbo.bind();
		//vbo.insert(cube_vert, 0);
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
		
		
		ibo = graphics.createIBO(STATIC_DRAW);
		ibo.bind();
		//ibo.insert(indices, 0);
		ibo.set(mesh.indices);
		
		vao = graphics.createVAO();
		
		VertexAttribute attribs = new VertexAttribute();
		attribs.add(0, 3, false, 5, 0);
		attribs.add(1, 2, false, 5, 3);
		
		vao.put(vbo, attribs);

		shader = graphics.loadShader("test/shaders/basic_fragment.glsl",
									 "test/shaders/basic_vertex.glsl");
		shader.setup();
		shader.enable();
		GL30 gl = new Lwjgl3GL30();

		gl.activeTexture(0);
	
		shader.set("sampler", 0);

		tex = new GLTexture2D(gl, false, false);
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
		camera2D.setOrtho(0, getWidth(), getHeight(), 0);

		//shader.set("projection", perspective);
		graphics.viewport(0, 0, getWidth(), getHeight());
		
		//APPLY TRANSFORMATION
		shader.set("transform", t.getMapping());
		
		add(camera3D);
	}

	@Override
	public void draw() {
		graphics.viewport(0, 0, getWidth(), getHeight());
		graphics.clear(GL20.COLOR_BUFFER_BIT | GL20.DEPTH_BUFFER_BIT);
		
		//t.rotateY(0.01f);
		shader.set("transform", t.getMapping());
		shader.set("projection", camera3D.getMapping());
		//shader.set("projection", new Matrix4f().perspective);
		
		graphics.background(0.0f, 0.5f, 1.0f, 1.0f);
		shader.enable();
		tex.bind();
		graphics.render(TRIANGLES, vao);

	}
	
	public static void main(String[] args) {
		TestApplication display = new TestApplication("Test Application", 1280, 720);
		display.setRenderer(OPENGL_DEBUG); 
		display.setVisible(true);
//		TestApplication display2 = new TestApplication("Test Application 2", 1280, 720);
//		display2.setRenderer(OPENGL_DEBUG); 
//		display2.setVisible(true);
	}
	
	@Override
	public void keyDown(int key) {
		System.out.println("Pressed key " + key);
	}
	
	@Override
	public void mousePressed(int button) {
		System.out.println("What's up button # " + button);
	}
	
	@Override
	public void mouseEntered() {
		System.out.println("Mouse entered");
	}
	
	@Override
	public void mouseExited() {
		System.out.println("Mouse exited");
	}
	
	@Override
	public void windowClosed() {
		System.out.println("The window is now closed!");
	}
	
	@Override
	public void windowResized(int width, int height) {
		
	}
}
