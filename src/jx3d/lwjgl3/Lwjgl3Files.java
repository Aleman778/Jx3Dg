package jx3d.lwjgl3;

import static org.lwjgl.assimp.Assimp.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import jx3d.io.FileHandle;
import jx3d.io.Files;
import jx3d.io.JavaIOSystem;

/**
 * Implementation 
 * @since 1.0
 * @author Aleman778
 */
public class Lwjgl3Files extends Files {

	public Lwjgl3Files() {
		super(new JavaIOSystem());
	}
	
	@Override
	public byte[] loadBytes(String file) {
		try {
			InputStream stream = io.createInput(file);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveBytes(String file, byte[] bytes) {
		return false;
	}
	
	@Override
	public String loadText(String file) {
		try {
			String result = "";
			BufferedReader reader = io.createReader(file);
			String line;
			while ((line = reader.readLine()) != null) {
				result += line + "\n";
			}
			reader.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean saveText(String file, String text) {
		return true;
	}

	@Override
	public String[] loadStrings(String file) {
		return null;
	}

	@Override
	public boolean saveStrings(String file, String[] strings) {
		return false;
	}

	/*
	 * TODO: DEBUG MOVE load shape and save shape into different class for ASSIMP loading.
	 */
	@Override
	public Mesh loadShape(String file) {
		AIScene scene = aiImportFile(file, aiProcess_Triangulate | aiProcess_FlipUVs);
		
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

	@Override
	public boolean saveShape(String file, Mesh mesh) {
		// TODO Auto-generated method stub
		return false;
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
	public File selectFolder(String title, String current, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File selectFile(String title, String current, int action, String filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
