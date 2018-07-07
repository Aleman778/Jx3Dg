#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texcoord;

out vs_out {
	vec3 position;
	vec2 texcoord;
} attr;

void main() {
	attr.position = position;
	attr.texcoord = texcoord;
	gl_Position = vec4(position, 1.0f);
}
