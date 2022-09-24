package com.colombalink.msdevice.repository;

import com.colombalink.msdevice.entity.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
