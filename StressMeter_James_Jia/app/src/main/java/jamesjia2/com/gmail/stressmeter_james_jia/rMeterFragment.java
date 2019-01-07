package jamesjia2.com.gmail.stressmeter_james_jia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import java.util.Random;

//get access to the vibrator and media player in main
import static jamesjia2.com.gmail.stressmeter_james_jia.MainActivity.player;
import static jamesjia2.com.gmail.stressmeter_james_jia.MainActivity.vib;


public class rMeterFragment extends Fragment {

    int count;
    String tag = "jj";
    String scoreTag = "score";
    String imageTag = "image";
    String timeTag = "time";

    void rMeterFragment() {
        count = (int) Math.random()*3;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get view
        final View view = inflater.inflate(R.layout.fragment_meter, container, false);

        ///get gridview and initialize image adapter
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        final ImageAdapter adapter = new ImageAdapter(getActivity());

        //put in a specified set of images into the image adapter
        adapter.setImage(count);
        //bind adapter and gridview together
        gridview.setAdapter(adapter);

        //behavior when grid image is clicked - start SaveImage activity
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //turn off vibrator and ringtone
                vib.cancel();
                player.stop();

                //get time and score
                int score = adapter.scoreGrid[position];
                int imageId = adapter.imageGrid[position];
                Long time = System.currentTimeMillis()/1000;

                //pass time and score to start activity
                Intent s = new Intent(getActivity(), SaveImage.class);
                s.putExtra(scoreTag, score);
                s.putExtra(imageTag, imageId);
                s.putExtra(timeTag, time);
                startActivity(s);
            }
        });

        //set up more images button
        Button startButton = (Button) view.findViewById(R.id.more_images);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //turn off vibrator and ringtone
                vib.cancel();
                player.stop();

                //make so count is always between 1 and 3
                count = (count+1)%3;
                adapter.setImage(count);

                //call this to update the gridview UI
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
