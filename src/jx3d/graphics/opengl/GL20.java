package jx3d.graphics.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Interface wrapper for some of the OpenGL 1.x - 2.x functions,
 * based on OpenGLES 2 (with some added functions).
 * @since 1.0
 * @author Aleman778
 */
public interface GL20 extends GLConstants {

	public void activeTexture(int texture);

	public void attachShader(int program, int shader);

	public void bindAttribLocation(int program, int index, String name);

	public void bindBuffer(int target, int buffer);

	public void bindFramebuffer(int target, int framebuffer);

	public void bindRenderbuffer(int target, int renderbuffer);

	public void bindTexture(int target, int texture);

	public void blendColor(float red, float green, float blue, float alpha);

	public void blendEquation(int mode);

	public void blendEquationSeparate(int modeRGB, int modeAlpha);

	public void blendFunc(int sfactor, int dfactor);

	public void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha);

	public void bufferData(int target, int size, Buffer data, int usage);

	public void bufferSubData(int target, int offset, int size, Buffer data);

	public int checkFramebufferStatus(int target);

	public void clear(int mask);

	public void clearColor(float red, float green, float blue, float alpha);

	public void clearDepth(float depth);

	public void clearStencil(int s);

	public void colorMask(boolean red, boolean green, boolean blue, boolean alpha);

	public void compileShader(int shader);

	public void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
			int imageSize, Buffer data);

	public void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
			int format, int imageSize, Buffer data);

	public void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
			int border);

	public void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);

	public int createProgram();

	public int createShader(int type);

	public void cullFace(int mode);

	public void deleteBuffer(int buffer);

	public void deleteBuffers(int n, IntBuffer buffers);

	public void deleteFramebuffer(int framebuffer);

	public void deleteFramebuffers(int n, IntBuffer framebuffers);

	public void deleteProgram(int program);

	public void deleteRenderbuffer(int renderbuffer);

	public void deleteRenderbuffers(int n, IntBuffer renderbuffers);

	public void deleteShader(int shader);

	public void deleteTexture(int texture);

	public void deleteTextures(int n, IntBuffer textures);

	public void depthFunc(int func);

	public void depthMask(boolean flag);

	public void depthRangef(float zNear, float zFar);

	public void detachShader(int program, int shader);

	public void disable(int cap);

	public void disableVertexAttribArray(int index);

	public void drawArrays(int mode, int first, int count);

	public void drawElements(int mode, int count, int type, Buffer indices);

	public void drawElements(int mode, int count, int type, int indices);

	public void enable(int cap);

	public void enableVertexAttribArray(int index);

	public void finish();

	public void flush();

	public void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer);

	public void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level);

	public void frontFace(int mode);

	public int genBuffer();

	public void genBuffers(int n, IntBuffer buffers);

	public void generateMipmap(int target);

	public int genFramebuffer();

	public void genFramebuffers(int n, IntBuffer framebuffers);

	public int genRenderbuffer();

	public void genRenderbuffers(int n, IntBuffer renderbuffers);

	public int genTexture();

	public void genTextures(int n, IntBuffer textures);

	public String getActiveAttrib(int program, int index, IntBuffer size, Buffer type);

	public String getActiveUniform(int program, int index, IntBuffer size, Buffer type);

	public void getAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders);

	public int getAttribLocation(int program, String name);

	public void getBooleanv(int pname, Buffer params);

	public void getBufferParameteriv(int target, int pname, IntBuffer params);

	public int getError();

	public void getFloatv(int pname, FloatBuffer params);

	public void getFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params);

	public void getIntegerv(int pname, IntBuffer params);

	public String getProgramInfoLog(int program);

	public void getProgramiv(int program, int pname, IntBuffer params);

	public void getRenderbufferParameteriv(int target, int pname, IntBuffer params);

	public String getShaderInfoLog(int shader);

	public void getShaderiv(int shader, int pname, IntBuffer params);

	public void getShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision);

	public String getString(int name);

	public void getTexParameterfv(int target, int pname, FloatBuffer params);

	public void getTexParameteriv(int target, int pname, IntBuffer params);

	public void getUniformfv(int program, int location, FloatBuffer params);

	public void getUniformiv(int program, int location, IntBuffer params);

	public int getUniformLocation(int program, String name);

	public void getVertexAttribfv(int index, int pname, FloatBuffer params);

	public void getVertexAttribiv(int index, int pname, IntBuffer params);

	public void getVertexAttribPointerv(int index, int pname, Buffer pointer);

	public void hint(int target, int mode);

	public boolean isBuffer(int buffer);

	public boolean isEnabled(int cap);

	public boolean isFramebuffer(int framebuffer);

	public boolean isProgram(int program);

	public boolean isRenderbuffer(int renderbuffer);

	public boolean isShader(int shader);

	public boolean isTexture(int texture);

	public void lineWidth(float width);

	public void linkProgram(int program);

	public ByteBuffer mapBuffer(int target, int access);

	public ByteBuffer mapBuffer(int target, int access, long size, ByteBuffer buffer);

	public void pixelStorei(int pname, int param);

	public void polygonOffset(float factor, float units);

	public void readPixels(int x, int y, int width, int height, int format, int type, Buffer pixels);

	public void releaseShaderCompiler();

	public void renderbufferStorage(int target, int internalformat, int width, int height);

	public void sampleCoverage(float value, boolean invert);

	public void scissor(int x, int y, int width, int height);

	public void shaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length);

	public void shaderSource(int shader, String string);

	public void stencilFunc(int func, int ref, int mask);

	public void stencilFuncSeparate(int face, int func, int ref, int mask);

	public void stencilMask(int mask);

	public void stencilMaskSeparate(int face, int mask);

	public void stencilOp(int fail, int zfail, int zpass);

	public void stencilOpSeparate(int face, int fail, int zfail, int zpass);

	public void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
			int type, Buffer pixels);

	public void texParameterf(int target, int pname, float param);

	public void texParameterfv(int target, int pname, FloatBuffer params);

	public void texParameteri(int target, int pname, int param);

	public void texParameteriv(int target, int pname, IntBuffer params);

	public void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
			int type, Buffer pixels);

	public void uniform1f(int location, float x);

	public void uniform1fv(int location, int count, float v[], int offset);

	public void uniform1fv(int location, int count, FloatBuffer v);

	public void uniform1i(int location, int x);

	public void uniform1iv(int location, int count, int v[], int offset);

	public void uniform1iv(int location, int count, IntBuffer v);

	public void uniform2f(int location, float x, float y);

	public void uniform2fv(int location, int count, float v[], int offset);

	public void uniform2fv(int location, int count, FloatBuffer v);

	public void uniform2i(int location, int x, int y);

	public void uniform2iv(int location, int count, int[] v, int offset);

	public void uniform2iv(int location, int count, IntBuffer v);

	public void uniform3f(int location, float x, float y, float z);

	public void uniform3fv(int location, int count, float[] v, int offset);

	public void uniform3fv(int location, int count, FloatBuffer v);

	public void uniform3i(int location, int x, int y, int z);

	public void uniform3iv(int location, int count, int v[], int offset);

	public void uniform3iv(int location, int count, IntBuffer v);

	public void uniform4f(int location, float x, float y, float z, float w);

	public void uniform4fv(int location, int count, float v[], int offset);

	public void uniform4fv(int location, int count, FloatBuffer v);

	public void uniform4i(int location, int x, int y, int z, int w);

	public void uniform4iv(int location, int count, int v[], int offset);

	public void uniform4iv(int location, int count, IntBuffer v);

	public void uniformMatrix2fv(int location, int count, boolean transpose, float value[], int offset);

	public void uniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix3fv(int location, int count, boolean transpose, float value[], int offset);

	public void uniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix4fv(int location, int count, boolean transpose, float value[], int offset);

	public void uniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value);

	public boolean unmapBuffer(int target);

	public void useProgram(int program);

	public void validateProgram(int program);

	public void vertexAttrib1f(int indx, float x);

	public void vertexAttrib1fv(int indx, FloatBuffer values);

	public void vertexAttrib2f(int indx, float x, float y);

	public void vertexAttrib2fv(int indx, FloatBuffer values);

	public void vertexAttrib3f(int indx, float x, float y, float z);

	public void vertexAttrib3fv(int indx, FloatBuffer values);

	public void vertexAttrib4f(int indx, float x, float y, float z, float w);

	public void vertexAttrib4fv(int indx, FloatBuffer values);

	public void vertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr);

	public void vertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr);

	public void viewport(int x, int y, int width, int height);
}
