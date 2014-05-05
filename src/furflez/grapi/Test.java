package furflez.grapi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Test {

	public static String[] names = { "Miguel", "Davi", "Arthur", "Gabriel",
			"Pedro", "Lucas", "Matheus", "Bernardo", "Rafael", "Guilherme",
			"Enzo", "Felipe", "Gustavo", "Nicolas", "Heitor", "Samuel",
			"João Pedro", "Pedro Henrique", "Cauã", "Henrique", "Murilo",
			"Eduardo", "Vitor", "Daniel", "Lorenzo", "Vinicius", "Pietro",
			"João Vitor", "Leonardo", "Théo", "Caio", "Isaac", "Lucca", "João",
			"Davi Lucas", "Enzo Gabriel", "Yuri", "Bryan", "Thiago",
			"João Gabriel", "Benjamin", "Joaquim", "Emanuel", "Thomas", "Ryan",
			"Carlos Eduardo", "Rodrigo", "Ian", "Fernando", "Bruno", "Otávio",
			"Francisco", "Calebe", "Igor", "Antonio", "Erick", "João Lucas",
			"Luiz Felipe", "André", "Davi Lucca", "Kaique", "Nathan",
			"Luiz Miguel", "Breno", "Vitor Hugo", "João Guilherme", "Benício",
			"Augusto", "João Miguel", "Pedro Lucas", "Levi", "Anthony", "Yago",
			"Danilo", "Juan", "Kauê", "Diego", "Vicente", "Davi Luiz",
			"Luiz Gustavo", "Alexandre", "Raul", "Luan", "Diogo", "Marcelo",
			"Ricardo", "Luiz Henrique", "Henry", "Jonas", "Enrico",
			"Lucas Gabriel", "Renan", "Luiz Otávio", "Pedro Miguel", "William",
			"Ícaro", "Giovanni", "João Paulo", "Paulo", "Adryan", "Sophia",
			"Julia", "Alice", "Manuela", "Isabella", "Laura", "Maria Eduarda",
			"Giovanna", "Valentina", "Beatriz", "Luiza", "Helena",
			"Maria Luiza", "Isadora", "Mariana", "Gabriela", "Ana Clara",
			"Rafaela", "Maria Clara", "Isabelly", "Yasmin", "Ana Julia",
			"Lívia", "Lara", "Lorena", "Heloísa", "Melissa", "Sarah",
			"Ana Luiza", "Letícia", "Nicole", "Ana Beatriz", "Emanuelly",
			"Esther", "Lavínia", "Marina", "Cecília", "Rebeca", "Vitória",
			"Maria Fernanda", "Larissa", "Clara", "Carolina", "Bianca",
			"Alícia", "Fernanda", "Gabrielly", "Catarina", "Ana Laura",
			"Emilly", "Eduarda", "Amanda", "Pietra", "Agatha", "Milena",
			"Maria Alice", "Laís", "Maria Julia", "Maria", "Elisa", "Stella",
			"Maria Vitória", "Bruna", "Ana Sophia", "Bárbara", "Maria Cecília",
			"Olivia", "Nathalia", "Camila", "Ana Carolina", "Maitê", "Eloá",
			"Luana", "Luna", "Ana Lívia", "Brenda", "Alana", "Sophie", "Ana",
			"Isabel", "Mirella", "Juliana", "Marcela", "Isis", "Iara",
			"Antônia", "Kamilly", "Alexia", "Lis", "Maria Sophia", "Joana",
			"Clarice", "Ayla", "Caroline", "Antonella", "Evellyn", "Malu",
			"Maria Laura", "Mikaela", "Stefany", "Chuck Norris", "Jovem Nerd",
			"Sérgio", "Steven Seagal", "Jackie Chan", "Cumpadi Washington" };

	
	public static BufferedImage img = null;
	public static ArrayList<Node> graph;

	public static void main(String[] args) {

		img = GrAPI.generateNewImage(800, 800);
		System.out.println("criando grafo...");

		graph = GrAPI.generateGraph();

		// int[] posX = { 50 , 458, 140 };
		// int[] posY = { 100, 222, 50 };
		// graph = GrAPI.generateGraph(posX, posY);
 
		
		// Utility.generateGraph("23il9k1mvqdwyc4gfxuhn7j5608oabszrtpe8!()");
		// grafo = Utility.generateGraph("abcdefghijklmnopqrstuvwxyz");

		for (Node nodo : graph) {
			int randomName = (int) (Math.random() * (names.length - 1));
			nodo.setName(names[randomName]);
			System.out.println("Name: "+ nodo.getName() + "\nX: "
					+ nodo.getX() + "\nY: " + nodo.getY() + "\n " + " ");

			img = GrAPI.drawNodos(nodo, img);
		}

		// ArrayList<Nodo> grafoAux = new ArrayList<Nodo>();
		// String[] strs = { "0-1","1>2" };
		// GrAPI.associateNeighbors(graph, strs);
		GrAPI.associateNeighbors(graph, 2);
		GrAPI.drawConnectionLines(graph, true, true);
		GrAPI.writeTextFile(graph);
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
