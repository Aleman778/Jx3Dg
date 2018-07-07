#version 330 core

out vec4 fragColor;

in vs_out {
	vec3 position;
	vec2 texcoord;
} attr;

uniform sampler2D sampler;

void main() {
	fragColor = texture(sampler, attr.texcoord);
}
