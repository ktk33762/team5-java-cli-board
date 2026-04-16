package com.controller;

import com.AppContext;
import com.domain.user.BaseUser;
import com.global.Rq;
import com.sound.SoundManager;
import com.sound.SoundType;

public class SystemController {
    public void run() {
        while (true) {
            System.out.println("CLI 기반 텍스트 RPG");
            System.out.println("여러분은 이제 모험의 세계로 떠납니다.");
            System.out.println("====================================");
            System.out.println("직업을 선택하여 주세요!");
            System.out.println("1.전사 2.마법사");
            System.out.print("숫자를 입력하여 주세요 -> ");
            String select = Rq.getLine();
            switch (select) {
                case "1" -> System.out.println("전사를 선택하셨습니다.");
                case "2" -> System.out.println("마법사를 선택하셨습니다.");
                default -> {
                    System.out.println("다시 입력해주세요.");
                    continue;
                }
            }
            System.out.println("닉네임을 입력하여 주세요!");
            System.out.print("닉네임 : ");
            String name = Rq.getLine();
            BaseUser user = AppContext.userService.createUser(select, name);
            AppContext.setUser(user);
            System.out.printf("닉네임 설정 완료! %s\n", user.getName());
            System.out.println("====================================");
            SoundManager.getInstance().playBgm(SoundType.BGM_TOWN);
            System.out.printf("%s %s(이)가 마을에 왔습니다.\n", user.getJob(), user.getName());
            int result = AppContext.townController.start();
            if(result == -1) return;
        }
    }
}
