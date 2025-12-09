package com.example.back_end.controller;

public class devicecontroller {
    import com.example.back_end.dto.devicerequest;
    import com.example.back_end.model.device;
    import com.example.back_end.model.testlog;
    import com.example.back_end.service.deviceservice;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/devices")
    public class devicecontroller {
        private final deviceservice deviceservice;

        public devicecontroller(deviceservice deviceservice) {
            this.deviceservice = deviceservice;
        }

        @PostMapping
        public ResponseEntity<device> createDevice(@RequestBody devicerequest req) {
            if (req.getIp_adress() == null || req.getName() == null) {
                return ResponseEntity.badRequest().body("Nome e IP obrigatórios");
            }
            device d = new device();
            d.setName(req.getName());
            d.setIp_adress(req.getIp_adress());
            d.setType(req.getType());

            try {
                device saved = deviceservice.createDevice(d);
                return ResponseEntity.ok(saved);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }

        @GetMapping
        public ResponseEntity<List<device>> listDevices(@RequestParam(value = "refresh", required = "false") boolean refresh){
            return ResponseEntity.ok(deviceservice.listDevices(refresh));
        }

        @GetMapping("/{id}/logs")
        public ResponseEntity<List<testlog>> getDeviceLogs(@PathVariable Long id) {
           return ResponseEntity.ok(deviceservice.getDeviceLogs(id));
    }
}
