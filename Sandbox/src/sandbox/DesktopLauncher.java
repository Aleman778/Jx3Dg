package sandbox;

import jx3d.core.Module;
import jx3d.platform.lwjgl3.Lwjgl3Application;
import jx3d.platform.lwjgl3.Lwjgl3Configurations;

public class DesktopLauncher {

    public static void main(String args[]) {
        Lwjgl3Configurations config = new Lwjgl3Configurations();
        config.renderer = Module.OPENGL_DEBUG;

        Lwjgl3Application app = new Lwjgl3Application(config, new TestApplication());
        app.run();

    }
}
