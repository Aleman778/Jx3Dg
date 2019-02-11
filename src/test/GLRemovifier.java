package test;

import java.io.*;

public class GLRemovifier {


    public static void main(String[] args) {
        //String filename = "C:\\Users\\alexa\\Documents\\eclipse\\jx3DGraphics\\src\\jx3d\\graphics\\opengl\\GL30";
        String filename = "C:\\Users\\alexa\\Documents\\eclipse\\jx3DGraphics\\src\\jx3d\\desktop\\LwjglGL30";
        File file = new File(filename + ".java");

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String document = "";
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf(" gl");
                if (idx > 0 && line.indexOf("public") > 0) {
                    line = line.replaceAll(" gl", " ");
                    int nxt = idx + 1;
                    char[] arr = line.toCharArray();
                    arr[nxt] += 32;

                    String modified = new String(arr);
                    document += modified + "\n";
                } else {
                    document += line + "\n";
                }
            }
            br.close();

            System.out.println(document);


            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(document);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
