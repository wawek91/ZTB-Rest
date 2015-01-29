package pl.edu.agh.ztb.service;

import pl.edu.agh.ztb.mod2.model.Fixture;

import java.sql.Timestamp;

/**
 * Created by Marek on 2015-01-29.
 */
public class FixtureTmp extends Fixture {

    public FixtureTmp(int id, int location_id, int segment_ctrl_id, String actual_state, String dim_level, double hours_on, Timestamp time_of_last_switch_on, Timestamp time_of_last_switch_off, String hid_status, String device_type, String ballasts_and_work_type, String voltage_reset, String min_dim_level) {
        super(1, 1, 1, "1", "1", 1.1, new Timestamp(12312312), new Timestamp(1231321), "1", "1", "1", "1", "1");
    }
}
