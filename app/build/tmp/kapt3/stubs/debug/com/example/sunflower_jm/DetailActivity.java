package com.example.sunflower_jm;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/sunflower_jm/DetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/sunflower_jm/databinding/DetailViewBinding;", "getBinding", "()Lcom/example/sunflower_jm/databinding/DetailViewBinding;", "setBinding", "(Lcom/example/sunflower_jm/databinding/DetailViewBinding;)V", "content", "", "db", "Lcom/example/sunflower_jm/db/AppDatabase;", "getDb", "()Lcom/example/sunflower_jm/db/AppDatabase;", "setDb", "(Lcom/example/sunflower_jm/db/AppDatabase;)V", "id", "", "sunFlowerDao", "Lcom/example/sunflower_jm/db/SunFlowerDao;", "getSunFlowerDao", "()Lcom/example/sunflower_jm/db/SunFlowerDao;", "setSunFlowerDao", "(Lcom/example/sunflower_jm/db/SunFlowerDao;)V", "title", "deleteItem", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DetailActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.sunflower_jm.databinding.DetailViewBinding binding;
    public com.example.sunflower_jm.db.SunFlowerDao sunFlowerDao;
    public com.example.sunflower_jm.db.AppDatabase db;
    private int id = 0;
    private java.lang.String title = "";
    private java.lang.String content = "";
    private java.util.HashMap _$_findViewCache;
    
    public DetailActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.sunflower_jm.databinding.DetailViewBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.databinding.DetailViewBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.sunflower_jm.db.SunFlowerDao getSunFlowerDao() {
        return null;
    }
    
    public final void setSunFlowerDao(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.db.SunFlowerDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.sunflower_jm.db.AppDatabase getDb() {
        return null;
    }
    
    public final void setDb(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.db.AppDatabase p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void deleteItem() {
    }
}