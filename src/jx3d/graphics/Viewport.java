package jx3d.graphics;

public class Viewport {

	public float x;
	
	public float y;
	
	public float width;
	
	public float height;
	
	public Viewport() {
		this(0, 0, 1, 1);
	}
	
	public Viewport(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
