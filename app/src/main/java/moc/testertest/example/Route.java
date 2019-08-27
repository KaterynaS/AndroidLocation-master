package moc.testertest.example;


import java.util.ArrayList;

public class Route {

    String routeName;
    ArrayList<StopInfo> stops;

    public Route(String routeName, ArrayList<StopInfo> stops) {
        this.routeName = routeName;
        this.stops = stops;
    }

    public Route(String routeName) {
        this.routeName = routeName;
        stops = new ArrayList<>();
    }

    public String getRouteName() {
        return routeName;
    }

    public ArrayList<StopInfo> getStops() {
        return stops;
    }

    public void addStopToRoute(StopInfo anotherStop)
    {
        stops.add(anotherStop);
    }
//
//    public void addStopToRoute(String stopName, double latitude, double longtitude)
//    {
//        stops.add(new StopInfo(stopName, latitude, longtitude));
//    }
}
