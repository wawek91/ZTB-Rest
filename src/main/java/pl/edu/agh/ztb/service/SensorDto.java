package pl.edu.agh.ztb.service;

import pl.edu.agh.ztb.mod2.model.Sensor;

public class SensorDto extends Sensor {
	
	//workaround for missing non-argument constructor in Sensor class - it is needed for Jackson to instantiate object properly
	public SensorDto() {
		super(-1, -1, -1);
	}

	public SensorDto(int segment_ctrl_id, int location_id, int sensor_type_id) {
		super(segment_ctrl_id, location_id, sensor_type_id);
	}

}
