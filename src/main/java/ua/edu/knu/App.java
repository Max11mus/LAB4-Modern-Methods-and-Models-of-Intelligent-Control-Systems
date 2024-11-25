package ua.edu.knu;

import ua.edu.knu.fuzzy.TrafficLight;

public class App
{
    public static void main( String[] args ){

        TrafficLight trafficLight = new TrafficLight();
        System.out.println("GreenTime - 23");
        trafficLight.setGreenTime(23);

        System.out.println("CarsNS - 77");
        trafficLight.setCarsNS(77);

        System.out.println("CarsWS - 89");
        trafficLight.setCarsWE(89);

        System.out.println("DeltaGreenTime - " + trafficLight.getDeltaGreenTime());

    }
}
