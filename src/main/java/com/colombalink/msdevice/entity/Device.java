package com.colombalink.msdevice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "device")
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    private String id;
    private String deviceName;
    private double reading;
    private DeviceType deviceType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdDate;
}
