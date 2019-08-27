package moc.testertest.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BeeperActivity extends AppCompatActivity {

    TextView nameOfRouteTextview;
    TextView allStopsTextview;
    TextView previousStopTextview;
    TextView currentStopTextview;
    TextView nextStopTextview;
    TextView currentCoordinatesTextview;
    private ArrayList<Route> listOfRouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beeper);

        nameOfRouteTextview = findViewById(R.id.name_of_the_chosen_route_textview);
        allStopsTextview = findViewById(R.id.all_stops_textview);
        previousStopTextview = findViewById(R.id.previous_stop_info_textview);
        currentStopTextview = findViewById(R.id.current_stop_info_textview);
        nextStopTextview = findViewById(R.id.next_stop_info_textview);
        currentCoordinatesTextview = findViewById(R.id.current_coordinates_textview);

        populateListOfRoutes();

        Intent intent = getIntent();
        String chosenRouteName = intent.getStringExtra("selectedRoute");
        nameOfRouteTextview.setText(chosenRouteName);

        //display corresponding stops

        Route chosenRoute = whichRouteChosen(chosenRouteName);
        String allStopsString = showAllStops(chosenRoute);
        allStopsTextview.setText(allStopsString);

    }

    //to be developed

    private String showAllStops(Route chosenRoute) {
        String stopNames = "";

        ArrayList<StopInfo> stops = chosenRoute.getStops();

        for (int i = 0; i < stops.size(); i++) {

            if(i < stops.size() - 1)
            {
                stopNames = stopNames + stops.get(i).name + " => ";
            }
            else
            {
                stopNames = stopNames + stops.get(i).name;
            }
        }

        return stopNames;
    }


    private Route whichRouteChosen(String selectedRouteName)
    {
        Route selected = null;
        for (int i = 0; i < listOfRouts.size(); i++)
        {
            String iThRouteName = listOfRouts.get(i).routeName;
            if(selectedRouteName.equals(iThRouteName))
            {
                selected = listOfRouts.get(i);
            }

        }
        return selected;
    }

    private void populateListOfRoutes() {
        StopInfo homeStop = new StopInfo("Home",  37.9088978, -122.046608);
        StopInfo JMstatueStop = new StopInfo("John Muir Statue",  37.9118638, -122.0404740);
        StopInfo SkazkaStop = new StopInfo("Skazka Club",  37.9105854, -122.0386769);
        StopInfo stationStop = new StopInfo("Bus Station",  37.9102259, -122.0437567);
        StopInfo tampicoMontigoIntercStop = new StopInfo("Tampico Montego Interc",  37.9101362, -122.0454525);
        StopInfo trailGateStop = new StopInfo("Trail Gate",  37.9078039, -122.0395546);
        StopInfo tampicoMontezumaStop = new StopInfo("Tampico Montezuma",  37.90745898, -122.0412840);

        Route HomeJMstatue = new Route("HomeJMstatue");
        HomeJMstatue.addStopToRoute(homeStop);
        HomeJMstatue.addStopToRoute(tampicoMontigoIntercStop);
        HomeJMstatue.addStopToRoute(stationStop);
        HomeJMstatue.addStopToRoute(JMstatueStop);

        Route JMstatueHome = new Route("JMstatueHome");

        Route JMstatueSkazka = new Route("JMstatueSkazka");
        JMstatueSkazka.addStopToRoute(JMstatueStop);
        JMstatueSkazka.addStopToRoute(SkazkaStop);


        Route SkazkaJMstatue = new Route("SkazkaJMstatue");


        Route SkazkaHome = new Route("SkazkaHome");
        SkazkaHome.addStopToRoute(SkazkaStop);
        SkazkaHome.addStopToRoute(trailGateStop);
        SkazkaHome.addStopToRoute(tampicoMontezumaStop);
        SkazkaHome.addStopToRoute(homeStop);

        Route HomeSkazka = new Route("HomeSkazka");

        listOfRouts = new ArrayList<Route>();
        listOfRouts.add(HomeJMstatue);
        listOfRouts.add(JMstatueSkazka);
        listOfRouts.add(SkazkaHome);
    }
}
