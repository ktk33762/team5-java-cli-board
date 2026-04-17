package org.example;

public class App {
    public void run() {

        while (true) {
            System.out.println("== 게임 앱 ==");
            Character.Status();

            switch (AppContext.getResponse(AppContext::choiceOfPlace)) {

                case 1 -> {
                    AppContext.clearConsole();
                    AppContext.hunt.Run();
                }
                case 2 -> {
                    AppContext.clearConsole();
                    AppContext.store.Run();
                }
                case 5 -> {
                    System.out.println("종료합니다.");
                    return;
                }
            }
        }
    }

}
