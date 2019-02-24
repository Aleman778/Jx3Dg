package jx3d.graphics.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Interface wrapper for some of the OpenGL 1.x - 2.x functions,
 * based on OpenGLES 2 (with some added functions).
 *
 * @author Aleman778
 * @since 1.0
 */
public interface GL20 extends GLConstants {

    void activeTexture(int texture);

    void attachShader(int program, int shader);

    void bindAttribLocation(int program, int index, String name);

    void bindBuffer(int target, int buffer);

    void bindFramebuffer(int target, int framebuffer);

    void bindRenderbuffer(int target, int renderbuffer);

    void bindTexture(int target, int texture);

    void blendColor(float red, float green, float blue, float alpha);

    void blendEquation(int mode);

    void blendEquationSeparate(int modeRGB, int modeAlpha);

    void blendFunc(int sfactor, int dfactor);

    void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha);

    void bufferData(int target, int size, Buffer data, int usage);

    void bufferSubData(int target, int offset, int size, Buffer data);

    int checkFramebufferStatus(int target);

    void clear(int mask);

    void clearColor(float red, float green, float blue, float alpha);

    void clearDepth(float depth);

    void clearStencil(int s);

    void colorMask(boolean red, boolean green, boolean blue, boolean alpha);

    void compileShader(int shader);

    void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
                             int imageSize, Buffer data);

    void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
                                int format, int imageSize, Buffer data);

    void copyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
                       int border);

    void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);

    int createProgram();

    int createShader(int type);

    void cullFace(int mode);

    void deleteBuffer(int buffer);

    void deleteBuffers(int n, IntBuffer buffers);

    void deleteFramebuffer(int framebuffer);

    void deleteFramebuffers(int n, IntBuffer framebuffers);

    void deleteProgram(int program);

    void deleteRenderbuffer(int renderbuffer);

    void deleteRenderbuffers(int n, IntBuffer renderbuffers);

    void deleteShader(int shader);

    void deleteTexture(int texture);

    void deleteTextures(int n, IntBuffer textures);

    void depthFunc(int func);

    void depthMask(boolean flag);

    void depthRangef(float zNear, float zFar);

    void detachShader(int program, int shader);

    void disable(int cap);

    void disableVertexAttribArray(int index);

    void drawArrays(int mode, int first, int count);

    void drawElements(int mode, int count, int type, Buffer indices);

    void drawElements(int mode, int count, int type, int indices);

    void enable(int cap);

    void enableVertexAttribArray(int index);

    void finish();

    void flush();

    void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer);

    void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level);

    void frontFace(int mode);

    int genBuffer();

    void genBuffers(int n, IntBuffer buffers);

    void generateMipmap(int target);

    int genFramebuffer();

    void genFramebuffers(int n, IntBuffer framebuffers);

    int genRenderbuffer();

    void genRenderbuffers(int n, IntBuffer renderbuffers);

    int genTexture();

    void genTextures(int n, IntBuffer textures);

    String getActiveAttrib(int program, int index, IntBuffer size, Buffer type);

    String getActiveUniform(int program, int index, IntBuffer size, Buffer type);

    void getAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders);

    int getAttribLocation(int program, String name);

    void getBooleanv(int pname, Buffer params);

    void getBufferParameteriv(int target, int pname, IntBuffer params);

    int getError();

    float getFloat(int pname);

    void getFloatv(int pname, FloatBuffer params);

    void getFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params);

    int getInteger(int pname);

    void getIntegerv(int pname, IntBuffer params);

    String getProgramInfoLog(int program);

    int getProgrami(int program, int pname);

    void getProgramiv(int program, int pname, IntBuffer params);

    void getRenderbufferParameteriv(int target, int pname, IntBuffer params);

    String getShaderInfoLog(int shader);

    int getShaderi(int shader, int pname);

    void getShaderiv(int shader, int pname, IntBuffer params);

    void getShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision);

    String getString(int name);

    void getTexParameterfv(int target, int pname, FloatBuffer params);

    void getTexParameteriv(int target, int pname, IntBuffer params);

    void getUniformfv(int program, int location, FloatBuffer params);

    void getUniformiv(int program, int location, IntBuffer params);

    int getUniformLocation(int program, String name);

    void getVertexAttribfv(int index, int pname, FloatBuffer params);

    void getVertexAttribiv(int index, int pname, IntBuffer params);

    void getVertexAttribPointerv(int index, int pname, Buffer pointer);

    void hint(int target, int mode);

    boolean isBuffer(int buffer);

    boolean isEnabled(int cap);

    boolean isFramebuffer(int framebuffer);

    boolean isProgram(int program);

    boolean isRenderbuffer(int renderbuffer);

    boolean isShader(int shader);

    boolean isTexture(int texture);

    void lineWidth(float width);

    void linkProgram(int program);

    ByteBuffer mapBuffer(int target, int access);

    ByteBuffer mapBuffer(int target, int access, long size, ByteBuffer buffer);

    void pixelStorei(int pname, int param);

    void polygonOffset(float factor, float units);

    void readPixels(int x, int y, int width, int height, int format, int type, Buffer pixels);

    void releaseShaderCompiler();

    void renderbufferStorage(int target, int internalformat, int width, int height);

    void sampleCoverage(float value, boolean invert);

    void scissor(int x, int y, int width, int height);

    void shaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length);

    void shaderSource(int shader, String string);

    void stencilFunc(int func, int ref, int mask);

    void stencilFuncSeparate(int face, int func, int ref, int mask);

    void stencilMask(int mask);

    void stencilMaskSeparate(int face, int mask);

    void stencilOp(int fail, int zfail, int zpass);

    void stencilOpSeparate(int face, int fail, int zfail, int zpass);

    void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
                   int type, Buffer pixels);

    void texParameterf(int target, int pname, float param);

    void texParameterfv(int target, int pname, FloatBuffer params);

    void texParameteri(int target, int pname, int param);

    void texParameteriv(int target, int pname, IntBuffer params);

    void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
                      int type, Buffer pixels);

    void uniform1f(int location, float x);

    void uniform1fv(int location, int count, float v[], int offset);

    void uniform1fv(int location, int count, FloatBuffer v);

    void uniform1i(int location, int x);

    void uniform1iv(int location, int count, int v[], int offset);

    void uniform1iv(int location, int count, IntBuffer v);

    void uniform2f(int location, float x, float y);

    void uniform2fv(int location, int count, float v[], int offset);

    void uniform2fv(int location, int count, FloatBuffer v);

    void uniform2i(int location, int x, int y);

    void uniform2iv(int location, int count, int[] v, int offset);

    void uniform2iv(int location, int count, IntBuffer v);

    void uniform3f(int location, float x, float y, float z);

    void uniform3fv(int location, int count, float[] v, int offset);

    void uniform3fv(int location, int count, FloatBuffer v);

    void uniform3i(int location, int x, int y, int z);

    void uniform3iv(int location, int count, int v[], int offset);

    void uniform3iv(int location, int count, IntBuffer v);

    void uniform4f(int location, float x, float y, float z, float w);

    void uniform4fv(int location, int count, float v[], int offset);

    void uniform4fv(int location, int count, FloatBuffer v);

    void uniform4i(int location, int x, int y, int z, int w);

    void uniform4iv(int location, int count, int v[], int offset);

    void uniform4iv(int location, int count, IntBuffer v);

    void uniformMatrix2fv(int location, int count, boolean transpose, float value[], int offset);

    void uniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix3fv(int location, int count, boolean transpose, float value[], int offset);

    void uniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix4fv(int location, int count, boolean transpose, float value[], int offset);

    void uniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value);

    boolean unmapBuffer(int target);

    void useProgram(int program);

    void validateProgram(int program);

    void vertexAttrib1f(int indx, float x);

    void vertexAttrib1fv(int indx, FloatBuffer values);

    void vertexAttrib2f(int indx, float x, float y);

    void vertexAttrib2fv(int indx, FloatBuffer values);

    void vertexAttrib3f(int indx, float x, float y, float z);

    void vertexAttrib3fv(int indx, FloatBuffer values);

    void vertexAttrib4f(int indx, float x, float y, float z, float w);

    void vertexAttrib4fv(int indx, FloatBuffer values);

    void vertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr);

    void vertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr);

    void viewport(int x, int y, int width, int height);
}
