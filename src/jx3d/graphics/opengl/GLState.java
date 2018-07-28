package jx3d.graphics.opengl;

/**
 * Collection of variables holding an OpenGL state.
 * @since 1.0
 * @author Aleman778
 */
public class GLState {

	//Max number of texture units available
	private static final int MAX_TEXTURE_UNITS = 32;
	
	//Buffer states
	public int arrayBuffer = 0;
	public int elementArrayBuffer = 0;
	public int vertexArray = 0;
	
	//Shader states
	public int shader = 0;
	
	//Texture states
	public int activeTexture = 0;
	public GLTexUnit[] texUnits;
	
	/**
	 * Constructor. Initializes some data.<br>
	 * <i>Note:</i> when creating a OpenGL state there must be an
	 * OpenGL context running on the current thread.
	 */
	public GLState() {
		texUnits = new GLTexUnit[MAX_TEXTURE_UNITS];
		for (int i = 0; i < texUnits.length; i++) {
			texUnits[i] = new GLTexUnit();
		}
	}
	
	/**
	 * Check and set the state of the buffer.
	 * @param target the type of buffer
	 * @param buffer the buffer
	 * @return false if the buffer is already bound
	 */
	public boolean buffer(int target, int buffer) {
		int oldBuffer = -1;
		switch (target) {
		case GL20.ARRAY_BUFFER:
			oldBuffer = arrayBuffer;
			arrayBuffer = buffer;
			break;
		case GL20.ELEMENT_ARRAY_BUFFER:
			oldBuffer = elementArrayBuffer;
			elementArrayBuffer = buffer;
			break;
		}
		System.out.println(oldBuffer + " == " + buffer);
		return !(oldBuffer == buffer);
	}
	
	/**
	 * Check and set the state of the vertex array.
	 * @param array the vertex array
	 * @return false if the array is already bound
	 */
	public boolean vertexArray(int array) {
		if (vertexArray == array)
			return false;
		vertexArray = array;
		return true;
	}
	
	public boolean texture(int target, int texture) {
		int oldTexture = -1;
		switch (target) {
		case GL20.TEXTURE_2D:
			oldTexture = getTexture2d();
			setTexture2d(texture);
			break;
		}
		return !(oldTexture == texture);
	}
	
	/**
	 * Select a texture unit.
	 * @param unit texture unit to select
	 */
	public void setActiveTexture(int unit) {
		activeTexture = unit;
	}
	
	/**
	 * Set the texture id into specific texture unit
	 * @param texture texture identification
	 */
	public void setTexture1d(int texture) {
		texUnits[activeTexture].texture1d = texture;
	}
	
	/**
	 * Get texture id of the specific texture unit
	 * @return the texture identification, 0 if null
	 */
	public int getTexture1d() {
		return texUnits[activeTexture].texture1d;
	}

	/**
	 * Set the texture id into specific texture unit
	 * @param texture texture identification
	 */
	public void setTexture2d(int texture) {
		texUnits[activeTexture].texture2d = texture;
	}

	/**
	 * Get texture id of the specific texture unit
	 * @return the texture identification, 0 if null
	 */
	public int getTexture2d() {
		return texUnits[activeTexture].texture2d;
	}

	/**
	 * Set the texture id into specific texture unit
	 * @param texture texture identification
	 */
	public void setTexture2dMultisample(int texture) {
		texUnits[activeTexture].texture2dMultisample = texture;
	}

	/**
	 * Get texture id of the specific texture unit
	 * @return the texture identification, 0 if null
	 */
	public int getTexture2dMultisample() {
		return texUnits[activeTexture].texture2dMultisample;
	}

	/**
	 * Set the texture id into specific texture unit
	 * @param texture texture identification
	 */
	public void setTexture3d(int texture) {
		texUnits[activeTexture].texture3d = texture;
	}

	/**
	 * Get texture id of the specific texture unit
	 * @return the texture identification, 0 if null
	 */
	public int getTexture3d() {
		return texUnits[activeTexture].texture3d;
	}

	/**
	 * Set the texture id into specific texture unit
	 * @param texture texture identification
	 */
	public void setTextureCubeMap(int texture) {
		texUnits[activeTexture].textureCubeMap = texture;
	}

	/**
	 * Get texture id of the specific texture unit
	 * @return the texture identification, 0 if null
	 */
	public int getTextureCubeMap() {
		return texUnits[activeTexture].textureCubeMap;
	}

	/**
	 * OpenGL texture unit data structure.
	 * @since 1.0
	 * @author Aleman778
	 */
	class GLTexUnit {
		public int texture1d            = 0;
		public int texture2d            = 0;
		public int texture2dMultisample = 0;
		public int texture3d            = 0;
		public int textureCubeMap       = 0;
	}
}
