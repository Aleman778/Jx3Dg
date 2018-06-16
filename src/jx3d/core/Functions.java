package jx3d.core;

import jx3d.graphics.Shader;
/**
 * Shared functions in the jx3DGraphics API.
 * @since 1.0
 * @author Aleman778
 */
public class Functions extends Constants {
	
	//Rendering functions	
	public void viewport(int x, int y, int w, int h) {
		graphics.viewport(x, y, w, h);
	}
           
	public Shader loadShader(String fragment) {
		return graphics.loadShader(fragment);
	}
	       
	public Shader loadShader(String fragment, String vertex) {
		return graphics.loadShader(fragment, vertex);
	}
	       
	public Shader loadShader(int shader) {
		return graphics.loadShader(shader);
	}
	
	//File I/O
	/**
	 * {@inheritDoc Files#open(int, String)}
	 */
	public boolean open(int access, String file) {
		return files.open(access, file);
	}
	
	public void close() {
		files.close();
	}
	
	public String read() {
		return files.read();
	}
	
	public String readln() {
		return files.readln();
	}
	
	public boolean write(String data) {
		return files.write(data);
	}
}
