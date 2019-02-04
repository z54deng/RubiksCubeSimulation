package engine.graph;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL30.*;


public class Mesh {

    private final int vaoId;        //Generated using glGenVertexArrays

    private final int posVboId;     //generated using glGenBuffers

    private final int colourVboId;  //generated using glGenBuffers

    private final int idxVboId;     //generated using glGenBuffers

    private final int vertexCount;

    public Mesh(float[] positions, float[] colours,  int[] indices) {

        FloatBuffer posBuffer = null;
        FloatBuffer colourBuffer = null;
        IntBuffer indicesBuffer = null;
        /*
        A VBO is just a memory buffer stored in the graphics card
        memory that stores vertices. This is where we will
        transfer our array of floats that model a triangle.
         */

        /*
        A VAO stores multiple VBO, and they are called attribute list,
        each one can hold one type of data, position, color, texture..
         */
        try {
            this.vertexCount = indices.length;

            // Create the VAO and bind to it
            this.vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Position VBO
            this.posVboId = glGenBuffers();
            posBuffer  = MemoryUtil.memAllocFloat(positions.length);
            posBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, this.posVboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            // Define structure of the data
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
             /*
            index: location where the shader expects data
            size: specifies the number of components per vertex attribute (from 1 to 4)
                3 because its 3D
            type: what data type is in array, this case its float
            normalized: rescaling high and low values
            stride: Specifies the byte offset between consecutive
                generic vertex attributes. (offset between consecutive index)
            offset: Specifies an offset to the first component in the buffer.
                distance between given to first
            */

            // Colour VBO
            this.colourVboId = glGenBuffers();
            colourBuffer = MemoryUtil.memAllocFloat(colours.length);
            colourBuffer.put(colours).flip();
            glBindBuffer(GL_ARRAY_BUFFER, this.colourVboId);
            glBufferData(GL_ARRAY_BUFFER, colourBuffer, GL_STATIC_DRAW);
            // index 1 because this is the location where
            // our shader will be expecting that data.
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);


            // Index VBO
            this.idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);


            // Unbind the VBO
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            // Unbind the VAO
            glBindVertexArray(0);
        } finally {
            //java garbage collection will not clean up off-heap allocations
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (colourBuffer != null) {
                MemoryUtil.memFree(colourBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }


    public void render() {


        // Draw the mesh
        glBindVertexArray(this.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        // Draw the vertices
        // p1: type of primitives to render
        // p2: starting index in the enabled arrays.
        // p3: the number of indices to be rendered.
        //glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());

        glDrawElements(GL_TRIANGLES, this.getVertexCount(), GL_UNSIGNED_INT, 0);
        //mode, count, type, indices

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);


        /*
            clear the window, bind the shader program,
            bind the VAO, draw the vertices stored
            in the VBO associated to the VAO and
            restore the state.
         */
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(colourVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}