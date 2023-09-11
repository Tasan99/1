import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Iterator;

class tasan_ManageCarData implements ManageCarDataFunctions
{
    private final ArrayList<CarFunctions> car_List;
    private final PriorityQueue<CarFunctions> carListByTotalRange;
    private final PriorityQueue<CarFunctions> carListByRemainingRange;

    tasan_ManageCarData() {
        car_List = new ArrayList<>();
        carListByRemainingRange = new PriorityQueue<>(new TotalRangeComparator());
        carListByTotalRange = new PriorityQueue<>(new RemainingRangeComparator());


    }

    public void readData(String filename) {
        try {
            BufferedReader input = new BufferedReader(
            new InputStreamReader(new FileInputStream(filename)));
            String inn;
            while ((inn = input.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inn, "\t");
                if (st.countTokens() == 4) {
                    CarFunctions car = new tasan_Car(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                    car_List.add(car);
                    carListByRemainingRange.add(car);
                    carListByTotalRange.add(car);
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }

    public ArrayList<CarFunctions> getCarList() {
        ArrayList<CarFunctions> tempCarList = new ArrayList<>();
        for (int i = 0; i < car_List.size(); i++) {
            tempCarList.add(car_List.get(i));
        }
        return tempCarList;
    }

    public PriorityQueue<CarFunctions> getCarListByTotalRange()
	{
		PriorityQueue<CarFunctions> tempCarList = new PriorityQueue<>(new TotalRangeComparator());
		for(int i = 0; i < car_List.size(); i++){
			tempCarList.add(car_List.get(i));
		}
		return tempCarList;
	}

    public ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator()
	{
		Iterator<CarFunctions> itByTotalRange = carListByTotalRange.iterator();
		ArrayList<CarFunctions> list = new ArrayList<>();
		while( itByTotalRange.hasNext() ){
			list.add(itByTotalRange.next());
		}
		return list;
	}
    
    public PriorityQueue<CarFunctions> getCarListByRemainingRange()
	{
		PriorityQueue<CarFunctions> tempCarList = new PriorityQueue<>(new RemainingRangeComparator());
		for(int i = 0; i < car_List.size(); i++){
			tempCarList.add(car_List.get(i));
		}
		return tempCarList;
	}
	
    public ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator()
	{
		ArrayList<CarFunctions> list = new ArrayList<>();
		Iterator<CarFunctions> RemainingRange = carListByRemainingRange.iterator();
		while( RemainingRange.hasNext() ){
			list.add(RemainingRange.next());
		}
		return list;
	}

    public ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange)
	{
		ArrayList<String> list = new ArrayList<>();
		while( carListByTotalRange.size() > 0 ){
			CarFunctions car = carListByTotalRange.poll();
			if( (car.getTotalRangeInMiles() >= minTotalRange) && (car.getTotalRangeInMiles() <= maxTotalRange) ){
				String equalsCarIndex = "";
				String otherCarIndices = "";
				for( int i = 0; i < car_List.size(); i++ ){
					if( car_List.get(i).equals(car) ){equalsCarIndex = "   " + i;}
					if( car_List.get(i).getFuelEconomyInMilesPerGallon() == car.getFuelEconomyInMilesPerGallon() ){
						otherCarIndices = otherCarIndices+"   " + i;
					}
				}
				list.add(car.toString() + equalsCarIndex + otherCarIndices);
			}
		}
		for( int i = 0; i < car_List.size(); i++ ){
			carListByTotalRange.add(car_List.get(i));
		}
		
		return list;
	}

    public ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange)
	{
		ArrayList<String> list = new ArrayList<>();
		while( carListByRemainingRange.size() > 0 ){
			CarFunctions car = carListByRemainingRange.poll();
			if( (car.getRemainingRangeInMiles() >= minRemainingRange) && (car.getRemainingRangeInMiles() <= maxRemainingRange) ){
				String equalsCarIndex = "";
				String otherCarIndices = "";
				for( int i = 0; i < car_List.size(); i++ ){
					if( car_List.get(i).equals(car) ){equalsCarIndex = "   " + i;}
					if( car_List.get(i).getFuelEconomyInMilesPerGallon() == car.getFuelEconomyInMilesPerGallon() ){
						otherCarIndices = otherCarIndices+"   " + i;
					}
				}
				list.add(car.toString() + equalsCarIndex + otherCarIndices);
			}
		}
		for( int i = 0; i < car_List.size(); i++ ){
			carListByRemainingRange.add(car_List.get(i));
		}
		
		return list;
	}
}

