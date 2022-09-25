package com.colombalink.msdevice.controller;

import com.colombalink.msdevice.dto.DeviceDTO;
import com.colombalink.msdevice.entity.Device;
import com.colombalink.msdevice.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@CrossOrigin(origins = "http://localhost:4200")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @ApiOperation(value = "Save device data")
    @PostMapping("/")
    public Device saveDeviceData(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.saveDeviceData(deviceDTO);
    }

    @ApiOperation(value = "Get device Data")
    @GetMapping("/{deviceId}")
    public Device getDevice(@PathVariable("deviceId") String deviceId) {
        return deviceService.getDevice(deviceId);
    }

    @ApiOperation(value = "Get All device data")
    @GetMapping("/get-all")
    public List<Device> getAllDevice() {
        return deviceService.getAll();
    }

    @ApiOperation(value = "Update device Data")
    @PatchMapping("/{deviceId}")
    public Device updateDevice(@PathVariable("deviceId") String deviceId, @RequestBody DeviceDTO deviceDTO) {
        return deviceService.updateDeviceData(deviceId, deviceDTO);
    }

    @ApiOperation(value = "Delete device Data")
    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") String deviceId) {
        deviceService.deleteDevice(deviceId);
    }
}
