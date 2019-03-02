package sandbox;

import jx3d.core.Module;
import jx3d.lwjgl3.Lwjgl3Application;
import jx3d.lwjgl3.Lwjgl3Configurations;

public class DesktopLauncher {

    public static void main(String args[]) {
        Lwjgl3Configurations config = new Lwjgl3Configurations();
        config.renderer = Module.OPENGL_DEBUG;
        config.profile = Module.GL20_PROFILE;

        Lwjgl3Application app = new Lwjgl3Application(config, new TestApplication());
        app.run();

    }
}
