package test;

import jx3d.core.Constants;
import jx3d.desktop.DesktopFiles;

public class FilesTest {

	public static void main(String[] args) {
		DesktopFiles f = new DesktopFiles(null);
		f.open(Constants.WRITE, "src/test/test");
		System.out.println(f.write("Test2"));
		f.close();
		f.open(Constants.READ, "src/test/test");
		System.out.println(f.read());
		
	}
	
}
