package io.codelex.flightplanner.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingController {

    private AdminService adminService;

    @Autowired
    public TestingController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/clear")
    public void clearFlights() {
        adminService.clearFlights();
    }

}
