package jamesjia2.com.gmail.stressmeter_james_jia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SaveImage extends AppCompatActivity {

    String tag = "jj";
    int score = 0;
    int time = 0;
    int imageId = 0;
    int blank = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image);

        //get intent and score and time info
        Intent intent = getIntent();
        score = intent.getIntExtra("score", blank);
        imageId = intent.getIntExtra("image", blank);
        time = (int) intent.getLongExtra("time", blank);

        //Draw inflated image
        Drawable drawable = getResources().getDrawable(imageId);
        ImageView image = (ImageView)findViewById(R.id.bigImage);
        image.setImageDrawable(drawable);


    }


    //when submit button is clicked
    public void onSubmitClicked(View view) throws IOException {

        //convert ints to strings for writing
        String scoreString = String.valueOf(score);
        String timeString = String.valueOf(time);

        //set up for csv writer
        String path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String name = "Res.csv";
        String completePath = path + File.separator + name;
        File f = new File(completePath);


        try {
            //write in .csv format
            String data = scoreString+","+timeString+"\n";

            // if file doesnt exists, then create it
            if (!f.exists()) {
                f.createNewFile();
            }
            else {
                FileWriter fw = new FileWriter(completePath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(data);
                bw.close();

                Log.d(tag, "hi");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.finishAffinity();
    }

    //finish when cancel is closed
    public void onCancelClicked(View view) throws IOException {
        finish();
    }
}
