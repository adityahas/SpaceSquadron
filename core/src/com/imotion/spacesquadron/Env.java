package com.imotion.spacesquadron;

/**
 * Created by Aditya Hadi on 12/3/2016.
 */

public class Env {
    // Scenes name
    public final static String O2D_SCENE_EMPTY = "EmptyScene";
    public final static String O2D_SCENE_MAIN = "MainScene";
    public final static String O2D_SCENE_MENU = "MenuScene";
    public final static String O2D_SCENE_GAMEPLAY = "GameplayScene";
    public final static String O2D_SCENE_SETTING = "SettingScene";
    public final static String O2D_SCENE_CREDIT = "CreditScene";
    public static final String O2D_SCENE_LOADING = "LoadingScene";
    public static final String O2D_SCENE_POP_UP = "PopUpScene";

    // Sounds name
    public final static String MUSIC_TITLE = "m_title.mp3";
    public final static String SFX_CLICK = "s_click.mp3";
    public final static String SFX_JUMP = "s_jump.mp3";


    private static SpaceSquadron app;


    public Env(SpaceSquadron spaceSquadron) {
        app = spaceSquadron;
    }

    public static SpaceSquadron getGame() {
        return app;
    }
}
