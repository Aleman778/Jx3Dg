package jx3d.lwjgl3;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jx3d.io.Files;
import jx3d.io.JavaSystemIO;
import jx3d.io.JavaImageIO;
import jx3d.io.AssimpShapeIO;

/**
 * Implementation 
 * @since 1.0
 * @author Aleman778
 */
public class Lwjgl3Files extends Files {

	public Lwjgl3Files() {
		super(new JavaSystemIO(), new JavaImageIO(), new AssimpShapeIO());
	}
	
	@Override
	public byte[] loadBytes(String file) {
		try {
			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			InputStream input = sys.createInput(file);
			copy(input, output, new byte[1024 * 4]);
			return output.toByteArray();
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

	@Override
	public boolean saveBytes(String file, byte[] bytes) {
		return false;
	}
	
	@Override
	public String loadText(String file) {
		try {
			String result = "";
			BufferedReader reader = sys.createReader(file);
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
