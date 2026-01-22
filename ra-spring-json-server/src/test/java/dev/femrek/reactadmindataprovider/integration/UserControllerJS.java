package dev.femrek.reactadmindataprovider.integration;

import dev.femrek.reactadmindataprovider.jsonserverextended.controller.RAControllerJSExtended;
import dev.femrek.reactadmindataprovider.jsonserverextended.service.IRAServiceJSExtended;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserControllerJS extends RAControllerJSExtended<User, Long> {
    private final UserService userService;

    public UserControllerJS(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected IRAServiceJSExtended<User, Long> getService() {
        return userService;
    }
}
