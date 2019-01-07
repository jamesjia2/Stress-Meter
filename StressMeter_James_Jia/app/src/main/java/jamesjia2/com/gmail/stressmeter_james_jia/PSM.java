package jamesjia2.com.gmail.stressmeter_james_jia;

//import edu.dartmouth.cs.stressmeter.R;

/**
 * Created by ruiwang on 8/23/15.
 */
public class PSM {

    //returns a 4x4 grid filled with image pointers -
    //returns a different grid based on the received parameter
    public static int[] getGridById(int id) {
        switch (id) {
            case 1:
                return getGrid1();
            case 2:
                return getGrid2();
            case 3:
                return getGrid3();
        }

        return null;
    }

    //returns the first grid of images
    public static int[] getGrid1() {
        int[] grid = new int[16];

        grid[0] = R.drawable.psm_talking_on_phone2;
        grid[1] = R.drawable.psm_stressed_person;
        grid[2] = R.drawable.psm_stressed_person12;
        grid[3] = R.drawable.psm_lonely;
        grid[4] = R.drawable.psm_gambling4;
        grid[5] = R.drawable.psm_clutter3;
        grid[6] = R.drawable.psm_reading_in_bed2;
        grid[7] = R.drawable.psm_stressed_person4;
        grid[8] = R.drawable.psm_lake3;
        grid[9] = R.drawable.psm_cat;
        grid[10] = R.drawable.psm_puppy3;
        grid[11] = R.drawable.psm_neutral_person2;
        grid[12] = R.drawable.psm_beach3;
        grid[13] = R.drawable.psm_peaceful_person;
        grid[14] = R.drawable.psm_alarm_clock2;
        grid[15] = R.drawable.psm_sticky_notes2;

        return grid;
    }

    //returns the first score grid
    public static int[] getScoreGrid1(){
        int[] grid = new int[16];

        grid[0] = 6;
        grid[1] = 8;
        grid[2] = 14;
        grid[3] = 16;
        grid[4] = 5;
        grid[5] = 7;
        grid[6] = 13;
        grid[7] = 15;
        grid[8] = 2;
        grid[9] = 4;
        grid[10] = 10;
        grid[11] = 12;
        grid[12] = 1;
        grid[13] = 3;
        grid[14] = 9;
        grid[15] = 11;

        return grid;
    }

    //second image grid
    public static int[] getGrid2() {
        int[] grid = new int[16];

        grid[0] = R.drawable.psm_anxious;
        grid[1] = R.drawable.psm_hiking3;
        grid[2] = R.drawable.psm_stressed_person3;
        grid[3] = R.drawable.psm_lonely2;
        grid[4] = R.drawable.psm_dog_sleeping;
        grid[5] = R.drawable.psm_running4;
        grid[6] = R.drawable.psm_alarm_clock;
        grid[7] = R.drawable.psm_headache;
        grid[8] = R.drawable.psm_baby_sleeping;
        grid[9] = R.drawable.psm_puppy;
        grid[10] = R.drawable.psm_stressed_cat;
        grid[11] = R.drawable.psm_angry_face;
        grid[12] = R.drawable.psm_bar;
        grid[13] = R.drawable.psm_running3;
        grid[14] = R.drawable.psm_neutral_child;
        grid[15] = R.drawable.psm_headache2;

        return grid;
    }

    //second score grid
    public static int[] getScoreGrid2(){
        int[] grid = new int[16];

        grid[0] = 6;
        grid[1] = 8;
        grid[2] = 14;
        grid[3] = 16;
        grid[4] = 5;
        grid[5] = 7;
        grid[6] = 13;
        grid[7] = 15;
        grid[8] = 2;
        grid[9] = 4;
        grid[10] = 10;
        grid[11] = 12;
        grid[12] = 1;
        grid[13] = 3;
        grid[14] = 9;
        grid[15] = 11;

        return grid;
    }

    //third image grid
    public static int[] getGrid3() {
        int[] grid = new int[16];

        grid[0] = R.drawable.psm_mountains11;
        grid[1] = R.drawable.psm_wine3;
        grid[2] = R.drawable.psm_barbed_wire2;
        grid[3] = R.drawable.psm_clutter;
        grid[4] = R.drawable.psm_blue_drop;
        grid[5] = R.drawable.psm_to_do_list;
        grid[6] = R.drawable.psm_stressed_person7;
        grid[7] = R.drawable.psm_stressed_person6;
        grid[8] = R.drawable.psm_yoga4;
        grid[9] = R.drawable.psm_bird3;
        grid[10] = R.drawable.psm_stressed_person8;
        grid[11] = R.drawable.psm_exam4;
        grid[12] = R.drawable.psm_kettle;
        grid[13] = R.drawable.psm_lawn_chairs3;
        grid[14] = R.drawable.psm_to_do_list3;
        grid[15] = R.drawable.psm_work4;

        return grid;
    }

    //third score grid
    public static int[] getScoreGrid3(){
        int[] grid = new int[16];

        grid[0] = 6;
        grid[1] = 8;
        grid[2] = 14;
        grid[3] = 16;
        grid[4] = 5;
        grid[5] = 7;
        grid[6] = 13;
        grid[7] = 15;
        grid[8] = 2;
        grid[9] = 4;
        grid[10] = 10;
        grid[11] = 12;
        grid[12] = 1;
        grid[13] = 3;
        grid[14] = 9;
        grid[15] = 11;

        return grid;
    }
}
