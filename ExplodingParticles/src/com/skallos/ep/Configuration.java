package com.skallos.ep;

public enum Configuration {
	
//	public static final float EXPLOSION_START_RADIUS = 10f;
	EXPLOSION_START_RADIUS(0f),
	EXPLOSION_MAXIMUM_RADIUS(45f),
	EXPLOSION_GROWTH_TIME(1.5f),
	EXPLOSION_ADULT_TIME_LENGTH(0.5f),
	EXPLOSION_SHRINK_TIME_LENGTH(0.75f),
	PARTICLE_STD_RADIUS(25f),
	PARTICLE_MIN_VELOCITY(50f),
	PARTICLE_MAX_VELOCITY(125f);
	
	
	private float value;
	Configuration(float value){
		this.value = value;
	}
	
	public float getValue(){
		return this.value;
	}
	
}
