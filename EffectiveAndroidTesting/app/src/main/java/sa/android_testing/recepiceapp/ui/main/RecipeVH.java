package sa.android_testing.recepiceapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class RecipeVH extends RecyclerView.ViewHolder {
    public final TextView textView;

    public RecipeVH(View itemView) {
        super(itemView);
        textView = (TextView)itemView;
    }
}
