import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class tasan_ManageCarData implements ManageCarDataFunctions {
    private final ArrayList<CarFunctions> carList;
    private final PriorityQueue<CarFunctions> carListByTotalRange;
    private final PriorityQueue<CarFunctions> carListByRemainingRange;

    tasan_ManageCarData() {
        carList = new ArrayList<>();
        carListByRemainingRange = new PriorityQueue<>(new TotalRangeComparator());
        carListByTotalRange = new PriorityQueue<>(new RemainingRangeComparator());


    }

    public void readData(String filename) {
        try {
            BufferedReader input = new BufferedReader(
            new InputStreamReader(new FileInputStream(filename)));
            String inn;
            while ((inn = input.readLine()) != null) {
                java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
                if (st.countTokens() == 4) {
                    CarFunctions car = new tasan_Car(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                    carList.add(car);
                    carListByRemainingRange.add(car);
                    carListByTotalRange.add(car);
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }

    public ArrayList<CarFunctions> getCarList() {
        ArrayList<CarFunctions> tempCarList = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            tempCarList.add(carList.get(i));
        }
        return tempCarList;
    }
}



