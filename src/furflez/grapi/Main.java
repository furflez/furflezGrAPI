package furflez.grapi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Main {

	public static BufferedImage img = null;
	public static ArrayList<Node> graph;

	public static void main(String[] args) {
		
		img = GrAPI.generateNewImage(800, 800);
		System.out.println("criando grafo...");

//		 graph = GrAPI.generateGraph();

		 int[] posX = {200, 200, 50, 300};
		 int[] posY = {175,50, 200,250};
		 graph = GrAPI.generateGraph(posX, posY);
		 
//		grafo = Utility.generateGraph("23il9k1mvqdwyc4gfxuhn7j5608oabszrtpe8!()");
//		grafo = Utility.generateGraph("abcdefghijklmnopqrstuvwxyz");
		
		for (Node nodo : graph) {
			System.out.println("Name: " + nodo.getName() + "\nX: "
					+ nodo.getX() + "\nY: " + nodo.getY() + "\n " + " ");
			
			img = GrAPI.drawNodos(nodo,img);
		}

//		ArrayList<Nodo> grafoAux = new ArrayList<Nodo>();
		GrAPI.associateNeighbors(graph);
		GrAPI.drawConnectionLines(graph,false,false);

		File outputfile = new File("image.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
