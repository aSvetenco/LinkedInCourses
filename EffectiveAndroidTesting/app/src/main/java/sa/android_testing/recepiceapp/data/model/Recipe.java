package sa.android_testing.recepiceapp.data.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Recipe {
    private static final String ID_PREFIX = "id=";
    private static final String TITLE_PREFIX = "title=";
    public final String id;
    public final String title;
    public final String descriptions;

    private Recipe(String id, String title, String descriptions) {
        this.id = id;
        this.title = title;
        this.descriptions = descriptions;
    }

    public static Recipe readFromStream(InputStream stream) {
        String id = null;
        String title = null;
        StringBuilder descriptions = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try{
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                if (line.startsWith(ID_PREFIX)) {
                    id = line.substring(ID_PREFIX.length());
                    continue;
                }
                if (line.startsWith(TITLE_PREFIX)) {
                    title = line.substring(TITLE_PREFIX.length());
                    continue;
                }
                if (descriptions.length()>0) {
                    descriptions.append("\n");
                }
                descriptions.append(line);

            }

        } catch (IOException e) {
            return null;
        }
        return new Recipe(id, title, descriptions.toString());
    }


}
