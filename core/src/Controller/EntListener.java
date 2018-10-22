package Controller;

import java.util.ArrayList;



import Model.AEPaintBullet;
import Model.Entity;
import com.esotericsoftware.kryonet.Client;

public class EntListener {
	
	ArrayList<Entity> listenedList;
	Client client;
	public EntListener(ArrayList<Entity> listenedList, Client client)
	{
		this.listenedList = listenedList;
		this.client = client;
	}
	public void add(Entity ent)
	{
		if(ent instanceof AEPaintBullet)
			listenedList.add(0, ent);
		else
			listenedList.add(ent);
		
		client.sendTCP(ent);
	}
	
	

}
