package com.example.back_end.controller;

public class testcontroller {
    import com.example.back_end.model.testlog;
    import com.example.back_end.service.service.deviceservice;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/test")
    public class testcontroller {
        private final deviceservice deviceservice;

        public testcontroller(deviceservice deviceservice) {
            this.deviceservice = deviceservice;
        }

        @PostMapping("/device/{id}")
        public ResponseEntity<?> runTest(@PathVariable Long id){
            
        }
    }
}
