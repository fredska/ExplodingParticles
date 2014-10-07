package com.skallos.ep;

public enum Configuration {
	
//	public static final float EXPLOSION_START_RADIUS = 10f;
	EXPLOSION_START_RADIUS(5f),
	EXPLOSION_MAXIMUM_RADIUS(30f),
	EXPLOSION_GROWTH_TIME(1.5f),
	EXPLOSION_ADULT_TIME_LENGTH(0.5f),
	EXPLOSION_SHRINK_TIME_LENGTH(0.75f),
	PARTICLE_STD_RADIUS(5f),
	PARTICLE_MIN_VELOCITY(-150f),
	PARTICLE_MAX_VELOCITY(150f);
	
	
	private float value;
	Configuration(float value){
		this.value = value;
	}
	
	public float getValue(){
		return this.value;
	}
	
}
