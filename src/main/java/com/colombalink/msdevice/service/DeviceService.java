package com.colombalink.msdevice.service;

import com.colombalink.msdevice.dto.DeviceDTO;
import com.colombalink.msdevice.entity.Device;
import com.colombalink.msdevice.entity.DeviceType;
import com.colombalink.msdevice.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceName("TMP_SEN_002");
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

    public DeviceDTO getDevice(String deviceId) {
        return new DeviceDTO();
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device updateDeviceData(String deviceId, DeviceDTO deviceDTO) {
        deviceDTO.setId(deviceId);
        Device device = convertDtoToEntity(deviceDTO);
        return deviceRepository.save(device);
    }

    public void deleteDevice(String deviceId) {
//        Device device = deviceRepository.findById(deviceId).orElseThrow("");
    }

    private Device convertDtoToEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getId(), deviceDTO.getDeviceName(), deviceDTO.getReading(), deviceDTO.getDeviceType(), new Date());
    }
}
