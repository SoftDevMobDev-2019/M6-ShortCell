package au.edu.swin.sdmd.shortcell;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * This program draws an elementary cellular automata and shows it on the screen. It is a demo
 * of how AsyncTask can be used for short tasks. In this case, larger images should be handled
 * in a different manner.
 */

public class MainActivity extends AppCompatActivity {

    ImageView pic;
    ProgressBar pg;
    EditText etRule;
    TextInputLayout tilRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = findViewById(R.id.imageView);
        pg = findViewById(R.id.progressBar);
        etRule = findViewById(R.id.etRule);
        tilRule = findViewById(R.id.tilRule);


    }

    public void onClick(View v) {

        String input = etRule.getText().toString();
        if (input.equals("") || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 255) {
            tilRule.setError(getString(R.string.rule_range));
        } else {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            ElemCA ca = new ElemCA(100, 200);
            ca.setNumber(Integer.parseInt(input));

            // TODO #1 draw a bitmap of a elementary cellular automata

            // TODO #1a show the bitmap on screen

        }

    }

    // TODO #2 write an AsyncTask to process the bitmap and show on screen

    // TODO #2a add in an indeterminate progress bar (spinner)
}
