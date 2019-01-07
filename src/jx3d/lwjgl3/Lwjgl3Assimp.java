package jx3d.lwjgl3;

import static org.lwjgl.assimp.Assimp.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;

import jx3d.graphics.Mesh;

/**
 * Shape IO implementation using the Assimp Library.
 * @since 1.0
 * @author Aleman778
 */
public class Lwjgl3Assimp extends ShapeIO {

	@Override
	public Mesh loadShape(String file) {
		try {
			InputStream input = sys.createInput(file);
			byte[] bytes = new byte[1024 * 4];
			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			int count = (int) copy(input, output, bytes);
			bytes = output.toByteArray();
			ByteBuffer buffer = ByteBuffer.allocateDirect(count + 1).order(ByteOrder.nativeOrder());
	        buffer.put(bytes).put((byte) 0).flip();
	        
			//AIScene scene = aiImportFile(buffer, aiProcess_Triangulate | aiProcess_FlipUVs);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static long copy(final InputStream input, final OutputStream output, final byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
	}
	
	private void processMesh(ArrayList<Vector3f> verts, ArrayList<Vector2f> uvs, ArrayList<Short> inds, AIMesh mesh) {
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

	@Override
	public boolean saveShape(String file, Mesh shape) {
		return false;
	}

}
