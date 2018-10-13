package test;

import jx3d.core.Module;
import jx3d.lwjgl3.Lwjgl3Files;

public class FilesTest {

	public static void main(String[] args) {
		Lwjgl3Files f = new Lwjgl3Files(null);
		f.open(Module.WRITE, "src/test/test");
		System.out.println(f.write("Test2"));
		f.close();
		f.open(Module.READ, "src/test/test");
		System.out.println(f.read());
		
	}
	
}
