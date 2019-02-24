package jx3d.graphics.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Interface wrapper for some of the OpenGL 3.x - 4.x functions,
 * based on OpenGLES 2 (with some added functions).
 *
 * @author Aleman778
 * @since 1.0
 */
public interface GL30 extends GL20 {

    void beginQuery(int target, int id);

    void beginTransformFeedback(int primitiveMode);

    void bindBufferBase(int target, int index, int buffer);

    void bindBufferRange(int target, int index, int buffer, int offset, int size);

    void bindSampler(int unit, int sampler);

    void bindTransformFeedback(int target, int id);

    void bindVertexArray(int array);

    void blitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1,
                        int dstY1, int mask, int filter);

    void clearBufferfi(int buffer, int drawbuffer, float depth, int stencil);

    void clearBufferfv(int buffer, int drawbuffer, FloatBuffer value);

    void clearBufferiv(int buffer, int drawbuffer, IntBuffer value);

    void clearBufferuiv(int buffer, int drawbuffer, IntBuffer value);

    void copyBufferSubData(int readTarget, int writeTarget, int readOffset, int writeOffset, int size);

    void copyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y,
                          int width, int height);

    void deleteQueries(int n, int[] ids, int offset);

    void deleteQueries(int n, IntBuffer ids);

    void deleteSamplers(int count, int[] samplers, int offset);

    void deleteSamplers(int count, IntBuffer samplers);

    void deleteTransformFeedbacks(int n, int[] ids, int offset);

    void deleteTransformFeedbacks(int n, IntBuffer ids);

    void deleteVertexArray(int array);

    void deleteVertexArrays(int n, int[] arrays, int offset);

    void deleteVertexArrays(int n, IntBuffer arrays);

    void drawArraysInstanced(int mode, int first, int count, int instanceCount);

    void drawBuffers(int n, IntBuffer bufs);

    void drawElementsInstanced(int mode, int count, int type, int indicesOffset, int instanceCount);

    void drawRangeElements(int mode, int start, int end, int count, int type, Buffer indices);

    void drawRangeElements(int mode, int start, int end, int count, int type, int offset);

    void endQuery(int target);

    void endTransformFeedback();

    void flushMappedBufferRange(int target, int offset, int length);

    void framebufferTextureLayer(int target, int attachment, int texture, int level, int layer);

    void genQueries(int n, int[] ids, int offset);

    void genQueries(int n, IntBuffer ids);

    void genSamplers(int count, int[] samplers, int offset);

    void genSamplers(int count, IntBuffer samplers);

    void genTransformFeedbacks(int n, int[] ids, int offset);

    void genTransformFeedbacks(int n, IntBuffer ids);

    int genVertexArray();

    void genVertexArrays(int n, int[] arrays, int offset);

    void genVertexArrays(int n, IntBuffer arrays);

    void getActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, IntBuffer params);

    String getActiveUniformBlockName(int program, int uniformBlockIndex);

    void getActiveUniformBlockName(int program, int uniformBlockIndex, Buffer length,
                                  Buffer uniformBlockName);

    void getActiveUniformsiv(int program, int uniformCount, IntBuffer uniformIndices, int pname,
                            IntBuffer params);

    void getBufferParameteri64v(int target, int pname, LongBuffer params);

    Buffer getBufferPointerv(int target, int pname);

    int getFragDataLocation(int program, String name);

    void getInteger64v(int pname, LongBuffer params);

    void getQueryiv(int target, int pname, IntBuffer params);

    void getQueryObjectuiv(int id, int pname, IntBuffer params);

    void getSamplerParameterfv(int sampler, int pname, FloatBuffer params);

    void getSamplerParameteriv(int sampler, int pname, IntBuffer params);

    String getStringi(int name, int index);

    int getUniformBlockIndex(int program, String uniformBlockName);

    void getUniformIndices(int program, String[] uniformNames, IntBuffer uniformIndices);

    void getUniformuiv(int program, int location, IntBuffer params);

    void getVertexAttribIiv(int index, int pname, IntBuffer params);

    void getVertexAttribIuiv(int index, int pname, IntBuffer params);

    void invalidateFramebuffer(int target, int numAttachments, IntBuffer attachments);

    void invalidateSubFramebuffer(int target, int numAttachments, IntBuffer attachments, int x, int y,
                                 int width, int height);

    boolean isQuery(int id);

    boolean isSampler(int sampler);

    boolean isTransformFeedback(int id);

    boolean isVertexArray(int array);

    void pauseTransformFeedback();

    void programParameteri(int program, int pname, int value);

    void readBuffer(int mode);

    void renderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height);

    void resumeTransformFeedback();

    void samplerParameterf(int sampler, int pname, float param);

    void samplerParameterfv(int sampler, int pname, FloatBuffer param);

    void samplerParameteri(int sampler, int pname, int param);

    void samplerParameteriv(int sampler, int pname, IntBuffer param);

    void texImage3D(int target, int level, int internalformat, int width, int height, int depth, int border,
                   int format, int type, Buffer pixels);

    void texImage3D(int target, int level, int internalformat, int width, int height, int depth, int border,
                   int format, int type, int offset);

    void texSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height,
                      int depth, int format, int type, Buffer pixels);

    void texSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height,
                      int depth, int format, int type, int offset);

    void transformFeedbackVaryings(int program, String[] varyings, int bufferMode);

    void uniform1uiv(int location, int count, IntBuffer value);

    void uniform3uiv(int location, int count, IntBuffer value);

    void uniform4uiv(int location, int count, IntBuffer value);

    void uniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding);

    void uniformMatrix2x3fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix2x4fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix3x2fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix3x4fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix4x2fv(int location, int count, boolean transpose, FloatBuffer value);

    void uniformMatrix4x3fv(int location, int count, boolean transpose, FloatBuffer value);

    void vertexAttribDivisor(int index, int divisor);

    void vertexAttribI4i(int index, int x, int y, int z, int w);

    void vertexAttribI4ui(int index, int x, int y, int z, int w);

    void vertexAttribIPointer(int index, int size, int type, int stride, int offset);
}
