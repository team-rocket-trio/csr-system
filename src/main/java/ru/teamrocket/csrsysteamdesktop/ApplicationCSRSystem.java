package ru.teamrocket.csrsysteamdesktop;

/**
 * Created by Alexander Shreyner on 06.11.2016.
 */

import javafx.application.Application;

abstract class ApplicationCSRSystem extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    protected static void launchApp(Class<? extends ApplicationCSRSystem> appClass, String[] argv){
        Application.launch(appClass, argv);
    }
}
