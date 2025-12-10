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
            try {
                testlog log = deviceservice.runTest(id);
                return ResponseEntity.ok(log);
            }catch (IllegalArgumentException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }catch (Exception ex){
                return ResponseEntity.status(500).body("Erro: " + ex.getMessage());
            }
        }
    }
}
