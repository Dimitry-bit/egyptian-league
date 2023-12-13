package com.github.egyptian_league;

import com.github.egyptian_league.CLI.CLI_Input;

public class Main {
    public static void main(String[] args) {
        Arguments.initArgs(args);
        ApplicationRepository.loadDb();

        if (Arguments.getArgument("--no-gui", 0) != null) {
            CLI_Input.start();
        } else {
            HelloApplication.launch(HelloApplication.class, args);
        }

        ApplicationRepository.saveDb();
    }
}
