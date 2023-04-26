package com.korwin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.util.Scanner;

public class Korwin extends ApplicationAdapter {
	SpriteBatch batch;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	BitmapFont font24;
	Rectangle buttonRect;
	Texture buttonImg, buttonImg2, buttonImgCurrent;
	String[] wypowiedz=new String[6];
	int rand;
	File file;
	Scanner scan;

	public void generate(){
		for(int i=0; i<6; i++){
			wypowiedz[i]=" ";
		}
		for(int i=1; i<=6; i++) {
			//file = new File(i+".txt");
			file = new File("C:\\Users\\jakub\\OneDrive\\Dokumenty\\GitHub\\generator-wypowiedzi-korwina-v2\\"+i+".txt");
			try {
				scan = new Scanner(file);
			} catch (java.io.FileNotFoundException e) {
				System.out.println("File does not exist");
			}
			rand = (int) (Math.random() * 20) + 1;
			for (int j = 0; j < rand; j++) {
				wypowiedz[i-1] = scan.nextLine();
			}
		}
		scan.close();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		for(int i=0; i<6; i++){
			wypowiedz[i]=" ";
		}

		//button
		buttonRect=new Rectangle();
		buttonRect.height=100;
		buttonRect.width=200;
		buttonRect.x=550;
		buttonRect.y=550;
		buttonImgCurrent=new Texture(Gdx.files.internal("button.png"));
		buttonImg=new Texture(Gdx.files.internal("button.png"));
		buttonImg2=new Texture(Gdx.files.internal("button2.png"));

		//font
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 24;
		font24 = generator.generateFont(parameter);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(buttonImgCurrent,buttonRect.x,buttonRect.y);
		font24.draw(batch,"GENERATOR WYPOWIEDZI KORWINA",450,700);
		for(int i=0; i<6; i++){
			font24.draw(batch,wypowiedz[i],150,450-i*50);
		}
		batch.end();

		buttonImgCurrent = buttonImg;
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX()>=buttonRect.x && Gdx.input.getX()<=buttonRect.x+buttonRect.width && Gdx.input.getY()<=720-buttonRect.y && Gdx.input.getY()>=720-buttonRect.y-buttonRect.height){
				generate();
			}
		}
		if(Gdx.input.isTouched()){
			if(Gdx.input.getX()>=buttonRect.x && Gdx.input.getX()<=buttonRect.x+buttonRect.width && Gdx.input.getY()<=720-buttonRect.y && Gdx.input.getY()>=720-buttonRect.y-buttonRect.height){
				buttonImgCurrent=buttonImg2;
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		generator.dispose();
		font24.dispose();
		buttonImg.dispose();
		buttonImg2.dispose();
		buttonImgCurrent.dispose();
	}
}
