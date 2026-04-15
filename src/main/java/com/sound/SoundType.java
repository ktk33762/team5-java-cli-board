package com.sound;

/**
 * 사운드 파일 경로를 관리하는 열거형 클래스
 * 각 사운드 타입에 대해 해당 사운드 파일의 경로를 저장
 * 경로는 src/main/resources 기준
 */
public enum SoundType {
    // BGM
    BGM_TOWN("sounds/bgm/town_bgm.wav"),
    BGM_BATTLE("sounds/bgm/battle_bgm.wav"),

    // 몬스터 등장 효과음
    SFX_SLIME_APPEAR("sounds/sfx/slime_appear.wav"),
    SFX_GOBLIN_APPEAR("sounds/sfx/goblin_appear.wav"),

    // 몬스터 피격 효과음
    SFX_SLIME_HIT("sounds/sfx/slime_hit.wav"),
    SFX_GOBLIN_HIT("sounds/sfx/goblin_hit.wav"),

    // 유저 피격 효과음
    SFX_PLAYER_HIT("sounds/sfx/player_hit.wav"),

    // 레벨업 효과음
    SFX_LEVEL_UP("sounds/sfx/levelup.wav");

    private final String path;

    SoundType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
