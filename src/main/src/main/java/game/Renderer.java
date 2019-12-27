package game;

import engine.GameItem;
import engine.Utils;
import engine.Window;
import engine.graph.Camera;
import engine.graph.ShaderProgram;
import engine.graph.Transformation;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    /**
     * Jimmy Deng, June 07, 2019
     * This class uses the provided shaders and renders the data in the gameItem array
     *
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private ShaderProgram shaderProgram;

    private Transformation transformation;

    public Renderer() {
        this.transformation = new Transformation();
    }

    public void init(Window window) throws Exception {
        // Create shader
        this.shaderProgram = new ShaderProgram();
        this.shaderProgram.createVertexShader(Utils.loadResource("/vertex.vert")); //uses method in Utils
        this.shaderProgram.createFragmentShader(Utils.loadResource("/fragment.frag"));
        this.shaderProgram.link();


        // Create uniforms for modelView and projection matrices and texture
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");

        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clear() {
        //GL11.glClearColor(0.0f,0.0f,0.0f,0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Camera camera, GameItem[] gameItems) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        this.shaderProgram.bind();


        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);


        // Update view Matrix
        Matrix4f viewMatrix = transformation.getViewMatrix(camera);


        // Render each gameItem
        for(GameItem gameItem : gameItems) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mes for this game item
            gameItem.getMesh().render();
        }
        shaderProgram.unbind();
    }

    public void cleanup() {
        if (this.shaderProgram != null) {
            this.shaderProgram.cleanup();
        }
    }
}