package jx3d.platform.lwjgl3;

import jx3d.graphics.Image;
import jx3d.graphics.Mesh;
import jx3d.io.FileHandle;
import jx3d.io.Files;
import jx3d.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Implementation
 *
 * @author Aleman778
 * @since 1.0
 */
public class Lwjgl3Files implements Files {

    public static final String LOCAL_DIR = new File("").getAbsolutePath() + File.separator;
    public static final String EXTERNAL_DIR = new File("").getPath() + File.separator;

    @Override
    public byte[] loadBytes(String file) {
        return IOUtils.loadBytes(createInput(file));
    }

    @Override
    public boolean saveBytes(String file, byte[] bytes) {
        return IOUtils.saveBytes(createOutput(file), bytes);
    }

    @Override
    public String loadText(String file) {
        return IOUtils.loadText(createInput(file));
    }

    @Override
    public boolean saveText(String file, String text) {
        return IOUtils.saveText(createOutput(file), text);
    }

    @Override
    public String[] loadStrings(String file) {
        return IOUtils.loadStrings(createInput(file));
    }

    @Override
    public boolean saveStrings(String file, String[] strings) {
        return IOUtils.saveStrings(createOutput(file), strings);
    }

    @Override
    public Image loadImage(String file) {
        return IOUtils.loadImage(createInput(file));
    }

    @Override
    public boolean saveImage(String file, Image image) {
        return false;
    }

    @Override
    public Mesh loadShape(String file) {
        return Lwjgl3Assimp.importShape(createInput(file));
    }

    @Override
    public boolean saveShape(String file, Mesh shape) {
        return Lwjgl3Assimp.exportShape(createOutput(file));
    }

    @Override
    public File selectFolder(String title, String current, String filter) {
        return null;
    }

    @Override
    public File selectFile(String title, String current, int action, String filter) {
        return null;
    }

    @Override
    public InputStream createInput(String file) {
        if (file == null)
            return null;

        if (file.isEmpty())
            return null;

        //HTTP URL file
        InputStream input = null;
        if (file.contains("http://") || file.contains("https://"))
            input = inputFromURL(file);

        //Project resource
        if (input == null)
            input = inputFromResource(file);

        //Local directory
        if (input == null)
            input = local(file).toInputStream();

        //External directory
        if (input == null)
            input = external(file).toInputStream();

        return input;
    }

    @Override
    public OutputStream createOutput(String file) {
        if (file == null)
            return null;

        if (file.isEmpty())
            return null;

        OutputStream output = null;

        //Local directory
        if (output == null)
            output = local(file).toOutputStream();

        //External directory
        if (output == null)
            output = external(file).toOutputStream();

        return output;
    }

    private InputStream inputFromURL(String file) {
        try {
            URL url = new URL(file);
            URLConnection connection = url.openConnection();

            if (connection instanceof HttpURLConnection) {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setInstanceFollowRedirects(true);

                int response = httpConnection.getResponseCode();
                if (response >= 300 && response < 400) {
                    inputFromURL(httpConnection.getHeaderField("Location"));
                }

                return httpConnection.getInputStream();
            } else if (connection instanceof JarURLConnection) {
                return url.openStream();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private InputStream inputFromResource(String file) {
        ClassLoader loader = getClass().getClassLoader();
        InputStream input = loader.getResourceAsStream(file);
        if (input != null) {
            String clss = input.getClass().getName();
            if (!clss.equals("sun.plugin.cache.EmptyInputStream")) {
                return input;
            }
        }
        return null;
    }

    @Override
    public FileHandle local(String file) {
        return new FileHandle(LOCAL_DIR + file);
    }

    @Override
    public FileHandle external(String file) {
        return new FileHandle(EXTERNAL_DIR + file);
    }
}
