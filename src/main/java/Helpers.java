import java.util.ArrayList;
import java.util.List;

public class Helpers {
	
	public static ArrayList<CarDealerSelector> compareCheckedVehicles(ArrayList<CarDealerSelector> onlineCarList) {

		ArrayList<CarDealerSelector> reducedListOfCarsWithDealers = new ArrayList<CarDealerSelector>(onlineCarList);
		List<String> checkedCars = IOOperations.readFromExcel();
		
		for (CarDealerSelector onlineCar : onlineCarList) {
			for (String checkedCar : checkedCars) {
				if (onlineCar.getCarId().equals(checkedCar)) {
					reducedListOfCarsWithDealers.remove(onlineCar);	
					break;
				}
			}
		}
		return reducedListOfCarsWithDealers;
	}
}
