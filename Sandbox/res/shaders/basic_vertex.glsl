#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texcoord;

out vs_out {
	vec3 position;
	vec2 texcoord;
} attr;

uniform mat4 projection;
uniform mat4 transform;

void main() {
	attr.position = position;
	attr.texcoord = texcoord;
	gl_Position = projection * transform * vec4(position, 1.0f);
}
