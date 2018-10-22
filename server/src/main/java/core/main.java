/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Model.AEPaintBullet;
import Model.BasicArmor;
import Model.BasicCape;
import Model.BasicShoes;
import Model.BasicShooter;
import Model.Bone;
import Model.Bullet;
import Model.BulletState;
import Model.CloudBullet;
import Model.CloudShooter;
import Model.Collidable;
import Model.Collision;
import Model.DStates;
import Model.DashCape;
import Model.DoubleJumpShoes;
import Model.Entity;
import Model.EquipType;
import Model.Equipable;
import Model.EquipableItem;
import Model.GiantShoes;
import Model.HStates;
import Model.Hero;
import Model.HeroArr;
import Model.HeroSkeleton;
import Model.IDResponse;
import Model.PaintShooter;
import Model.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Purco
 */
public class main {
    public static int counter = 0;
    private HeroArr heroArr ;
    public static void main(String args[]) throws IOException{
      Server server = new Server();
       Kryo kryo = server.getKryo();
		 	
		 kryo.register(AEPaintBullet.class);
		 	kryo.register(ArrayList.class);
			kryo.register(BasicArmor.class);
			kryo.register(BasicCape.class);
			kryo.register(BasicShoes.class);
			kryo.register(BasicShooter.class);
			kryo.register(Bone.class);
			kryo.register(Model.Bone[].class);
			
			kryo.register(Bullet.class);
			kryo.register(BulletState.class);
			kryo.register(CloudBullet.class);
			kryo.register(CloudShooter.class);
			kryo.register(Collision.class);
			kryo.register(Collidable.class);
			kryo.register(DashCape.class);
			kryo.register(DoubleJumpShoes.class);
			kryo.register(DStates.class);
			kryo.register(Entity.class);
			kryo.register(Equipable.class);
			kryo.register(EquipableItem.class);
			kryo.register(EquipType.class);
			kryo.register(GiantShoes.class);
			
			
			kryo.register(Hero.class);
			kryo.register(HeroSkeleton.class);
			kryo.register(HStates.class);
			kryo.register(IDResponse.class);
			
			
			kryo.register(PaintShooter.class);
			kryo.register(Vector2.class);
		
    server.start();
    server.bind(54555, 54777);
      server.addListener(new Listener() {
       public void received (Connection connection, Object object) {
           if (object instanceof Hero){
               Hero hero = (Hero)object;
               if(hero.id == -1){
                   IDResponse response = new IDResponse();
                   response.id = counter;
                   hero.id = counter;
                   response.hero = hero;
                   counter++;
                   server.sendToTCP(connection.getID(), response);
                   
               }
               else{
               server.sendToAllExceptTCP(connection.getID(), object);
               }
           }
          
          server.sendToAllExceptTCP(connection.getID(), object);
       }
    });
    }
    
}
