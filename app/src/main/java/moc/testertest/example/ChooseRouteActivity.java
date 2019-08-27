package moc.testertest.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ChooseRouteActivity extends AppCompatActivity {

    Spinner routsSpinner;
    Button goButton;
    List<Route> listOfRouts;
    Button startBeeperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_route);

        addItemsOnRoutsSpinner();
        addListenerOnSpinnerItemSelection();

        goButton = findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRouteDetails();
            }
        });


        startBeeperButton = findViewById(R.id.start_beeper_button);
        startBeeperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBeeper();
            }
        });
    }


    public void addItemsOnRoutsSpinner() {

        routsSpinner = (Spinner) findViewById(R.id.choose_route_spinner);
//
        StopInfo homeStop = new StopInfo("Home",  37.9088978, -122.046608);
        StopInfo JMstatueStop = new StopInfo("Home",  37.9118638, -122.0404740);
        StopInfo SkazkaStop = new StopInfo("Home",  37.9105854, -122.0386769);
        StopInfo stationStop = new StopInfo("Home",  37.9102259, -122.0437567);
        StopInfo tampicoMontigoIntercStop = new StopInfo("Home",  37.9101362, -122.0454525);
        StopInfo trailGateStop = new StopInfo("Home",  37.9078039, -122.0395546);
        StopInfo tampicoMontezumaStop = new StopInfo("Home",  37.90745898, -122.0412840);

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

        ArrayList<String> listOfRoutNames = new ArrayList<>(listOfRouts.size());

        for (int i = 0; i < listOfRouts.size(); i++) {
            listOfRoutNames.add(listOfRouts.get(i).routeName);

        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listOfRoutNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routsSpinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        routsSpinner = (Spinner) findViewById(R.id.choose_route_spinner);
        routsSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    public void goToRouteDetails()
    {
        Intent routeDetails = new Intent(ChooseRouteActivity.this, RouteDetailsActivity.class);
        String selectedRoute = routsSpinner.getSelectedItem().toString();

        Route routeChosen = whichRouteChosen(selectedRoute);


        //send route name with intent
        routeDetails.putExtra("selectedRoute", selectedRoute);

        //todo send route object (name and stops) or use singleton

        startActivity(routeDetails);
    }



    public void startBeeper()
    {
        Intent routeDetails = new Intent(ChooseRouteActivity.this, BeeperActivity.class);
        String selectedRoute = routsSpinner.getSelectedItem().toString();

        //send route name with intent
        routeDetails.putExtra("selectedRoute", selectedRoute);

        //todo send route object (name and stops) or use singleton

        startActivity(routeDetails);
    }

    private Route whichRouteChosen(String selectedRoute)
    {
        Route selected = null;
        for (int i = 0; i < listOfRouts.size(); i++)
        {
            String iThRouteName = listOfRouts.get(i).routeName;
            if(selectedRoute.equals(iThRouteName))
            {
                selected = listOfRouts.get(i);
            }

        }
        return selected;
    }
}
