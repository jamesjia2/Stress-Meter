package jamesjia2.com.gmail.stressmeter_james_jia;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


public class ResultsFragment extends Fragment {

    TableLayout table;
    LineChartView chart;
    List<PointValue> pValues = new ArrayList<PointValue>();
    List<Line> lines = new ArrayList<Line>();

    void ResultsFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get view
        final View view = inflater.inflate(R.layout.fragment_results, container, false);

        //make table
        table = (TableLayout) view.findViewById(R.id.main_table);
        try {
            makeTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //make graph
        chart = (LineChartView) view.findViewById(R.id.linechart);
        try {
            makeGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    //creates table and fills it in with score and time values
    public void makeTable() throws IOException {

        //HEADER
        TableRow headerRow = new TableRow(getActivity());

        //create time header and format
        TextView timeHeader = new TextView(getActivity());
        timeHeader.setText("Time");
        timeHeader.setTextColor(Color.WHITE);
        headerRow.addView(timeHeader);

        //create stress header and format
        TextView stressHeader = new TextView(getActivity());
        stressHeader.setText("Stress");
        stressHeader.setTextColor(Color.WHITE);
        headerRow.addView(stressHeader);

        //format the row background and add the row to the table
        headerRow.setBackgroundColor(Color.BLACK);
        table.addView(headerRow);

        //BODY - start by reading the csv line by line
        String path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String name = "Res.csv";
        String completePath = path + File.separator + name;

        //use buffered reader to read csv
        String line;
        String[] data;
        FileInputStream fis = new FileInputStream(completePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        //read until the end
        while ((line = reader.readLine()) != null) {

            //parse lines
            data = line.split(",");

            //initialize new table row object
            TableRow bodyRow = new TableRow(getActivity());

            //add time and format
            TextView timeValue = new TextView(getActivity());
            timeValue.setText(data[1]);
            timeValue.setTextColor(Color.BLACK);
            bodyRow.addView(timeValue);

            //add score and format
            TextView scoreValue = new TextView(getActivity());
            scoreValue.setText(data[0]);
            scoreValue.setTextColor(Color.BLACK);
            bodyRow.addView(scoreValue);

            //put border around body rows
            GradientDrawable bodyBorders = new GradientDrawable();
            bodyBorders.setStroke(2, Color.BLACK);
            bodyRow.setBackgroundDrawable(bodyBorders);

            //add the body row to the table
            table.addView(bodyRow);
        }

        reader.close();

    }

    //creates graph drawing and plots the point as well as a connecting linegraph
    public void makeGraph() throws IOException {

        //Start by reading the csv file line by line
        String path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String name = "Res.csv";
        String completePath = path + File.separator + name;

        //use buffered reader to read csv
        String line;
        String[] data;
        int count = 1;
        FileInputStream fis = new FileInputStream(completePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        //read until the end
        while ((line = reader.readLine()) != null) {

            //parse the lines
            data = line.split(",");

            //turn score back into int, make pointvalue and add to arraylist
            int scoreData = Integer.parseInt(data[0]);
            pValues.add(new PointValue(count, scoreData));

            //increment count - this is used to track the instance number (# rows in .csv file)
            count++;
        }

        //make a line with the set of pointvalues
        Line graphLine = new Line(pValues);
        //formatting
        graphLine.setColor(Color.BLUE);
        graphLine.setFilled(true);

        //put it in an arraylist of lines for the chart to use
        lines.add(graphLine);

        //intialize axis, then format their labels
        Axis x = new Axis();
        Axis y = new Axis();
        x.setName("Instances");
        y.setName("Stress Level");

        //initialize the chart data object for the graph
        LineChartData chartData = new LineChartData();

        //use the lines array as well as the x and y axis declared previously
        chartData.setAxisXBottom(x);
        chartData.setAxisYLeft(y);
        chartData.setLines(lines);

        chart.setLineChartData(chartData);

        //format background color
        chart.setBackgroundColor(Color.GRAY);
    }
}
