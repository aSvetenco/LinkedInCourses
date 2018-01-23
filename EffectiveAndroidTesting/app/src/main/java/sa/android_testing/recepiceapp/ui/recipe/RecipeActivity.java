package sa.android_testing.recepiceapp.ui.recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sa.android_testing.recepiceapp.R;
import sa.android_testing.recepiceapp.data.local.RecipeStore;
import sa.android_testing.recepiceapp.data.model.Recipe;

import static android.view.View.GONE;


public class RecipeActivity extends AppCompatActivity {

    public static final String KEY_ID = "id_key";

    @Override
    protected void onCreate(Bundle safeInstanceState) {
        super.onCreate(safeInstanceState);
        setContentView(R.layout.activity_recipe);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        RecipeStore store = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        Recipe recipe = store.map.get(id);
        if (recipe == null) {
            title.setVisibility(GONE);
            description.setText(R.string.no_recipe_found);
        return;
        }
        title.setText(recipe.title);
        description.setText(recipe.descriptions);
    }

}
