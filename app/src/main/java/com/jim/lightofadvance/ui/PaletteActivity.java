package com.jim.lightofadvance.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jim.lightofadvance.R;

public class PaletteActivity extends AppCompatActivity {

    private LinearLayout root;
    private TextView text, text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        root = findViewById(R.id.root);
        text = findViewById(R.id.text);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        initPalette();
    }

    /**
     * 初始化调试版
     */
    private void initPalette() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);

        Palette p = createPaletteSync(bitmap);
        Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

        // Load default colors
        int backgroundColor = ContextCompat.getColor(PaletteActivity.this, R.color.colorAccent);
        int textColor = ContextCompat.getColor(PaletteActivity.this, R.color.colorAccent);
        int textColor1 = ContextCompat.getColor(PaletteActivity.this, R.color.colorAccent);
        int textColor2 = ContextCompat.getColor(PaletteActivity.this, R.color.colorAccent);

        // Check that the Vibrant swatch is available
        if (vibrantSwatch != null) {
            backgroundColor = vibrantSwatch.getRgb();
            //textColor = vibrantSwatch.getTitleTextColor();
            textColor1 = vibrantSwatch.getBodyTextColor();
        }

        //Set the toolbar background and text colors
        //root.setBackgroundColor(backgroundColor);
        text.setTextColor(backgroundColor);
        text1.setTextColor(textColor1);
        text2.setTextColor(textColor2);

    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }
}
