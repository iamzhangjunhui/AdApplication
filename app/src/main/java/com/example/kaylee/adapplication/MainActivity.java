package com.example.kaylee.adapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Object[] list = new Object[]{"dsada", "adssada", "dsada", R.drawable.a, "adssada", "dsada", "adssada", "dsada", "adssada", "dsada", "adssada", "dsada", "adssada", "dsada", "adssada"
            , "dsada", "adssada", "dsada", "adssada", R.mipmap.ic_launcher_round, "dsada", "adssada", "dsada", "adssada", "dsada", "adssada", "dsada", "adssada"};
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPos = linearLayoutManager.findFirstVisibleItemPosition();
                int endPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = firstPos; i <= endPosition; i++) {
                    View view = linearLayoutManager.findViewByPosition(i);
                    AdImageView adImageView = view.findViewById(R.id.image);
                    if (adImageView != null && adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(linearLayoutManager.getHeight()-view.getTop());
                    }
                }

            }
        });
    }

    class AdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_text, null);
                return new TextViewHolder(view);
            } else {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_image, null);
                return new ImageViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TextViewHolder) {
                ((TextViewHolder) holder).bindData(list[position] + "");
            } else {
                ((ImageViewHolder) holder).bindData((Integer) list[position]);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (list[position] instanceof String) {
                return 1;
            } else {
                return 2;
            }

        }

        @Override
        public int getItemCount() {
            return list.length;
        }

        class TextViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public TextViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text);
            }

            public void bindData(String data) {
                if (!TextUtils.isEmpty(data))
                    textView.setText(data);
            }
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
            }

            public void bindData(int data) {
                if (data != 0) {
                    imageView.setImageResource(data);
                }
            }
        }
    }
}
