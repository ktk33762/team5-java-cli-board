package com.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/**
 * 배경음 페이드 인/아웃 효과 담당 클래스
 * Runnable을 구현하므로 executor에 submit하거나 직접 run() 호출 모두 가능
 */
public class SoundFade implements Runnable {
    public enum FadeType {
        FADE_IN,
        FADE_OUT
    }

    private final Clip clip;
    private final FadeType fadeType;
    private final int duration;         // 페이드 효과 지속 시간 (밀리초)
    private final Runnable onComplete;  // 완료 콜백

    private static final float MAX_VOLUME =  0.0f;   // dB 기준 최대 (0dB)
    private static final float MIN_VOLUME = -60.0f;  // dB 기준 무음

    public SoundFade(Clip clip, FadeType fadeType, int duration, Runnable onComplete) {
        this.clip = clip;
        this.fadeType = fadeType;
        this.duration = duration;
        this.onComplete = onComplete;
    }

    @Override
    public void run() {
        try {
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            int steps = 50; // 페이드 단계 수 (높을수록 부드럽지만 CPU 사용량 증가)
            int stepDelay = duration / steps;
            float volumeStep = (MAX_VOLUME - MIN_VOLUME) / steps;

            for (int i = 0; i <= steps; i++) {
                float volume;
                if (fadeType == FadeType.FADE_IN) {
                    volume = MIN_VOLUME + (volumeStep * i);
                } else {
                    volume = MAX_VOLUME - (volumeStep * i);
                }
                // 범위 클램핑(일정 범위를 넘어가지 않도록 제한)
                volume = Math.max(MIN_VOLUME, Math.min(MAX_VOLUME, volume));
                gainControl.setValue(volume);
                Thread.sleep(stepDelay);
            }

            if (onComplete != null) {
                onComplete.run();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
