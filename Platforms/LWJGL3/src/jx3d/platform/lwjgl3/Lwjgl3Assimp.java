package jx3d.platform.lwjgl3;

import jx3d.graphics.Mesh;
import jx3d.io.IOUtils;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.assimp.Assimp.*;

/**
 * Shape IO implementation using the Assimp Library.
 *
 * @author Aleman778
 * @since 1.0
 */
public class Lwjgl3Assimp {

    public static Mesh importShape(InputStream input) {
        byte[] bytes = IOUtils.loadBytes(input);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length + 1).order(ByteOrder.nativeOrder());
        buffer.put(bytes).put((byte) 0).flip();

        AIScene scene = aiImportFileFromMemory(buffer, aiProcess_Triangulate | aiProcess_FlipUVs, "obj");
        ArrayList<Vector3f> verts = new ArrayList<>();
        ArrayList<Vector2f> uvs = new ArrayList<>();
        ArrayList<Short> inds = new ArrayList<>();

        PointerBuffer meshes = scene.mMeshes();
        while (meshes.hasRemaining()) {
            AIMesh mesh = AIMesh.create(meshes.get());
            processMesh(verts, uvs, inds, mesh);
        }

        Mesh result = new Mesh();
        result.vertices = verts.toArray(new Vector3f[verts.size()]);
        result.uv = uvs.toArray(new Vector2f[uvs.size()]);
        result.indices = new short[inds.size()];
        for (int i = 0; i < inds.size(); i++) {
            result.indices[i] = inds.get(i);
        }

        return result;
    }

    public static boolean exportShape(OutputStream output) {
        return false;
    }

    private static void processMesh(ArrayList<Vector3f> verts, ArrayList<Vector2f> uvs, ArrayList<Short> inds, AIMesh mesh) {
        AIVector3D.Buffer aiVecBuffer = mesh.mVertices();
        while (aiVecBuffer.hasRemaining()) {
            AIVector3D aiVec = aiVecBuffer.get();
            verts.add(new Vector3f(aiVec.x(), aiVec.y(), aiVec.z()));
        }

        AIFace.Buffer aiFaceBuffer = mesh.mFaces();
        while (aiFaceBuffer.hasRemaining()) {
            if (aiFaceBuffer.hasRemaining()) {
                AIFace face = aiFaceBuffer.get();
                IntBuffer buf = face.mIndices();
                while (buf.hasRemaining()) {
                    inds.add((short) buf.get());
                }
            }
        }

        AIVector3D.Buffer aiUVBuffer = mesh.mTextureCoords(0);
        if (aiUVBuffer != null) {
            while (aiUVBuffer.hasRemaining()) {
                AIVector3D aiVec = aiUVBuffer.get();
                uvs.add(new Vector2f(aiVec.x(), aiVec.y()));
            }
        }
    }
}
