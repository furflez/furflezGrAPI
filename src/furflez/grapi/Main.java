package furflez.grapi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Main {

	public static BufferedImage img = null;
	public static ArrayList<Nodo> grafo;

	public static void main(String[] args) {
		System.out.println();
		img = GrAPI.generateNewImage(800, 800);
		System.out.println("criando grafo...");

		 grafo = GrAPI.generateGraph();

		// int[] posX = {50, 250, 250, 50};
		// int[] posY = {50,50, 250,250};
//		grafo = Utility.generateGraph("23il9k1mvqdwyc4gfxuhn7j5608oabszrtpe8!()");
//		grafo = Utility.generateGraph("abcdefghijklmnopqrstuvwxyz");
		
		for (Nodo nodo : grafo) {
			System.out.println("Name: " + nodo.getName() + "\nX: "
					+ nodo.getX() + "\nY: " + nodo.getY() + "\n " + " ");
			
			GrAPI.drawNodos(nodo);
		}

//		ArrayList<Nodo> grafoAux = new ArrayList<Nodo>();
//		grafoAux = Utility.associateNeighbors(grafo, 1);

		GrAPI.drawConnectionLines(grafo);

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
