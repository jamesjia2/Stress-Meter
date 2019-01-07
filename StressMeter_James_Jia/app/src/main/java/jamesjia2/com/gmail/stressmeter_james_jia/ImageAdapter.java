package jamesjia2.com.gmail.stressmeter_james_jia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private PSM psm;
    int[] imageGrid;
    int[] scoreGrid;

    //initialize the image and score grid with the first image/score grids
    public ImageAdapter(Context c) {
        mContext = c;
        //references to our images
        imageGrid = psm.getGrid1();
        scoreGrid = psm.getScoreGrid1();

    }

    //sets the imagegrid as either the first, second, or third set of images
    public void setImage(int gridNum){

        //first grid
        if (gridNum == 1){
            imageGrid = psm.getGrid1();
            scoreGrid = psm.getScoreGrid1();
        }

        //second grid
        if (gridNum == 2){
            imageGrid = psm.getGrid2();
            scoreGrid = psm.getScoreGrid2();
        }

        //third grid
        if (gridNum == 0){
            imageGrid = psm.getGrid3();
            scoreGrid = psm.getScoreGrid3();
        }
    }

    public int getCount() {
        return imageGrid.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    //create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        //if not already there
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(335, 335));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        //if existing, update
        else{
            imageView = (ImageView) convertView;
        }

        //update the imageview with the current image
        imageView.setImageResource(imageGrid[position]);
        return imageView;
    }

}