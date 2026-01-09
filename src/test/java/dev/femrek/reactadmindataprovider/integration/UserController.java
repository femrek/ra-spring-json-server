package dev.femrek.reactadmindataprovider.integration;

import dev.femrek.reactadmindataprovider.controller.ReactAdminController;
import dev.femrek.reactadmindataprovider.service.IReactAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends ReactAdminController<User, Long> {

    @Autowired
    private UserService userService;

    @Override
    protected IReactAdminService<User, Long> getService() {
        return userService;
    }
}
