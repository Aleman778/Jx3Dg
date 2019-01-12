package jx3Dg.lwjgl3;

import static org.junit.Assert.*;

import org.junit.Test;

import jx3d.lwjgl3.Lwjgl3Files;

/**
 * @since 1.0
 * @author Aleman778
 */
public class Lwjgl3FilesTest {

	private Lwjgl3Files io = new Lwjgl3Files();
	
	@Test
	public void bytes() {
		String file = "/tests/files/ldbytes.dat";
		byte[] bytes = new byte[] {(byte) 84, (byte) 101, (byte) 115, (byte) 116};
		
		assertTrue(io.saveBytes(file, bytes));
		assertArrayEquals(bytes, io.loadBytes(file));
	}

	@Test
	public void text() {
		String file = "/tests/files/lorem.txt";
		String message = "Lorem ipsum dolor sit amet, consectetur adipisicing elit,\n"
				+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n"
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris\n"
				+ "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in\n"
				+ "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla\n"
				+ "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in\n"
				+ "culpa qui officia deserunt mollit anim id est laborum.";
		
		assertTrue(io.saveText(file, message));
		assertEquals(message, io.loadText(file));
	}
	
	@Test
	public void strings() {
		String file = "/tests/files/strings.txt";
		String[] bytes = new String[] {"Java Cross Platform 3D Graphics API!",
									   "LWJGL 3 platform is used for desktop apps.",
									   "This is just a test file",
									   "Write something important please..."};
		
		assertTrue(io.saveStrings(file, bytes));
		assertArrayEquals(bytes, io.loadStrings(file));
	}
	
	@Test
	public void image() {
		fail("Not implemented yet.");
	}
	
	@Test
	public void shape() {
		fail("Not implemented yet.");
	}
}
