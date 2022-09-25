package com.colombalink.msdevice.repository;

import com.colombalink.msdevice.entity.Device;
import com.colombalink.msdevice.entity.DeviceType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findByDeviceNameAndDeviceTypeAndCreatedDateBetween(String deviceName, DeviceType deviceType, Date startDate, Date endDate);

    List<Device> findByDeviceNameAndDeviceType(String deviceName, DeviceType deviceType);

    List<Device> findByDeviceTypeAndCreatedDateBetween(DeviceType deviceType, Date startDate, Date endDate);
}
