#version 330 core

layout(location = 0) in vec2 pos;
//layout(location = 1) in vec3 col;
layout(location = 1) in vec2 auv;


out vec2 uv;

//TODO: refactor
uniform mat4 t;
void main() {

uv = auv;
gl_Position =  t*vec4(pos , 0.0 , 1.0); //uTransform * 
}