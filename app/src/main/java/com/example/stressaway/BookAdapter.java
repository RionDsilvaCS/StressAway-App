package com.example.stressaway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.viewHolder> {

    private final Context context;
    private final ArrayList<BookCard> BookCardArrayList;
    private int flag=0;

    public BookAdapter(Context context, ArrayList<BookCard> BookCardArrayList) {
        this.context = context;
        this.BookCardArrayList = BookCardArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_card, parent, false);
        return new viewHolder(view);
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        private final TextView bookName;
        private final TextView authorName;
        CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.book_name);
            authorName = itemView.findViewById(R.id.author_name);
            cardView = itemView.findViewById(R.id.book_card);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BookCard model = BookCardArrayList.get(position);
        holder.bookName.setText(model.getBookName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }


    @Override
    public int getItemCount() {

        return BookCardArrayList.size();
    }
}