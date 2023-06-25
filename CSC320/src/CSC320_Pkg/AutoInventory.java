package CSC320_Pkg;

public class AutoInventory {
	String make;
	String model;
	String year;
	String color;
	String cond;
	String vin;
	double mileage;
	double msrp;
	double dealerPur;
	double price;

	// default constructor
	public AutoInventory() {
		make = "";
		model = "";
		year = "";
		color = "";
		cond = "";
		vin = "";
		mileage = 0.0;
		msrp = 0.0;
		dealerPur = 0.0;
		price = 0.0;
	}

	// parameterized constructor
	public AutoInventory(String make, String model, String year, String color, String cond, String vin, double mileage,
			double msrp, double dealerPur, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.cond = cond;
		this.vin = vin;
		this.mileage = mileage;
		this.msrp = msrp;
		this.dealerPur = dealerPur;
		this.price = price;
	}

	// method to add a new vehicle
	public String addNewVehicle(String make, String model, String year, String color, String cond, String vin,
			double mileage, double msrp, double dealerPur, double price) {
		try {
			this.make = make;
			this.model = model;
			this.year = year;
			this.color = color;
			this.cond = cond;
			this.vin = vin;
			this.mileage = mileage;
			this.msrp = msrp;
			this.dealerPur = dealerPur;
			this.price = price;
			return "Vehicle added successfully";
		} catch (Exception e) {
			return "Failed to add vehicle";
		}
	}

	// method to list vehicle information
	public String[] listVehicleInformation() {
		try {
			String[] vehicleInfo = new String[10];
			vehicleInfo[0] = this.make;
			vehicleInfo[1] = this.model;
			vehicleInfo[2] = this.year;
			vehicleInfo[3] = this.color;
			vehicleInfo[4] = this.cond;
			vehicleInfo[5] = String.valueOf(this.vin);
			vehicleInfo[6] = String.valueOf(this.mileage);
			vehicleInfo[7] = String.valueOf(this.msrp);
			vehicleInfo[8] = String.valueOf(this.dealerPur);
			vehicleInfo[9] = String.valueOf(this.price);
			return vehicleInfo;
		} catch (Exception e) {
			return null;
		}
	}

	// method to remove a vehicle
	public String removeVehicle() {
		try {
			this.make = "";
			this.model = "";
			this.year = "";
			this.color = "";
			this.cond = "";
			this.vin = "";
			this.mileage = 0.0;
			this.msrp = 0.0;
			this.dealerPur = 0.0;
			this.price = 0.0;
			return "Vehicle removed successfully";
		} catch (Exception e) {
			return "Failed to remove vehicle";
		}
	}

	// method to update vehicle attributes
	public String updateVehicleAttributes(String make, String model, String year, String color, String cond, String vin,
			double mileage, double msrp, double dealerPur, double price) {
		try {
			this.make = make;
			this.model = model;
			this.year = year;
			this.color = color;
			this.cond = cond;
			this.vin = vin;
			this.mileage = mileage;
			this.msrp = msrp;
			this.dealerPur = dealerPur;
			this.price = price;
			return "Vehicle attributes updated successfully";
		} catch (Exception e) {
			return "Failed to update vehicle attributes";
		}
	}
	
	

	//Get methods for car properties
	
	public String getMake() {
		return this.make;
	}

	public String getModel() {
		return this.model;
	}

	public String getYear() {
		return this.year;
	}

	public String getVin() {
		return this.vin;
	}

	public String getColor() {
		return this.color;
	}

	public String getCond() {
		return this.cond;
	}

	public double getMileage() {
		return this.mileage;
	}

	public double getMsrp() {
		return this.msrp;
	}

	public double getDealerPur() {
		return this.dealerPur;
	}

	public double getPrice() {
		return this.price;
	}
	
	//Set methods for car properties
	
	public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public void setDealerPur(double dealerPur) {
        this.dealerPur = dealerPur;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
