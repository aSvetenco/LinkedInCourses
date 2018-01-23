package sa.android_testing.recepiceapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sa.android_testing.recepiceapp.R;
import sa.android_testing.recepiceapp.data.local.RecipeStore;
import sa.android_testing.recepiceapp.data.model.Recipe;
import sa.android_testing.recepiceapp.ui.recipe.RecipeActivity;

import static sa.android_testing.recepiceapp.ui.recipe.RecipeActivity.KEY_ID;


public class RecipeRVAdapter extends RecyclerView.Adapter<RecipeVH> {

    private final RecipeStore store;

    public RecipeRVAdapter(RecipeStore store) {
        this.store = store;
    }

    @Override
    public RecipeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RecipeVH(view);
    }

    @Override
    public void onBindViewHolder(final RecipeVH holder, int position) {
        final Recipe recipe = store.recipes.get(position);
        holder.textView.setText(recipe.title);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.textView.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra(KEY_ID, recipe.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return store.recipes.size();
    }
}
