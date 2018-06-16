#version 330 core

layout (location = 0) in vec3 position;

out vs_out {
	vec3 position;
} attr;

void main() {
	attr.position = position;
	gl_Position = vec4(position, 1.0f);
}
