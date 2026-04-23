#version 330 core

layout(location = 0) in vec2 pos;
layout(location = 1) in vec2 auv;


out vec2 uv;

//TODO: refactor
uniform mat4 t;
uniform mat4 v;
void main() {

uv = auv;
gl_Position =  v*t*vec4(pos , 0.0 , 1.0);
}