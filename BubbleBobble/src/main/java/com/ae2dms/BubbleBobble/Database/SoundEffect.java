package com.ae2dms.BubbleBobble.Database;

import javafx.scene.media.AudioClip;
import java.net.URL;

/**
 * The {@link com.ae2dms.BubbleBobble.Database.SoundEffect} class handles the game's SFX.
 * <p>
 * Classes that want to use SFX will call the static variables in this enum and
 * play them via the play() method.
 * </p>
 */
public enum SoundEffect {

	/**
	 * sound effects
	 */
	FRUIT("sfx/fruit.wav"),
	DEATH("sfx/death.wav"),
	SHOOT("sfx/shoot.wav"),
	POP("sfx/pop.wav"),
	BUBBLED("sfx/bubbled.wav"),
	JUMP("sfx/jump.wav"),
	EXPLODE("sfx/explode.wav"),
	LAND("sfx/land.wav");

	/**
	 * the default volume
	 */
	private double volume = 0.5;
	/**
	 * the audio clip
	 */
	private AudioClip clip;

	/**
	 * The initialize method to set the sound effect to default volume
 	 */
	SoundEffect(String soundFileName) {
		URL url = SoundEffect.class.getResource("/"+soundFileName);
		clip = new AudioClip(url.toExternalForm());
		clip.setVolume(volume);
	}

	/**
	 * The method to play the sound effect
	 */
	public void play() {
		if (clip.getVolume()!=0) {
			if (clip.isPlaying()) {
				clip.stop();
			}
			clip.play();
		}
	}

	/**
	 * The getter method of the volume
	 * @return the value of the volume
	 */
	public double getVolume(){
		return clip.getVolume();
	}

	/**
	 * The setter method of the volume
	 * @param volume indicates the value of the volume, which should be from 0 to 1
	 */
	public void setVolume(double volume){
		clip.setVolume(volume);
	}

	/**
	 * The method to decrease the volume
	 */
	public void setToLoop() {
		clip.setVolume(clip.getVolume()-0.1);
	}

	/**
	 * The method to increase the volume
	 */
	public void setToLoud() {
		clip.setVolume(clip.getVolume()+0.1);
	}

	/**
	 * The method to mute the volume
	 */
	public void mute() {
		clip.setVolume(0);
	}
}
