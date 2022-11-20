package com.example.sunflower_jm;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\b\u0010\u001a\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/sunflower_jm/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/example/sunflower_jm/OnItemLongClickListener;", "()V", "adapter", "Lcom/example/sunflower_jm/RecyclerViewAdapter;", "binding", "Lcom/example/sunflower_jm/databinding/ActivityMainBinding;", "db", "Lcom/example/sunflower_jm/db/AppDatabase;", "sunflowerDao", "Lcom/example/sunflower_jm/db/SunFlowerDao;", "sunflowerList", "Ljava/util/ArrayList;", "Lcom/example/sunflower_jm/db/SunFlowerEntity;", "Lkotlin/collections/ArrayList;", "deleteItem", "", "position", "", "getAllItemList", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLongClick", "onRestart", "setRecyclerView", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements com.example.sunflower_jm.OnItemLongClickListener {
    private com.example.sunflower_jm.databinding.ActivityMainBinding binding;
    private com.example.sunflower_jm.db.AppDatabase db;
    private com.example.sunflower_jm.db.SunFlowerDao sunflowerDao;
    private java.util.ArrayList<com.example.sunflower_jm.db.SunFlowerEntity> sunflowerList;
    private com.example.sunflower_jm.RecyclerViewAdapter adapter;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void getAllItemList() {
    }
    
    private final void setRecyclerView() {
    }
    
    @java.lang.Override()
    public void onLongClick(int position) {
    }
    
    private final void deleteItem(int position) {
    }
    
    @java.lang.Override()
    protected void onRestart() {
    }
}