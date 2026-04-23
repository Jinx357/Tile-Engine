#version 330 core


out vec4 FragColor;
in vec2 uv;

//uniform float uTime;
uniform sampler2D tex;
uniform float tint;

void main() {

FragColor = texture(tex , uv) * vec4(tint , 1.0 , 0.0 , 0.0);
}