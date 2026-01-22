package dev.femrek.reactadmindataprovider.unit.jsonserverextended;

import dev.femrek.reactadmindataprovider.jsonserverextended.controller.RAControllerJSExtended;
import dev.femrek.reactadmindataprovider.jsonserverextended.service.IRAServiceJSExtended;
import dev.femrek.reactadmindataprovider.unit.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Extended REST controller for User entity with bulk operations support.
 * This controller extends RAControllerJSExtended to provide all standard
 * React Admin operations plus updateMany and deleteMany endpoints.
 */
@RestController
@RequestMapping("/api/users-extended")
@CrossOrigin(origins = "*")
public class UserControllerJSExtended extends RAControllerJSExtended<User, Long> {
    private final UserServiceJSExtended userServiceJSExtended;

    public UserControllerJSExtended(UserServiceJSExtended userServiceJSExtended) {
        this.userServiceJSExtended = userServiceJSExtended;
    }

    @Override
    protected IRAServiceJSExtended<User, Long> getService() {
        return userServiceJSExtended;
    }
}

