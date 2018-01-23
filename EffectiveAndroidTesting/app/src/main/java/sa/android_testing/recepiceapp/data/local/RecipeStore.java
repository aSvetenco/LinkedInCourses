package sa.android_testing.recepiceapp.data.local;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sa.android_testing.recepiceapp.data.model.Recipe;

public class RecipeStore {

    public final List<Recipe> recipes = new ArrayList<>();
    public final Map<String, Recipe> map = new HashMap<>();

    public RecipeStore(Context context, String directory) {
        List<InputStream> streams = getAssetStream(context.getAssets(), directory);
        for (InputStream stream : streams) {
            Recipe recipe = Recipe.readFromStream(stream);
            if (recipe != null) {
                recipes.add(recipe);
                map.put(recipe.id, recipe);
            }
        }
    }

    public static List<InputStream> getAssetStream(AssetManager manager, String directory) {
        List<InputStream> streams = new ArrayList<>();
        String[] fileNames = getFileNames(manager, directory);

        for (String fileName : fileNames) {
            File file = new File(directory, fileName);
            try {
                InputStream stream = manager.open(file.getPath());
                if (stream != null) {
                    streams.add(stream);
                }
            } catch (IOException e) {
            }
        }
        return streams;
    }

    private static String[] getFileNames(AssetManager manager, String directory) {
        if (directory == null) {
            return new String[0];
        }

        try {
            return manager.list(directory);
        } catch (IOException e) {
            return new String[0];
        }
    }

    public Recipe getRecipe(String id) {
        return map.get(id);
    }
}
