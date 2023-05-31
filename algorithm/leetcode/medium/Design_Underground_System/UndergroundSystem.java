package algorithm.leetcode.medium.Design_Underground_System;

import java.util.*;

public class UndergroundSystem {
    /**
     * [내가 작성한 코드]
     * 체크인 정보를 저장할 checkInMap과 이동 경로에 대한 정보를 저장할 routeMap을 생성한다.
     * checkInMap에는 "사용자 ID"와 "출발역 이름 + 시간의 정보를 담은 문자열"을 담고,
     * routeMap에는 "출발역 + 도착역"과 "이전 traveling times의 누적치 + 횟수의 정보를 담은 배열"을 담는다.
     *
     * checkIn과 checkOut이 이루어질 때마다 두 Map의 정보를 갱신하고
     * getAverageTime에 대해서는 해당 route의 traveling times의 누적치 / 횟수의 값을 반환한다.
     *
     * 사실 기계적인 풀이에만 집중했을 뿐 실제 사용할 법한 코드는 아니다.
     *
     * Runtime : 109 ms (Beats 21%)
     * Memory : 54.2 MB (Beats 96.66%)
     */
    private final Map<Integer, String> checkInMap;
    private final Map<String, int[]> timeMap;
    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        timeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, stationName + " " + t);
    }

    public void checkOut(int id, String stationName, int t) {
        String[] inf = checkInMap.remove(id).split(" "); // remove(K)는 Key K에 해당하는 Value를 반환하고 Map에서 삭제한다. get과 remove를 따로 할 필요가 없다.
        String station = inf[0] + " " + stationName;
        int[] tArr = timeMap.getOrDefault(station, new int[2]);
        tArr[0] += t - Integer.parseInt(inf[1]);
        tArr[1] += 1;

        timeMap.put(station, tArr);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] tArr = timeMap.getOrDefault(startStation + " " + endStation,  new int[2]);

        return (double) tArr[0] / tArr[1];
    }

    /**
     * [객체지향적으로 작성한 코드]
     * 위의 코드와 비슷한 구성을 갖는 두 개의 Map을 사용했지만,
     * 각각 승객, 경로에 대한 정보를 담는 Passenger, Route 클래스를 활용하여 보다 객체지향적으로 작성한 코드이다.
     *
     * 처음 보는 사람도 한 눈에 코드의 구성과 의도를 알아보기 수월한 semantic한 코드라는 점에서 바람직하다.
     *
     * Runtime : 102 ms (Beats 63.72%)
     * Memory : 55.1 MB (Beats 76.37%)
     */
    private final Map<Integer, Passenger> currentPassengerMap;
    private final Map<String, Route> routeMap;

    public UndergroundSystem() {
        currentPassengerMap = new HashMap<>();
        timeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        if (!currentPassengerMap.containsKey(id)) {
            Passenger passenger = new Passenger(stationName, t);
            currentPassengerMap.put(id, passenger);
        }
    }

    public void checkOut(int id, String stationName, int t) {
        if (currentPassengerMap.containsKey(id)) {
            Passenger passenger = currentPassengerMap.get(id);
            passenger.checkout(stationName, t);
            String routeKey = passenger.checkinLocation + "," + passenger.checkoutLocation;
            Route route = timeMap.getOrDefault(routeKey, new Route(passenger.checkinLocation, passenger.checkoutLocation));
            route.addTrip(passenger.checkinTime, passenger.checkoutTime);
            timeMap.put(routeKey, route);
            currentPassengerMap.remove(id);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        return timeMap.get(startStation + "," + endStation).getAverageTime();
    }

    static class Passenger {
        int checkinTime;
        int checkoutTime;
        String checkinLocation;
        String checkoutLocation;

        public Passenger(String checkinLocation, int checkinTime) {
            this.checkinLocation = checkinLocation;
            this.checkinTime = checkinTime;
        }

        void checkout(String checkoutLocation, int checkoutTime) {
            this.checkoutLocation = checkoutLocation;
            this.checkoutTime = checkoutTime;
        }

    }

    static class Route {
        String startStation;
        String endStation;
        int totalNumberOfTrips;
        long totalTimeSpentInTrips;

        public Route(String startStation, String endStation) {
            this.startStation = startStation;
            this.endStation = endStation;
        }

        double getAverageTime() {
            return (double) totalTimeSpentInTrips / totalNumberOfTrips;
        }

        void addTrip(int startTime, int endTime) {
            totalTimeSpentInTrips += endTime - startTime;
            totalNumberOfTrips++;
        }
    }

}
