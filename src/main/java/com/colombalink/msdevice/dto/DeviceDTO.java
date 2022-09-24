package com.colombalink.msdevice.dto;

import com.colombalink.msdevice.entity.DeviceType;
import lombok.Data;

@Data
public class DeviceDTO {
    private String id;
    private String deviceName;
    private double reading;
    private DeviceType deviceType;
}
