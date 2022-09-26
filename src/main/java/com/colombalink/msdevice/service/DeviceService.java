package com.colombalink.msdevice.service;

import com.colombalink.msdevice.dto.DeviceDTO;
import com.colombalink.msdevice.entity.Device;
import com.colombalink.msdevice.entity.DeviceType;
import com.colombalink.msdevice.error.DeviceException;
import com.colombalink.msdevice.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceName("TMP_SEN_001");
        deviceDTO.setDeviceType(DeviceType.TEMPERATURE_SENSOR);
        deviceDTO.setReading(Math.random() * 100);
        saveDeviceData(deviceDTO);
    }

    public Device saveDeviceData(DeviceDTO deviceDTO) {
        Device device = convertDtoToEntity(deviceDTO);
        device.setId(UUID.randomUUID().toString());
        device = deviceRepository.save(device);
        log.info("deviceId" + device.getId(), device);
        return device;
    }

    public Device getDevice(String deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new DeviceException("Cannot find device Id" + deviceId));
    }

    public List<Device> getAll(String deviceName, DeviceType deviceType, Date startDate, Date endDate) {
        if (startDate != null && deviceName != null) {
            return deviceRepository.findByDeviceNameAndDeviceTypeAndCreatedDateBetween(deviceName, deviceType, startDate, endDate);
        } else if (deviceName != null & startDate == null) {
            return deviceRepository.findByDeviceNameAndDeviceType(deviceName, deviceType);
        } else if (deviceName == null && startDate != null) {
            return deviceRepository.findByDeviceTypeAndCreatedDateBetween(deviceType, startDate, endDate);
        } else {
            return deviceRepository.findAll();
        }
    }

    public Device updateDeviceData(String deviceId, DeviceDTO deviceDTO) {
        deviceDTO.setId(deviceId);
        Device device = convertDtoToEntity(deviceDTO);
        return deviceRepository.save(device);
    }

    public void deleteDevice(String deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    private Device convertDtoToEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getId(), deviceDTO.getDeviceName(), deviceDTO.getReading(), deviceDTO.getDeviceType(), new Date());
    }
}
