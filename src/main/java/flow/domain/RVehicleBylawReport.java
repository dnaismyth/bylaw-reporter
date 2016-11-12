package flow.domain;

import javax.persistence.Column;

public class RVehicleBylawReport extends RBylawReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7373332922627392245L;

	@Column(name = "vehicle_description", nullable = false)
	private String vehicleDescription;
	
	@Column(name = "license_plate")
	private String licensePlate;

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	
}
