import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import util.ShaderLocationsVault;
import util.ShaderProgram;

public class Box extends ASimpleObjectInstance {

  Box(
      GL3 gl,
      ShaderProgram program,
      ShaderLocationsVault shaderLocations,
      String name,
      float r, float g, float b) {
    super(gl, program, shaderLocations, name, r, g, b);
    init();
  }

  private void init() {
    //set up vertex attributes (in this case we have only position)
    List<Vector4f> positions = new ArrayList<>();
    float d = 1000;
    float dd = 400;
    positions.add(new Vector4f(d, -d, -dd, 1f));
    positions.add(new Vector4f(d, d, -dd, 1f));
    positions.add(new Vector4f(-d, d, -dd, 1f));
    positions.add(new Vector4f(-d, -d, -dd, 1f));
    positions.add(new Vector4f(d, -d, dd, 1f));
    positions.add(new Vector4f(d, d, dd, 1f));
    positions.add(new Vector4f(-d, d, dd, 1f));
    positions.add(new Vector4f(-d, -d, dd, 1f));

    // set up indices
    List<Integer> indices = new ArrayList<>();
    indices.add(0);
    indices.add(1);

    indices.add(1);
    indices.add(2);

    indices.add(2);
    indices.add(3);

    indices.add(3);
    indices.add(0);

    indices.add(4);
    indices.add(5);

    indices.add(5);
    indices.add(6);

    indices.add(6);
    indices.add(7);

    indices.add(7);
    indices.add(4);

    indices.add(4);
    indices.add(0);

    indices.add(5);
    indices.add(1);

    indices.add(6);
    indices.add(2);

    indices.add(7);
    indices.add(3);

    // set up object
    usualObjAdd(positions, indices, GL.GL_LINES);

//    // set up indices
//    List<Integer> indices2 = new ArrayList<>();
//    indices2.add(0);
//    indices2.add(1);
//    indices2.add(3);
//    indices2.add(2);
//    indices2.add(1);
//    indices2.add(3);
//    usualObjAdd(positions, indices2, GL.GL_TRIANGLES);
//
//    // set up indices
//    List<Integer> indices3 = new ArrayList<>();
//    indices3.add(4);
//    indices3.add(5);
//    indices3.add(7);
//    indices3.add(5);
//    indices3.add(6);
//    indices3.add(7);
//    usualObjAdd(positions, indices3, GL.GL_TRIANGLES);

  }

  @Override
  public void draw(GLAutoDrawable gla, Matrix4f outModelView, Matrix4f proj) {
    GL3 gl = gla.getGL().getGL3();
    FloatBuffer fb16 = Buffers.newDirectFloatBuffer(16);
    FloatBuffer fb4 = Buffers.newDirectFloatBuffer(4);
    FloatBuffer fb42 = Buffers.newDirectFloatBuffer(4);

    gl.glUniformMatrix4fv(
        shaderLocations.getLocation("projection"),
        1, false, proj.get(fb16));

    //pass the modelview matrix to the shader
    gl.glUniformMatrix4fv(
        shaderLocations.getLocation("modelview"),
        1, false, outModelView.get(fb16));

    //send the color of the triangle
    gl.glUniform4fv(
        shaderLocations.getLocation("vColor")
        , 1, material.getAmbient().get(fb4));

    meshObjList.get(0).draw(gla);

//    material.setAmbient(1, 0, 0);
//    //send the color of the triangle
//    gl.glUniform4fv(
//        shaderLocations.getLocation("vColor")
//        , 1, material.getAmbient().get(fb42));
//    meshObjList.get(1).draw(gla);
//
//    material.setAmbient(0, 0, 1);
//    //send the color of the triangle
//    gl.glUniform4fv(
//        shaderLocations.getLocation("vColor")
//        , 1, material.getAmbient().get(fb42));
//    meshObjList.get(2).draw(gla);
//
//    material.setAmbient(1, 1, 1);
  }
}
