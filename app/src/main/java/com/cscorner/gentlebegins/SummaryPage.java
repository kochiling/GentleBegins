package com.cscorner.gentlebegins;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SummaryPage extends AppCompatActivity {
    private GraphView graphView;
    private LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        graphView = findViewById(R.id.graph);
        series = new LineGraphSeries<>();

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("https://gentlebegins-default-rtdb.firebaseio.com/"); // Replace with your Firebase data path

        // Read and continuously update data from Firebase
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle updated data
                series.resetData(getDataPointsFromSnapshot(dataSnapshot));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors (e.g., network issues)
                // You can log or show an error message here
            }
        });

        graphView.addSeries(series);
    }

    private DataPoint[] getDataPointsFromSnapshot(DataSnapshot dataSnapshot) {
        int dataCount = (int) dataSnapshot.getChildrenCount();
        DataPoint[] dataPoints = new DataPoint[dataCount];

        int i = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            // Assuming your data structure has "x" and "y" fields, adjust these accordingly
            double x = Double.parseDouble(child.child("x").getValue().toString());
            double y = Double.parseDouble(child.child("y").getValue().toString());
            dataPoints[i] = new DataPoint(x, y);
            i++;
        }

        return dataPoints;
    }
}