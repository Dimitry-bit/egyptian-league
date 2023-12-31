package com.github.egyptian_league;

import com.github.egyptian_league.Arguments.Arguments;
import com.github.egyptian_league.CLI.CLI_Input;
import com.github.egyptian_league.Constants.ApplicationConstants;
import com.github.egyptian_league.GUI.GuiApplication;

public class Main {
    public static void main(String[] args) {
        Arguments.initArgs(args);
        ApplicationRepository.loadDb(ApplicationConstants.dbFileName);

        if (Arguments.getArgument("--no-gui", 0) != null) {
            CLI_Input.start();
        } else {
            GuiApplication.launch(GuiApplication.class, args);
        }

        ApplicationRepository.saveDb(ApplicationConstants.dbFileName);
    }
}
