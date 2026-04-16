package com;

import com.controller.SystemController;
import com.controller.TownController;
import com.domain.user.BaseUser;
import com.service.BattleService;
import com.service.UserService;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static BaseUser user;
    public static SystemController systemController;
    public static TownController townController;
    public static UserService userService;
    public static BattleService battleService;

    public static void init(Scanner sc) {
        scanner = sc;
        systemController = new SystemController();
        townController = new TownController();
        userService = new UserService();
        battleService = new BattleService();
    }

    public static void setUser(BaseUser _user) {
        user = _user;
    }
}
