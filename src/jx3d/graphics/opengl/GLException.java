package jx3d.graphics.opengl;

public class GLException extends RuntimeException {
	
	private static final long serialVersionUID = 153114369872664471L;

	public GLException(int errcode) {
		super(getErrorMessage(errcode));
	}

	private static String getErrorMessage(int errcode) {
		switch (errcode) {
		case GL20.INVALID_ENUM:
			return "INVALID_ENUM - enum argument out of range.";
		case GL20.INVALID_VALUE:
			return "INVALID_VALUE - numeric argument out of range.";
		case GL20.INVALID_OPERATION:
			return "INVALID_OPERATION - operation illegal in current state.";
		case GL20.STACK_OVERFLOW:
			return "STACK_OVERFLOW - function would cause a stack overflow.";
		case GL20.STACK_UNDERFLOW:
			return "STACK_UNDERFLOW - function would cause a stack underflow.";
		case GL20.OUT_OF_MEMORY:
			return "OUT_OF_MEMORY - not enough memory left to execute function.";
		case GL20.INVALID_FRAMEBUFFER_OPERATION:
			return "INVALID_FRAMEBUFFER_OPERATION - operation performed on incomplete framebuffer.";
		case GL20.EXT.CONTEXT_LOST:
			return "CONTEXT_LOST - context is lost, due to a graphics card reset.\n"
					+ "Part of OpenGL 4.5 or ARB_KHR_robustness.";
		case GL20.EXT.TABLE_TOO_LARGE:
			return "EXT.TABLE_TOO_LARGE - part of the ARB_imaging extension.\n"
					+ "This error code is deprecated in OpenGL 3 and removed in the core profile.";
		case GL20.NO_ERROR:
			return "No error has been recorded";
		}
		return "";
	}
}
