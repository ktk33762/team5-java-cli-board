package com.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BGM : 페이드 인/아웃 효과로 전환되고 동시에 하나만 재생
 * SFX : BGM과 별개의 Clip으로 동시 재생되므로 BGM을 끊지 않음
 * BGM 전환은 excurator(별도 스레드)에서 처리하여 메인 스레드 블로킹 하지 않음
 */
public class SoundManager {
    // 싱글톤 적용(전체 동작에서 한개의 SoundManager 인스턴스만 존재하도록)
    private static final SoundManager instance = new SoundManager();
    public static SoundManager getInstance() {
        return instance;
    }
    private SoundManager() {}

    // volatile 키워드로 가시성 보장
    // 모든 스레드가 최신 값을 읽도록 함
    private volatile Clip bgmClip;
    private volatile SoundType curBgm;

    // BGM 전환 + SFX 재생을 비동기로 처리하는 스레드 풀
    private final ExecutorService executor =
            Executors.newCachedThreadPool(r -> {
                Thread t = new Thread(r);
                t.setDaemon(true); // 게임 종료 시 자동 정리
                return t;
            });

    private static final int FADE_DURATION = 600;
    private static final int extraDelay = 200;

    // BGM 재생(전환)
    public void playBgm(SoundType bgmType) {
        if(bgmType == curBgm) return;

        executor.submit(() -> {
            if(bgmClip != null && bgmClip.isRunning()) {
                fadeOut(bgmClip, FADE_DURATION);
                bgmClip.stop();
                bgmClip.close();
            }
            Clip newClip = loadClip(bgmType);
            if(newClip != null) {
                bgmClip = newClip;
                curBgm = bgmType;
                setVolume(bgmClip, -60f);
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
                fadeIn(bgmClip, FADE_DURATION);
            }
        });
    }

    // BGM 정지
    public void stopBgm() {
        if (bgmClip == null) return;
        Clip target = bgmClip;
        bgmClip = null;
        curBgm = null;
        executor.submit(() -> {
            fadeOut(target, FADE_DURATION);
            target.stop();
            target.close();
        });
    }

    // 효과음 재생(동기)
    public void playSfx(SoundType sfxType) {
        Clip sfxClip = loadClip(sfxType);
        if (sfxClip == null) {
            // 파일 없으면 extraDelay만큼만 대기
            safeSleep(extraDelay);
            return;
        }

        long clipDurationMs = sfxClip.getMicrosecondLength() / 1000;
        long totalWait = clipDurationMs + extraDelay;

        sfxClip.start();

        safeSleep(totalWait); // 잠시 대기

        sfxClip.stop();
        sfxClip.close();
    }

    // 효과음 재생(비동기)
    public void playSfxAsync(SoundType sfxType) {
        executor.submit(() -> {
            Clip sfxClip = loadClip(sfxType);
            if (sfxClip == null) return;
            sfxClip.start();
            sfxClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) sfxClip.close();
            });
        });
    }

    // 잠시 쓰레드가 멈추도록 하는 메서드
    private void safeSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Clip 객체를 로드하는 메서드
    private Clip loadClip(SoundType soundType) {
        try {
            URL url = getClass().getClassLoader().getResource(soundType.getPath());
            if (url == null) {
                System.err.println("[SoundManager] 파일 없음: " + soundType.getPath());
                return null;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("[SoundManager] 로드 실패: " + soundType.getPath());
            return null;
        }
    }

    // 볼륨 조절
    private void setVolume(Clip clip, float dB) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(Math.max(control.getMinimum(), Math.min(control.getMaximum(), dB)));
        }
    }

    // 페이드 인
    private void fadeIn(Clip clip, int duration) {
        new SoundFade(clip, SoundFade.FadeType.FADE_IN, duration, null).run();
    }

    // 페이드 아웃
    private void fadeOut(Clip clip, int duration) {
        new SoundFade(clip, SoundFade.FadeType.FADE_OUT, duration, null).run();
    }

    // 앱 완전 종료 시 호출
    // BGM 정지 후 executor를 종료해 모든 스레드 정리
    public void shutdown() {
        stopBgm();
        executor.shutdown();
    }
}
