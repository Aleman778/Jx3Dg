#version 330 core

out vec4 fragColor;

in vs_out {
	vec3 position;
} attr;

void main() {
	fragColor = vec4(1.0f, 0.0f, 0.0f, 1.0f);
}
