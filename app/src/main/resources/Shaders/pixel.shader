#version 330 core

out vec4 FragColor;
in vec3 color;

uniform float uTime;

void main() {

FragColor = vec4(color.r , color.g , color.b , 1.0f);
}