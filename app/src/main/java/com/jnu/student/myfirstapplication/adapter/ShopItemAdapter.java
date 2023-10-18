package com.jnu.student.myfirstapplication.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jnu.student.myfirstapplication.R;
import com.jnu.student.myfirstapplication.bean.Book;

import java.util.ArrayList;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {

    public ArrayList<Book> getListBooks() {
        return ListBooks;
    }

    private final ArrayList<Book> ListBooks;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final TextView textViewName;
        private final ImageView imageViewItem;

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("具体操作");
            menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
            menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
            menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
        }

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textViewName = view.findViewById(R.id.text_view_book_title);
            imageViewItem = view.findViewById(R.id.image_view_book_cover);
            view.setOnCreateContextMenuListener(this);


        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public ImageView getImageViewItem() {
            return imageViewItem;
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param books String[] containing the data to populate views to be used
     *              by RecyclerView.
     */
    public ShopItemAdapter(ArrayList<Book> books) {
        ListBooks = books;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shop_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextViewName().setText(ListBooks.get(position).getTitle());
        viewHolder.getImageViewItem().setImageResource(ListBooks.get(position).getCoverResourceId());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ListBooks.size();
    }
}