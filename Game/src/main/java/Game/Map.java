package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;	

class Map {
	private Rectangle[] countries;
	private Image[] Worldmap;
	private Image map;
	int size=500;
	int x,y;;
	
	public Map()
	{
	 map=new ImageIcon("C:/Users/USER/git/RiskGame/Game/Map.jpg").getImage();
	 countries= new Rectangle[500];
	 Worldmap= new Image[500];
	 
	 loadmap();
	 
	}
	private void loadmap()
	{ for(int i=0;i<size;i++)
	{ if(x>=500)
	{x=0;
	y+=20;
	}
	Worldmap[i] = map;
    countries[i] = new Rectangle(x, y, 20, 20);

    x += 20;
}
}

public void draw(Graphics g) {
for (int i = 0; i < size; i++) {
    g.drawImage(Worldmap[i], countries[i].x, countries[i].y, null);
}

	}
	
}
