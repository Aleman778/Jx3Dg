package jx3d.graphics.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Interface wrapper for Open 2.x function and constants, based on OpenGLES.
 * @since 1.0
 * @author Aleman778
 */
public interface GL20 {

	public final int ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8B8A;
	public final int ACTIVE_ATTRIBUTES = 0x8B89;
	public final int ACTIVE_TEXTURE = 0x84E0;
	public final int ACTIVE_UNIFORM_MAX_LENGTH = 0x8B87;
	public final int ACTIVE_UNIFORMS = 0x8B86;
	public final int ALIASED_LINE_WIDTH_RANGE = 0x846E;
	public final int ALIASED_POINT_SIZE_RANGE = 0x846D;
	public final int ALPHA = 0x1906;
	public final int ALPHA_BITS = 0xD55;
	public final int ALWAYS = 0x207;
	public final int ARRAY_BUFFER = 0x8892;
	public final int ARRAY_BUFFER_BINDING = 0x8894;
	public final int ATTACHED_SHADERS = 0x8B85;
	public final int BACK = 0x405;
	public final int BLEND = 0xBE2;
	public final int BLEND_COLOR = 0x8005;
	public final int BLEND_DST_ALPHA = 0x80CA;
	public final int BLEND_DST_RGB = 0x80C8;
	public final int BLEND_EQUATION = 0x8009;
	public final int BLEND_EQUATION_ALPHA = 0x883D;
	public final int BLEND_EQUATION_RGB = 0x8009;
	public final int BLEND_SRC_ALPHA = 0x80CB;
	public final int BLEND_SRC_RGB = 0x80C9;
	public final int BLUE_BITS = 0xD54;
	public final int BOOL = 0x8B56;
	public final int BOOL_VEC2 = 0x8B57;
	public final int BOOL_VEC3 = 0x8B58;
	public final int BOOL_VEC4 = 0x8B59;
	public final int BUFFER_SIZE = 0x8764;
	public final int BUFFER_USAGE = 0x8765;
	public final int BYTE = 0x1400;
	public final int CCW = 0x901;
	public final int CLAMP_TO_EDGE = 0x812F;
	public final int COLOR_ATTACHMENT0 = 0x8CE0;
	public final int COLOR_BUFFER_BIT = 0x4000;
	public final int COLOR_CLEAR_VALUE = 0xC22;
	public final int COLOR_WRITEMASK = 0xC23;
	public final int COMPILE_STATUS = 0x8B81;
	public final int COMPRESSED_TEXTURE_FORMATS = 0x86A3;
	public final int CONSTANT_ALPHA = 0x8003;
	public final int CONSTANT_COLOR = 0x8001;
	public final int CULL_FACE = 0xB44;
	public final int CULL_FACE_MODE = 0xB45;
	public final int CURRENT_PROGRAM = 0x8B8D;
	public final int CURRENT_VERTEX_ATTRIB = 0x8626;
	public final int CW = 0x900;
	public final int DECR = 0x1E03;
	public final int DECR_WRAP = 0x8508;
	public final int DELETE_STATUS = 0x8B80;
	public final int DEPTH_ATTACHMENT = 0x8D00;
	public final int DEPTH_BITS = 0xD56;
	public final int DEPTH_BUFFER_BIT = 0x100;
	public final int DEPTH_CLEAR_VALUE = 0xB73;
	public final int DEPTH_COMPONENT = 0x1902;
	public final int DEPTH_COMPONENT16 = 0x81A5;
	public final int DEPTH_FUNC = 0xB74;
	public final int DEPTH_RANGE = 0xB70;
	public final int DEPTH_TEST = 0xB71;
	public final int DEPTH_WRITEMASK = 0xB72;
	public final int DITHER = 0xBD0;
	public final int DONT_CARE = 0x1100;
	public final int DST_ALPHA = 0x304;
	public final int DST_COLOR = 0x306;
	public final int DYNAMIC_DRAW = 0x88E8;
	public final int ELEMENT_ARRAY_BUFFER = 0x8893;
	public final int ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;
	public final int EQUAL = 0x202;
	public final int EXTENSIONS = 0x1F03;
	public final int FALSE = 0;
	public final int FASTEST = 0x1101;
	public final int FIXED = 0x140C;
	public final int FLOAT = 0x1406;
	public final int FLOAT_MAT2 = 0x8B5A;
	public final int FLOAT_MAT3 = 0x8B5B;
	public final int FLOAT_MAT4 = 0x8B5C;
	public final int FLOAT_VEC2 = 0x8B50;
	public final int FLOAT_VEC3 = 0x8B51;
	public final int FLOAT_VEC4 = 0x8B52;
	public final int FRAGMENT_SHADER = 0x8B30;
	public final int FRAMEBUFFER = 0x8D40;
	public final int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1;
	public final int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0;
	public final int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3;
	public final int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2;
	public final int FRAMEBUFFER_BINDING = 0x8CA6;
	public final int FRAMEBUFFER_COMPLETE = 0x8CD5;
	public final int FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6;
	public final int FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 0x8CD9;
	public final int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7;
	public final int FRAMEBUFFER_UNSUPPORTED = 0x8CDD;
	public final int FRONT = 0x404;
	public final int FRONT_AND_BACK = 0x408;
	public final int FRONT_FACE = 0xB46;
	public final int FUNC_ADD = 0x8006;
	public final int FUNC_REVERSE_SUBTRACT = 0x800B;
	public final int FUNC_SUBTRACT = 0x800A;
	public final int GENERATE_MIPMAP_HINT = 0x8192;
	public final int GEQUAL = 0x206;
	public final int GREATER = 0x204;
	public final int GREEN_BITS = 0xD53;
	public final int HIGH_FLOAT = 0x8DF2;
	public final int HIGH_INT = 0x8DF5;
	public final int IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B;
	public final int IMPLEMENTATION_COLOR_READ_TYPE = 0x8B9A;
	public final int INCR = 0x1E02;
	public final int INCR_WRAP = 0x8507;
	public final int INFO_LOG_LENGTH = 0x8B84;
	public final int INT = 0x1404;
	public final int INT_VEC2 = 0x8B53;
	public final int INT_VEC3 = 0x8B54;
	public final int INT_VEC4 = 0x8B55;
	public final int INVALID_ENUM = 0x500;
	public final int INVALID_FRAMEBUFFER_OPERATION = 0x506;
	public final int INVALID_OPERATION = 0x502;
	public final int INVALID_VALUE = 0x501;
	public final int INVERT = 0x150A;
	public final int KEEP = 0x1E00;
	public final int LEQUAL = 0x203;
	public final int LESS = 0x201;
	public final int LINE_LOOP = 0x2;
	public final int LINE_STRIP = 0x3;
	public final int LINE_WIDTH = 0xB21;
	public final int LINEAR = 0x2601;
	public final int LINEAR_MIPMAP_LINEAR = 0x2703;
	public final int LINEAR_MIPMAP_NEAREST = 0x2701;
	public final int LINES = 0x1;
	public final int LINK_STATUS = 0x8B82;
	public final int LOW_FLOAT = 0x8DF0;
	public final int LOW_INT = 0x8DF3;
	public final int LUMINANCE = 0x1909;
	public final int LUMINANCE_ALPHA = 0x190A;
	public final int MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;
	public final int MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C;
	public final int MAX_FRAGMENT_UNIFORM_VECTORS = 0x8DFD;
	public final int MAX_RENDERBUFFER_SIZE = 0x84E8;
	public final int MAX_TEXTURE_IMAGE_UNITS = 0x8872;
	public final int MAX_TEXTURE_SIZE = 0xD33;
	public final int MAX_VARYING_VECTORS = 0x8DFC;
	public final int MAX_VERTEX_ATTRIBS = 0x8869;
	public final int MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8B4C;
	public final int MAX_VERTEX_UNIFORM_VECTORS = 0x8DFB;
	public final int MAX_VIEWPORT_DIMS = 0xD3A;
	public final int MEDIUM_FLOAT = 0x8DF1;
	public final int MEDIUM_INT = 0x8DF4;
	public final int MIRRORED_REPEAT = 0x8370;
	public final int NEAREST = 0x2600;
	public final int NEAREST_MIPMAP_LINEAR = 0x2702;
	public final int NEAREST_MIPMAP_NEAREST = 0x2700;
	public final int NEVER = 0x200;
	public final int NICEST = 0x1102;
	public final int NO_ERROR = 0;
	public final int NONE = 0;
	public final int NOTEQUAL = 0x205;
	public final int NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2;
	public final int NUM_SHADER_BINARY_FORMATS = 0x8DF9;
	public final int ONE = 1;
	public final int ONE_MINUS_CONSTANT_ALPHA = 0x8004;
	public final int ONE_MINUS_CONSTANT_COLOR = 0x8002;
	public final int ONE_MINUS_DST_ALPHA = 0x305;
	public final int ONE_MINUS_DST_COLOR = 0x307;
	public final int ONE_MINUS_SRC_ALPHA = 0x303;
	public final int ONE_MINUS_SRC_COLOR = 0x301;
	public final int OUT_OF_MEMORY = 0x505;
	public final int PACK_ALIGNMENT = 0xD05;
	public final int POINTS = 0x0;
	public final int POLYGON_OFFSET_FACTOR = 0x8038;
	public final int POLYGON_OFFSET_FILL = 0x8037;
	public final int POLYGON_OFFSET_UNITS = 0x2A00;
	public final int RED_BITS = 0xD52;
	public final int RENDERBUFFER = 0x8D41;
	public final int RENDERBUFFER_ALPHA_SIZE = 0x8D53;
	public final int RENDERBUFFER_BINDING = 0x8CA7;
	public final int RENDERBUFFER_BLUE_SIZE = 0x8D52;
	public final int RENDERBUFFER_DEPTH_SIZE = 0x8D54;
	public final int RENDERBUFFER_GREEN_SIZE = 0x8D51;
	public final int RENDERBUFFER_HEIGHT = 0x8D43;
	public final int RENDERBUFFER_INTERNAL_FORMAT = 0x8D44;
	public final int RENDERBUFFER_RED_SIZE = 0x8D50;
	public final int RENDERBUFFER_STENCIL_SIZE = 0x8D55;
	public final int RENDERBUFFER_WIDTH = 0x8D42;
	public final int RENDERER = 0x1F01;
	public final int REPEAT = 0x2901;
	public final int REPLACE = 0x1E01;
	public final int RGB = 0x1907;
	public final int RGB5_A1 = 0x8057;
	public final int RGB565 = 0x8D62;
	public final int RGBA = 0x1908;
	public final int RGBA4 = 0x8056;
	public final int SAMPLE_ALPHA_TO_COVERAGE = 0x809E;
	public final int SAMPLE_BUFFERS = 0x80A8;
	public final int SAMPLE_COVERAGE = 0x80A0;
	public final int SAMPLE_COVERAGE_INVERT = 0x80AB;
	public final int SAMPLE_COVERAGE_VALUE = 0x80AA;
	public final int SAMPLER_2D = 0x8B5E;
	public final int SAMPLER_CUBE = 0x8B60;
	public final int SAMPLES = 0x80A9;
	public final int SCISSOR_BOX = 0xC10;
	public final int SCISSOR_TEST = 0xC11;
	public final int SHADER_BINARY_FORMATS = 0x8DF8;
	public final int SHADER_COMPILER = 0x8DFA;
	public final int SHADER_SOURCE_LENGTH = 0x8B88;
	public final int SHADER_TYPE = 0x8B4F;
	public final int SHADING_LANGUAGE_VERSION = 0x8B8C;
	public final int SHORT = 0x1402;
	public final int SRC_ALPHA = 0x302;
	public final int SRC_ALPHA_SATURATE = 0x308;
	public final int SRC_COLOR = 0x300;
	public final int STATIC_DRAW = 0x88E4;
	public final int STENCIL_ATTACHMENT = 0x8D20;
	public final int STENCIL_BACK_FAIL = 0x8801;
	public final int STENCIL_BACK_FUNC = 0x8800;
	public final int STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
	public final int STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
	public final int STENCIL_BACK_REF = 0x8CA3;
	public final int STENCIL_BACK_VALUE_MASK = 0x8CA4;
	public final int STENCIL_BACK_WRITEMASK = 0x8CA5;
	public final int STENCIL_BITS = 0xD57;
	public final int STENCIL_BUFFER_BIT = 0x400;
	public final int STENCIL_CLEAR_VALUE = 0xB91;
	public final int STENCIL_FAIL = 0xB94;
	public final int STENCIL_FUNC = 0xB92;
	public final int STENCIL_INDEX8 = 0x8D48;
	public final int STENCIL_PASS_DEPTH_FAIL = 0xB95;
	public final int STENCIL_PASS_DEPTH_PASS = 0xB96;
	public final int STENCIL_REF = 0xB97;
	public final int STENCIL_TEST = 0xB90;
	public final int STENCIL_VALUE_MASK = 0xB93;
	public final int STENCIL_WRITEMASK = 0xB98;
	public final int STREAM_DRAW = 0x88E0;
	public final int SUBPIXEL_BITS = 0xD50;
	public final int TEXTURE = 0x1702;
	public final int TEXTURE_2D = 0xDE1;
	public final int TEXTURE_BINDING_2D = 0x8069;
	public final int TEXTURE_BINDING_CUBE_MAP = 0x8514;
	public final int TEXTURE_CUBE_MAP = 0x8513;
	public final int TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516;
	public final int TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518;
	public final int TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A;
	public final int TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515;
	public final int TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517;
	public final int TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519;
	public final int TEXTURE_MAG_FILTER = 0x2800;
	public final int TEXTURE_MIN_FILTER = 0x2801;
	public final int TEXTURE_WRAP_S = 0x2802;
	public final int TEXTURE_WRAP_T = 0x2803;
	public final int TEXTURE0 = 0x84C0;
	public final int TEXTURE1 = 0x84C1;
	public final int TEXTURE10 = 0x84CA;
	public final int TEXTURE11 = 0x84CB;
	public final int TEXTURE12 = 0x84CC;
	public final int TEXTURE13 = 0x84CD;
	public final int TEXTURE14 = 0x84CE;
	public final int TEXTURE15 = 0x84CF;
	public final int TEXTURE16 = 0x84D0;
	public final int TEXTURE17 = 0x84D1;
	public final int TEXTURE18 = 0x84D2;
	public final int TEXTURE19 = 0x84D3;
	public final int TEXTURE2 = 0x84C2;
	public final int TEXTURE20 = 0x84D4;
	public final int TEXTURE21 = 0x84D5;
	public final int TEXTURE22 = 0x84D6;
	public final int TEXTURE23 = 0x84D7;
	public final int TEXTURE24 = 0x84D8;
	public final int TEXTURE25 = 0x84D9;
	public final int TEXTURE26 = 0x84DA;
	public final int TEXTURE27 = 0x84DB;
	public final int TEXTURE28 = 0x84DC;
	public final int TEXTURE29 = 0x84DD;
	public final int TEXTURE3 = 0x84C3;
	public final int TEXTURE30 = 0x84DE;
	public final int TEXTURE31 = 0x84DF;
	public final int TEXTURE4 = 0x84C4;
	public final int TEXTURE5 = 0x84C5;
	public final int TEXTURE6 = 0x84C6;
	public final int TEXTURE7 = 0x84C7;
	public final int TEXTURE8 = 0x84C8;
	public final int TEXTURE9 = 0x84C9;
	public final int TRIANE_FAN = 0x6;
	public final int TRIANE_STRIP = 0x5;
	public final int TRIANES = 0x4;
	public final int TRUE = 1;
	public final int UNPACK_ALIGNMENT = 0xCF5;
	public final int UNSIGNED_BYTE = 0x1401;
	public final int UNSIGNED_INT = 0x1405;
	public final int UNSIGNED_SHORT = 0x1403;
	public final int UNSIGNED_SHORT_4_4_4_4 = 0x8033;
	public final int UNSIGNED_SHORT_5_5_5_1 = 0x8034;
	public final int UNSIGNED_SHORT_5_6_5 = 0x8363;
	public final int VALIDATE_STATUS = 0x8B83;
	public final int VENDOR = 0x1F00;
	public final int VERSION = 0x1F02;
	public final int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;
	public final int VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
	public final int VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;
	public final int VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;
	public final int VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
	public final int VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
	public final int VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
	public final int VERTEX_SHADER = 0x8B31;
	public final int VIEWPORT = 0xBA2;
	public final int ZERO = 0;

	public final int COVERAGE_BUFFER_BIT_NV = 0x8000;
	public final int MAX_TEXTURE_MAX_ANISOTROPY_EXT = 0x84FF;
	public final int TEXTURE_MAX_ANISOTROPY_EXT = 0x84FE;

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
	
	public void bufferData(int target, int size, int usage);

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
