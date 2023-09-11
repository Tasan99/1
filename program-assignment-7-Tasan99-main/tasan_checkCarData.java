import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

class tasan_checkCarData
{
	public static void main(String[] args)
	{
		if( args.length != 6)
		{
			System.out.println("format testCarData \"input file\" \"min total range\" \"max total range\" \"min remaining range\" \"max remaining range\"");
			System.exit(0);
		}
		
		// get the command line arguments
		String filename = args[0];
		String outputFilename = args[1];
		double minTotalRange = Double.parseDouble(args[2]);
		double maxTotalRange = Double.parseDouble(args[3]);
		double minRemainingRange = Double.parseDouble(args[4]);
		double maxRemainingRange = Double.parseDouble(args[5]);
		
		// create a ManageCarData object
		ManageCarDataFunctions manageCarData = new tasan_ManageCarData();
		
		// read the car definitions from the input file
		manageCarData.readData(filename);

		// Path fileName = Path.of(outputFilename);
		try {
			FileWriter Writer = new FileWriter((outputFilename));
			System.out.println("carList");
			Writer.write("carList\n");
		ArrayList<CarFunctions> carList = manageCarData.getCarList();
		for( CarFunctions c : carList )
		{
			System.out.println(c);
			try {
				Writer.write(String.valueOf(c.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		Writer.write("\n");

		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator");
		Writer.write("carListByTotalRange iterator\n");
		ArrayList<CarFunctions> carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		for( CarFunctions c : carListByTotalRangeByIterator )
		{
			System.out.println(c);
			try {
				Writer.write(String.valueOf(c.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		Writer.write("\n");		
		
		// get an iterator for the PriorityQueue ordered by total range and print them out
		System.out.println("carListByTotalRange iterator local");
		Writer.write("carListByTotalRange iterator local\n");
		Iterator<CarFunctions> itByTotalRange = manageCarData.getCarListByTotalRange().iterator();
		while( itByTotalRange.hasNext() )
		{
			String a = itByTotalRange.next().toString();
			System.out.println(a);
			Writer.write(String.valueOf(a));
			Writer.write("\n");		
		}
		System.out.println();
		Writer.write("\n");
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator");
		Writer.write("carListByRemainingRange iterator\n");
		ArrayList<CarFunctions> carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		for( CarFunctions c : carListByRemainingRangeByIterator )
		{
			System.out.println(c);
			try {
				Writer.write(String.valueOf(c.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		Writer.write("\n");
		
		// get an iterator for the PriorityQueue ordered by remaining range and print them out
		System.out.println("carListByRemainingRange iterator local");
		Writer.write("carListByRemainingRange iterator local\n");
		Iterator<CarFunctions> itByRemaininglRange = manageCarData.getCarListByRemainingRange().iterator();
		while( itByRemaininglRange.hasNext() )
		{
			String a = itByRemaininglRange.next().toString();
			System.out.println(a);
			Writer.write(String.valueOf(a));
			Writer.write("\n");
		}
		System.out.println();
		Writer.write("\n");
		
		// get the list of cars stored in the PriroityQueue ordered by total range having total range [minTotalRange, maxTotalRange]
		System.out.println("carListByTotalRange.poll().getTotalRangeInMiles() in [" + minTotalRange + "," + maxTotalRange + "]");
		Writer.write("carListByTotalRange.poll().getTotalRangeInMiles() in [" + minTotalRange + "," + maxTotalRange + "]\n");
		ArrayList<String> carListByTotalRangeByPoll = manageCarData.getCarListByTotalRangeViaPoll(minTotalRange, maxTotalRange);
		for( String s : carListByTotalRangeByPoll )
		{
			System.out.println(s);
			try {
				Writer.write(String.valueOf(s.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		Writer.write("\n");
		
		// get the list of cars stored in the PriroityQueue ordered by remaining range having total range [minRemainingRange, maxRemainingRange]
		System.out.println("carListByTotalRange.poll().getRemainingRangeInMiles() in [" + minRemainingRange + "," + maxRemainingRange + "]");
		Writer.write("carListByTotalRange.poll().getRemainingRangeInMiles() in [" + minRemainingRange + "," + maxRemainingRange + "]\n");
		ArrayList<String> carListByRemainingRangeByPoll = manageCarData.getCarListByRemainingRangeViaPoll(minRemainingRange, maxRemainingRange);
		for( String s : carListByRemainingRangeByPoll )
		{
			System.out.println(s);
			try {
				Writer.write(String.valueOf(s.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		Writer.write("\n");
		
		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator (if empty, you didn't refill carListByTotalRange after polling all of the elements)");
		Writer.write("carListByTotalRange iterator (if empty, you didn't refill carListByTotalRange after polling all of the elements)\n");
		carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		for( CarFunctions c : carListByTotalRangeByIterator )
		{
			System.out.println(c);
			try {
				Writer.write(String.valueOf(c.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		Writer.write("\n");
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator (if empty, you didn't refill carListByRemainingRange after polling all of the elements)");
		Writer.write("carListByRemainingRange iterator (if empty, you didn't refill carListByRemainingRange after polling all of the elements)\n");
		carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		for( CarFunctions c : carListByRemainingRangeByIterator )
		{
			System.out.println(c);
			try {
				Writer.write(String.valueOf(c.toString()));
				Writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println();
		Writer.write("\n");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// get the list of cars that is stored as an arraylist and print it out
		
	}
}
