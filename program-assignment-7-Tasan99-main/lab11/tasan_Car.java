import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class tasan_Car implements CarFunctions
{

	private final String id;
	private final int fuel_economy_in_miles_per_gallon;
	private final int fuel_capacity_in_gallons;
	private double current_fuel_in_gallons;

	tasan_Car (String id,int fuel_economy_in_miles_per_gallon,int fuel_capacity_in_gallons, double current_fuel_in_gallons) {
		this.id=id;
		this.fuel_economy_in_miles_per_gallon=fuel_economy_in_miles_per_gallon;
		this.fuel_capacity_in_gallons=fuel_capacity_in_gallons;
		this.current_fuel_in_gallons=current_fuel_in_gallons;

	}
	public String toString()
	{
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles();
	}
}
