import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class tasan_Car implements CarFunctions
{
	private final String id;
	private final int fuel_economy_in_miles_per_gallon;
	private final int fuel_capacity_in_gallons;
	private double current_fuel_in_gallons;

	tasan_Car (String id, int fuelEconomyInMilesPerGallon, int fuelCapacityInGallons, double currentFuelInGallons)
	{
		this.id = id;
		this.fuel_economy_in_miles_per_gallon = fuelEconomyInMilesPerGallon;
		this.fuel_capacity_in_gallons = fuelCapacityInGallons;
		this.current_fuel_in_gallons = currentFuelInGallons;
	}

	public int getFuelEconomyInMilesPerGallon()
	{
		return fuel_economy_in_miles_per_gallon;
	}
	
	public int getFuelCapacityInGallons()
	{
		return fuel_capacity_in_gallons;
	}
	
	public double getCurrentFuelInGallons()
	{
		return current_fuel_in_gallons;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setCurrentFuelInGallons(double v)
	{
		current_fuel_in_gallons = v;
	}
	

	public String toString()
	{
		return getId() + "   " + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles();
	}
}
