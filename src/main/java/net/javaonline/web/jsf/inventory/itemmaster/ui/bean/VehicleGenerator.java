package net.javaonline.web.jsf.inventory.itemmaster.ui.bean;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;



public class VehicleGenerator implements Serializable
{
 
	private static final long serialVersionUID = 1L;
	private List<String> namesPool;
    private List<String> chassisPool;
    private Random randomizer = new Random(System.nanoTime());
    private NumberFormat numberFormatter;
    

    public VehicleGenerator() 
    {
        this.namesPool = getVehicleDescriptions();
        this.chassisPool = getChassisDescriptions();
        this.randomizer = new Random(System.nanoTime());
        this.numberFormatter = makeFormatter();
    }
    
    public ArrayList<Car> getRandomCars(int quantity)
    {
        ArrayList<Car> listWithCars = getPredefinedCars();
        
        if(quantity<listWithCars.size())
            return new ArrayList<Car>(listWithCars.subList(0, quantity));
        
        if(quantity>listWithCars.size())
            return addCarsToList(quantity - listWithCars.size(), listWithCars);
        
        //if quantity == listWithCars.size()
        return listWithCars;
    }
    
    public ArrayList<SelectableCar> getRandomSelectableCars(int quantity)
    {
        ArrayList<Car> listWithCars = getRandomCars(quantity);
        ArrayList<SelectableCar> listWithSelectableCars = new ArrayList<SelectableCar>(listWithCars.size());
        
        for (Car car : listWithCars) {
            listWithSelectableCars.add(new SelectableCar(car));
        }
        
        return listWithSelectableCars;
    }
    
    public ArrayList<Car> addCarsToList(int quantityToAdd, ArrayList<Car> list)
    {
        int currentListSize = list.size();
        int position = 0;
        /*by the end of this loop we will have partial copy, full copy or more then one copy of our list,
        appended to the end of it */
        for(int i = 0; i< quantityToAdd; i++)
        {
            //add Car from the begining of the list to the end of it
            Car copyReference = list.get(position); //this pointer is mostly for code readabllity
            //new car.id =  car list size+position value+id of the 1st element in the predefined car list
            Car car = new Car(currentListSize+position+1, copyReference.getName(),
                                      copyReference.getChassis(), copyReference.getWeight(),
                                      copyReference.getAcceleration(), copyReference.getMpg(),
                                      copyReference.getCost());
            list.add(car);
            //move pointer to one position up in the list
            position ++;
            //check if we copied all elements of the original list
            if(position>=currentListSize)
            {
                //reset position pointer to the begining
                position = 0;
                //since our list doubled in size and contain full pattern of the original list we can increase currentListSize
                currentListSize = list.size();
            }
        }
        return list;
    }
    
    public ArrayList<Car> removeCarsFromList(int quantityToRemove, ArrayList<Car> list)
    {
        return new ArrayList<Car>(list.subList(0, list.size()-quantityToRemove-1));
    }
    
    private ArrayList<Car> getPredefinedCars()
    {
        ArrayList<Car> listWithCars = new ArrayList<Car>();
        
        listWithCars.add(new Car(1,"Enduro","Van",15383,10,17.86,6617.17));
        listWithCars.add(new Car(2,"Tomcat","Bus",7331,15,16.65,31464.24));
        listWithCars.add(new Car(3,"Doublecharge","Pickup",5333,15,17.84,10922.73));
        listWithCars.add(new Car(4,"Swordfish","Bus",10956,5,5.17,6019.83));
        listWithCars.add(new Car(5,"Iguana","Pickup",1696,10,9.43,19736.16));
        listWithCars.add(new Car(6,"Dart","Motorcycle",9261,15,12.85,37947.84));
        listWithCars.add(new Car(7,"Pisces","Luxury",7846,10,15.13,19235.2));
        listWithCars.add(new Car(8,"Flash","Mid-Size",11499,10,12.74,29942.38));
        
        listWithCars.add(new Car(9,"Tomcat","Mid-Size",10766,15,7.04,14342.74));
        listWithCars.add(new Car(10,"Pisces","Subcompact",2082,10,13.38,8015.01));
        listWithCars.add(new Car(11,"Superflash","Subcompact",14959,5,3.91,31941.1));
        listWithCars.add(new Car(12,"Husky","Mid-Size",14334,15,13.98,9303.69));
        listWithCars.add(new Car(13,"Gazelle","Semi-Truck",14248,5,8.64,9285.57));
        
        listWithCars.add(new Car(14,"Superflash","Luxury",3037,15,8.44,36154.13));
        listWithCars.add(new Car(15,"Enduro","Subcompact",4997,5,6.02,31221.48));
        listWithCars.add(new Car(16,"Tomcat","Pickup",4555,10,4.06,35895.53));
        listWithCars.add(new Car(17,"Courier","Pickup",9848,10,16.15,27343.38));
        listWithCars.add(new Car(18,"Enduro","Motorcycle",5725,5,14.17,34430.44));
        
        listWithCars.add(new Car(19,"Swordfish","Luxury",1466,10,11.72,19266.56));
        listWithCars.add(new Car(20,"Tomcat","Station Wagon",15576,10,15.3,16844.05));
        listWithCars.add(new Car(21,"Iguana","Van",10973,15,3.77,33369.32));
        listWithCars.add(new Car(22,"Doublecharge","Pickup",9414,10,6.37,19370.61));
        listWithCars.add(new Car(23,"Hawk","Motorcycle",8545,5,7.98,39124.1));
        
        listWithCars.add(new Car(24,"Hawk","Subcompact",1853,10,12.54,36068.19));
        listWithCars.add(new Car(25,"Tomcat","Subcompact",12785,10,12.97,26141.79));
        listWithCars.add(new Car(26,"Hawk","Semi-Truck",12453,10,10.4,13654.1));
        listWithCars.add(new Car(27,"Doublecharge","Van",11952,10,17.58,22599.82));
        listWithCars.add(new Car(28,"Flash","Semi-Truck",6107,5,8.48,26096.26));
        
        listWithCars.add(new Car(29,"Courier","Bus",8187,5,4.25,25470.63));
        listWithCars.add(new Car(30,"Dart","Motorcycle",7177,10,12.16,35394.23));

        return listWithCars;
    }
    
    public ArrayList<Car> getCarsForLazyLoading(int quantity)
    {
        return new ArrayList<Car>(generateRandomCars(quantity));
    }
    
    public List<String> getChassisPool() { return chassisPool; }
    public List<String> getNamesPool() { return namesPool; }
    
    ///////////////////// THIS SECTION CONTAIN METHODS FOR TRUE RANDOM CAR GENERATION////////////////////
   //////////////// IT WAS REMOVED FROM PUBLIC CLASS INTERFACE DUE TO QC TEST REQUIREMENTS////////////
    
    private Car getRandomCar()
    {
        Car randomCar = new Car(randomizer.nextInt(10000000), generateName(),
                                  generateChassis(), generateWeight(), generateAccelerationValue(),
                                  generateMPG(), generateCost());
        return randomCar;
    }
    
    private SelectableCar getRandomSelectableCar() {
        return new SelectableCar(getRandomCar()); 
    }
    
    private ArrayList<Car> generateRandomCars(int quantity)
    {
        ArrayList<Car> listWithRandomCars = new ArrayList<Car>(quantity);
        for (int i = 0; i < quantity; i++) 
        {
            Car randomCar = getRandomCar();
            listWithRandomCars.add(randomCar);
        }
        return listWithRandomCars;
    }
    
    private ArrayList<SelectableCar> generateRandomSelectableCars(int quantity)
    {
        ArrayList<SelectableCar> listWithRandomCars = new ArrayList<SelectableCar>(quantity);
        for (int i = 0; i < quantity; i++) 
        {
            SelectableCar randomCar = getRandomSelectableCar();
            listWithRandomCars.add(randomCar);
        }
        return listWithRandomCars;
    }
    
    private List<String> getVehicleDescriptions() 
    {
        List<String> listWithNames = new ArrayList<String>();
        listWithNames.add("Spider");
        listWithNames.add("Hawk");
        listWithNames.add("Tomcat");
        listWithNames.add("Gazelle");
        listWithNames.add("Mantis");
        listWithNames.add("Flash");
        listWithNames.add("Iguana");
        listWithNames.add("Swordfish");
        listWithNames.add("Rattler");
        listWithNames.add("Courier");
        listWithNames.add("Pisces");
        listWithNames.add("Superflash");
        listWithNames.add("Doublecharge");
        listWithNames.add("Dart");
        listWithNames.add("Enduro");
        listWithNames.add("King Crab");
        listWithNames.add("Vanguard");
        listWithNames.add("Camel");
        listWithNames.add("Husky");
        return listWithNames;
    }

    private List<String> getChassisDescriptions() 
    {
        List<String> listWithNames = new ArrayList<String>();
        listWithNames.add("Motorcycle");
        listWithNames.add("Subcompact");
        listWithNames.add("Mid-Size");
        listWithNames.add("Luxury");
        listWithNames.add("Station Wagon");
        listWithNames.add("Pickup");
        listWithNames.add("Van");
        listWithNames.add("Bus");
        listWithNames.add("Semi-Truck");
        return listWithNames;
    }
    
    private NumberFormat makeFormatter() 
    {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setGroupingUsed(false);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter;
    }
    
    private String generateName() 
    { return namesPool.get(randomizer.nextInt(namesPool.size())); }
	
    private String generateChassis() 
    { return chassisPool.get(randomizer.nextInt(chassisPool.size())); }
    
    private int generateWeight() 
    { return 1000+randomizer.nextInt(15000); }
	
    private int generateAccelerationValue() 
    { return (1+randomizer.nextInt(3)) * 5; }
	
    private double generateMPG() 
    { return Double.parseDouble(numberFormatter.format( ((double)(3+randomizer.nextInt(15))) + randomizer.nextDouble())); }
	
    private double generateCost() 
    { return Double.parseDouble(numberFormatter.format( ((double)(2000+randomizer.nextInt(40000))) + randomizer.nextDouble()) ); }
}