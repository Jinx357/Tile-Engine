
plugins {
    java
    application
}

tasks.jar {
	manifest {
		
		attributes["Main-Class"] = "com.kira.game.core.App"
	}
	from(configurations.runtimeClasspath.get().map {
		
		if(it.isDirectory) it 
		else zipTree(it)
	})
	
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

repositories {
   
    mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
   
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
	
	
	

	implementation(platform("org.lwjgl:lwjgl-bom:3.4.1"))

	implementation("org.lwjgl", "lwjgl")
	implementation("org.lwjgl", "lwjgl-glfw")
	implementation("org.lwjgl", "lwjgl-openal")
	implementation("org.lwjgl", "lwjgl-opengl")
	implementation("org.lwjgl", "lwjgl-stb")
	implementation ("org.lwjgl", "lwjgl", classifier = "natives-windows")
	implementation ("org.lwjgl", "lwjgl-glfw", classifier = "natives-windows")
	implementation ("org.lwjgl", "lwjgl-openal", classifier = "natives-windows")
	implementation ("org.lwjgl", "lwjgl-opengl", classifier = "natives-windows")
	implementation ("org.lwjgl", "lwjgl-stb", classifier = "natives-windows")
	
	implementation("org.joml", "joml", "1.10.8")
	implementation("org.joml", "joml-primitives", "1.10.0")
	
	implementation("io.github.spair", "imgui-java-binding", "1.87.0")
	implementation("io.github.spair", "imgui-java-lwjgl3", "1.87.0")
	implementation("io.github.spair", "imgui-java-natives-windows", "1.87.0")




}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {

    mainClass = "com.kira.game.core.App"
}

tasks.named<JavaExec>("run") {
	jvmArgs("-Dorg.lwjgl.util.Debug=true");
	jvmArgs("-Dorg.lwjgl.util.DebugLoader=true");
	//jvmArgs("-Dorg.lwjgl.opengl.libname=");
	
}



tasks.named<Test>("test") {
   
    useJUnitPlatform()
}
