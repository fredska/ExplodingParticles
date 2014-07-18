package com.skallos.ep;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Explosion {
	private float startRadius, maxRadius;
	private float growthTime; //Time between the beginning and the maximum radius;
	private float adultTimeLength; //Time the explosion stays at it's maximum radius;
	private float shrinkTimeLength; //Time the explosion takes to go from adult to radius = 0;
	
	private float currentRadius;
	
	private Vector2 position;
	
	private float lifeTime;
	private float maxLifeTime;
	
	public Explosion(Vector2 position){
		this.position = position;
		this.lifeTime = 0;
		this.startRadius = Configuration.EXPLOSION_START_RADIUS.getValue();
		this.maxRadius = Configuration.EXPLOSION_MAXIMUM_RADIUS.getValue();
		
		this.growthTime = Configuration.EXPLOSION_GROWTH_TIME.getValue(); //Growth in seconds;
		this.adultTimeLength = Configuration.EXPLOSION_ADULT_TIME_LENGTH.getValue(); //Adult life in seconds;
		this.shrinkTimeLength = Configuration.EXPLOSION_SHRINK_TIME_LENGTH.getValue();
		this.currentRadius = this.startRadius;
		this.maxLifeTime = (growthTime + adultTimeLength + shrinkTimeLength);
	}
	
	public void update(float delta){
		if(this.lifeTime >= maxLifeTime) return;
		
		this.lifeTime += delta;
		
		if(this.lifeTime <= growthTime){
			this.currentRadius = startRadius + (lifeTime / growthTime) * (maxRadius - startRadius);
		} else if(this.lifeTime <= growthTime + adultTimeLength){
			this.currentRadius = this.maxRadius;
		} else {
			this.currentRadius = this.maxRadius * (1 - ((lifeTime - growthTime - adultTimeLength) / shrinkTimeLength));
		}
		
	}
	
	public void draw(ShapeRenderer shapeRenderer){
		//Don't draw anything if the explosion is past its lifetime
		if(this.lifeTime >= maxLifeTime) return;
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(this.position.x, this.position.y, this.currentRadius);
		shapeRenderer.end();
	}
	
	public boolean isDead(){
		return this.lifeTime >= (this.growthTime + this.adultTimeLength + this.shrinkTimeLength);
	}
	
	public float getCurrentRadius(){
		if(isDead()) return 0;
		return this.currentRadius;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
}
