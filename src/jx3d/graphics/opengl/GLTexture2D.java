package jx3d.graphics.opengl;

import java.nio.IntBuffer;

import jx3d.core.Module;
import jx3d.graphics.Image;
import jx3d.graphics.Texture2D;
import jx3d.util.BufferUtils;

public class GLTexture2D extends Texture2D {
	
	private static final int BASE_LOD = 0;
	
	private GL30 gl;
	private Image image;
	private int object;

	public GLTexture2D(GL30 gl, boolean mipmaps, boolean anisotropic) {
		super(mipmaps, anisotropic);
		this.gl = gl;
		this.object = gl.genTexture();
	}

	@Override
	public void bind() {
		gl.bindTexture(GL20.TEXTURE_2D, object);
	}

	@Override
	public void unbind() {
		gl.bindTexture(GL20.TEXTURE_2D, 0);
	}

	@Override
	public void dispose() {
		gl.deleteTexture(object);
		object = -1;
	}
	
	@Override
	public void image(Image image) {
		imageImpl(image, BASE_LOD);
	}

	@Override
	public void subImage(Image image, int x, int y, int w, int h) {
		subImageImpl(image, BASE_LOD, x, y, w, h);
	}
	
	@Override
	public void mipmap(Image image, int level) {
		if (!mipmapping)
			throw new IllegalStateException("Mipmapping is not enabled.");
		imageImpl(image, level);
	}

	@Override
	public void subMipmap(Image image, int level, int x, int y, int w, int h) {
		if (!mipmapping)
			throw new IllegalStateException("Mipmapping is not enabled.");
		subImageImpl(image, level, x, y, w, h);
	}

	private void imageImpl(Image image, int level) {
		bind();
		this.image = image;
		IntBuffer buf = BufferUtils.createIntBuffer(image.getPixels());
		gl.texImage2D(GL20.TEXTURE_2D, level, GL20.RGBA8, image.getWidth(),
				image.getHeight(), 0, GL20.RGBA, GL20.UNSIGNED_BYTE, buf);
	}
	
	private void subImageImpl(Image image, int level, int x, int y, int w, int h) {
		bind();
		this.image = image;
		IntBuffer buf = BufferUtils.createIntBuffer(image.getPixels());
		gl.texSubImage2D(GL20.TEXTURE_2D, BASE_LOD, x, y, w, h,
				GL20.RGBA, GL20.UNSIGNED_BYTE, buf);
	}
	
	@Override
	public void generateMipmaps() {
		if (image == null)
			throw new IllegalStateException("There is no source image, cannot generate mipmaps.");
		
		bind();
		mipmapping = true;
		int size = Math.max(getWidth(), getHeight());
		int levels = (int) (Math.log(size) / Math.log(2));
		setNumLODLevels(levels);
		setLODRange(-levels, levels);
		gl.generateMipmap(GL20.TEXTURE_2D);
	}

	@Override
	public void setLODBias(float bias) {
		bind();
		gl.texParameterf(GL20.TEXTURE_2D, GL20.TEXTURE_LOD_BIAS, bias);
	}

	@Override
	public void setLODRange(int min, int max) {
		bind();
		gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_MIN_LOD, min);
		gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_MAX_LOD, max);
	}

	@Override
	public void setNumLODLevels(int levels) {
		bind();
		gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_MAX_LEVEL, levels);
	}

	@Override
	public void setMinFilter(int filter) {
		bind();
		gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_MIN_FILTER, filter);
	}

	@Override
	public void setMagFilter(int filter) {
		bind();
		gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_MAG_FILTER, filter);
	}

	@Override
	public void setMaxAnisotropy(float amount) {
		bind();
		throw new IllegalStateException();
		//gl.texParameterf(GL20.TEXTURE_2D, GL20.TEXTURE_LOD_BIAS, amount);
	}
	
	@Override
	public void setWrapMode(int wrap, int axis) {
		bind();	
		int glwrap = GLGraphics.glGetTextureWrapMode(wrap);
		if ((axis & Module.S) == Module.S)
			gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_WRAP_S, glwrap);
		if ((axis & Module.T) == Module.T)
			gl.texParameteri(GL20.TEXTURE_2D, GL20.TEXTURE_WRAP_T, glwrap);
	}

	@Override
	public void setSample(int sample) {
		switch (sample) {
		case Module.POINT:
			setMinFilter(mipmapping ? GL20.NEAREST_MIPMAP_NEAREST : GL20.NEAREST);
			setMagFilter(GL20.NEAREST);
			break;
		case Module.LINEAR:
			setMinFilter(mipmapping ? GL20.LINEAR_MIPMAP_NEAREST : GL20.LINEAR);
			setMagFilter(GL20.NEAREST);
			break;
		case Module.BILINEAR:
			setMinFilter(mipmapping ? GL20.LINEAR_MIPMAP_NEAREST : GL20.LINEAR);
			setMagFilter(GL20.LINEAR);
			break;
		case Module.TRILINEAR:
			setMinFilter(mipmapping ? GL20.LINEAR_MIPMAP_LINEAR : GL20.LINEAR);
			setMagFilter(GL20.LINEAR);
			break;
		default:
			throw new IllegalArgumentException("Invalid sample option, expected POINT, LINEAR, BILINEAR or TRILINEAR");
		}
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}
}
