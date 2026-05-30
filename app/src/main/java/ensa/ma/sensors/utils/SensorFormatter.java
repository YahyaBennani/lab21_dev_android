package ensa.ma.sensors.utils;

import android.hardware.Sensor;
import android.os.Build;

public class SensorFormatter {

    public static String format(Sensor sensor) {
        String result = "Id : " + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? sensor.getId() : "N/A") + "\n"
                + "Name : " + sensor.getName() + "\n"
                + "Vendor : " + sensor.getVendor() + "\n"
                + "Version : " + sensor.getVersion() + "\n"
                + "Type : " + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH ? sensor.getStringType() : "N/A") + "\n"
                + "Int Type : " + sensor.getType() + "\n"
                + "Resolution : " + sensor.getResolution() + "\n"
                + "Power : " + sensor.getPower() + " mA\n"
                + "Maximum Range : " + sensor.getMaximumRange() + "\n"
                + "Min Delay : " + sensor.getMinDelay() + " µs\n";
        return result;
    }
}
