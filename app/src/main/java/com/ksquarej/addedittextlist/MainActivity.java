package com.ksquarej.addedittextlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaDrm;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> text_contents = new ArrayList<>();
    private LinearLayout chatLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ScrollView scrollview = findViewById(R.id.chatScrollView);
        scrollview.post(() -> scrollview.fullScroll(ScrollView.FOCUS_DOWN));

        chatLayout = findViewById(R.id.chatLayout);

        Button add_text_btn = findViewById(R.id.add_text_btn);
        add_text_btn.setOnClickListener(v->{
            Toast.makeText(this,String.valueOf(this.text_contents.size()) , Toast.LENGTH_SHORT).show();
            //for printing text array
            String temp_pr="";
            for (String data: this.text_contents)
                temp_pr+=(", "+data);
            Log.i("text_contents",temp_pr);

            this.text_contents.add("");
            RelativeLayout relativeLayout = getBoxLayout();
            relativeLayout.setFocusableInTouchMode(true);
            chatLayout.addView(relativeLayout);
            TextView index_pre = relativeLayout.findViewById(R.id.index_preserve);
            index_pre.setText(String.valueOf(this.text_contents.size()-1));
            EditText temp_text = relativeLayout.findViewById(R.id.add_text_box);
            temp_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!temp_text.getText().toString().equals("")) {
                        updateDataArray(Integer.valueOf(index_pre.getText().toString()),temp_text.getText().toString());
                    }
                }
            });

        });

    }

    void updateDataArray(int index, String data){
        this.text_contents.set(index, data);
    }

    RelativeLayout getBoxLayout() {
        LayoutInflater inflater = LayoutInflater.from(this);
        return (RelativeLayout) inflater.inflate(R.layout.design_of_text_box, null);
    }
}