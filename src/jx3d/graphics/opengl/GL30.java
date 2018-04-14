package jx3d.graphics.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Interface wrapper for some of the OpenGL 3.x - 4.x functions,
 * based on OpenGLES 2 (with some added functions).
 * @since 1.0
 * @author Aleman778
 */
public interface GL30 extends GL20 {

	public void beginQuery(int target, int id);

	public void beginTransformFeedback(int primitiveMode);

	public void bindBufferBase(int target, int index, int buffer);

	public void bindBufferRange(int target, int index, int buffer, int offset, int size);

	public void bindSampler(int unit, int sampler);

	public void bindTransformFeedback(int target, int id);

	public void bindVertexArray(int array);

	public void blitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1,
			int dstY1, int mask, int filter);

	public void clearBufferfi(int buffer, int drawbuffer, float depth, int stencil);

	public void clearBufferfv(int buffer, int drawbuffer, FloatBuffer value);

	public void clearBufferiv(int buffer, int drawbuffer, IntBuffer value);

	public void clearBufferuiv(int buffer, int drawbuffer, IntBuffer value);

	public void copyBufferSubData(int readTarget, int writeTarget, int readOffset, int writeOffset, int size);

	public void copyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y,
			int width, int height);

	public void deleteQueries(int n, int[] ids, int offset);

	public void deleteQueries(int n, IntBuffer ids);

	public void deleteSamplers(int count, int[] samplers, int offset);

	public void deleteSamplers(int count, IntBuffer samplers);

	public void deleteTransformFeedbacks(int n, int[] ids, int offset);

	public void deleteTransformFeedbacks(int n, IntBuffer ids);

	public void deleteVertexArray(int array);

	public void deleteVertexArrays(int n, int[] arrays, int offset);

	public void deleteVertexArrays(int n, IntBuffer arrays);

	public void drawArraysInstanced(int mode, int first, int count, int instanceCount);

	public void drawBuffers(int n, IntBuffer bufs);

	public void drawElementsInstanced(int mode, int count, int type, int indicesOffset, int instanceCount);

	public void drawRangeElements(int mode, int start, int end, int count, int type, Buffer indices);

	public void drawRangeElements(int mode, int start, int end, int count, int type, int offset);

	public void endQuery(int target);

	public void endTransformFeedback();

	public void flushMappedBufferRange(int target, int offset, int length);
	
	public void framebufferTextureLayer(int target, int attachment, int texture, int level, int layer);

	public void genQueries(int n, int[] ids, int offset);

	public void genQueries(int n, IntBuffer ids);
	
	public void genSamplers(int count, int[] samplers, int offset);

	public void genSamplers(int count, IntBuffer samplers);

	public void genTransformFeedbacks(int n, int[] ids, int offset);

	public void genTransformFeedbacks(int n, IntBuffer ids);

	public int genVertexArray();

	public void genVertexArrays(int n, int[] arrays, int offset);

	public void genVertexArrays(int n, IntBuffer arrays);

	public void getActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, IntBuffer params);

	public String getActiveUniformBlockName(int program, int uniformBlockIndex);

	public void getActiveUniformBlockName(int program, int uniformBlockIndex, Buffer length,
			Buffer uniformBlockName);

	public void getActiveUniformsiv(int program, int uniformCount, IntBuffer uniformIndices, int pname,
			IntBuffer params);

	public void getBufferParameteri64v(int target, int pname, LongBuffer params);

	public Buffer getBufferPointerv(int target, int pname);

	public int getFragDataLocation(int program, String name);

	public void getInteger64v(int pname, LongBuffer params);

	public void getQueryiv(int target, int pname, IntBuffer params);

	public void getQueryObjectuiv(int id, int pname, IntBuffer params);

	public void getSamplerParameterfv(int sampler, int pname, FloatBuffer params);

	public void getSamplerParameteriv(int sampler, int pname, IntBuffer params);

	public String getStringi(int name, int index);

	public int getUniformBlockIndex(int program, String uniformBlockName);

	public void getUniformIndices(int program, String[] uniformNames, IntBuffer uniformIndices);

	public void getUniformuiv(int program, int location, IntBuffer params);

	public void getVertexAttribIiv(int index, int pname, IntBuffer params);

	public void getVertexAttribIuiv(int index, int pname, IntBuffer params);

	public void invalidateFramebuffer(int target, int numAttachments, IntBuffer attachments);

	public void invalidateSubFramebuffer(int target, int numAttachments, IntBuffer attachments, int x, int y,
			int width, int height);

	public boolean isQuery(int id);

	public boolean isSampler(int sampler);

	public boolean isTransformFeedback(int id);

	public boolean isVertexArray(int array);

	public void pauseTransformFeedback();

	public void programParameteri(int program, int pname, int value);

	public void readBuffer(int mode);

	public void renderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height);

	public void resumeTransformFeedback();

	public void samplerParameterf(int sampler, int pname, float param);

	public void samplerParameterfv(int sampler, int pname, FloatBuffer param);

	public void samplerParameteri(int sampler, int pname, int param);

	public void samplerParameteriv(int sampler, int pname, IntBuffer param);

	public void texImage3D(int target, int level, int internalformat, int width, int height, int depth, int border,
			int format, int type, Buffer pixels);

	public void texImage3D(int target, int level, int internalformat, int width, int height, int depth, int border,
			int format, int type, int offset);

	public void texSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int type, Buffer pixels);

	public void texSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int type, int offset);

	public void transformFeedbackVaryings(int program, String[] varyings, int bufferMode);

	public void uniform1uiv(int location, int count, IntBuffer value);

	public void uniform3uiv(int location, int count, IntBuffer value);

	public void uniform4uiv(int location, int count, IntBuffer value);

	public void uniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding);

	public void uniformMatrix2x3fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix2x4fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix3x2fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix3x4fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix4x2fv(int location, int count, boolean transpose, FloatBuffer value);

	public void uniformMatrix4x3fv(int location, int count, boolean transpose, FloatBuffer value);

	public void vertexAttribDivisor(int index, int divisor);

	public void vertexAttribI4i(int index, int x, int y, int z, int w);

	public void vertexAttribI4ui(int index, int x, int y, int z, int w);

	public void vertexAttribIPointer(int index, int size, int type, int stride, int offset);
}
