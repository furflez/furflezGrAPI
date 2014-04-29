package furflez.grapi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GrAPI {
	private static ArrayList<Node> grafo;

	public static int[] seedRefX = { 131, 494, 669, 442, 235, 486, 230, 665,
			354, 668, 283, 79, 593, 623, 705, 335, 331, 221, 527, 503, 587,
			339, 621, 138, 498, 663, 412, 363, 687, 517, 10, 441, 530, 405,
			409, 439, 53, 693, 235, 208, 271, 598, 662, 71, 299, 688, 200, 346,
			241, 705, 325, 315, 545, 270, 274, 404, 145, 636, 271, 270, 656,
			443, 82, 12, 478, 514, 113, 572, 204, 61, 266, 42, 288, 153, 71,
			130, 339, 339, 481, 128, 126, 474, 409, 458, 66, 536, 483, 124, 10,
			641, 714, 445, 220, 630, 543, 326, 362, 12, 11, 600, 526, 347, 484,
			492, 407, 473, 345, 681, 522, 679, 409, 33, 208, 599, 73, 29, 161,
			484, 620, 502, 463, 628, 7, 7, 431, 403, 558, 140 };

	public static int[] seedRefY = { 617, 652, 92, 191, 708, 720, 512, 433,
			613, 347, 395, 38, 175, 290, 385, 38, 622, 489, 32, 360, 453, 257,
			1, 519, 418, 597, 134, 517, 509, 684, 355, 241, 198, 703, 561, 573,
			135, 115, 111, 238, 418, 479, 645, 149, 61, 145, 447, 419, 108,
			256, 9, 27, 296, 144, 128, 349, 221, 103, 484, 335, 79, 280, 236,
			348, 8, 414, 210, 103, 146, 254, 51, 222, 516, 533, 19, 312, 232,
			24, 277, 357, 700, 687, 598, 480, 94, 362, 509, 711, 585, 479, 73,
			600, 211, 523, 298, 610, 225, 326, 403, 296, 711, 586, 413, 298,
			615, 158, 191, 168, 107, 358, 363, 311, 691, 679, 646, 411, 648,
			589, 633, 690, 542, 443, 508, 637, 642, 191, 314, 76 };

	/**
	 * método que gera o grafo aleatóriamente, tanto os nós com suas posições
	 * 
	 * @return grafo completo em um arrayList
	 */
	public static ArrayList<Node> generateGraph() {

		grafo = new ArrayList<Node>();

		System.out.println("gerando os nós...");
		int randomNumber = 10;// (int) (Math.random() * 200) + 1;

		System.out.println("numero de nós: " + randomNumber);

		for (int i = 0; i < randomNumber; i++) {

			Node nodo = new Node();
			nodo.setId(i);
			nodo.setName("nodo_" + i);
			int x = (int) (Math.random() * (Main.img.getWidth() - 80)) + 10;
			int y = (int) (Math.random() * (Main.img.getHeight() - 80)) + 10;
			int[] position = { x, y };
			nodo.setPosition(verifyPosition(position));
			grafo.add(nodo);

		}

		return GrAPI.associateNeighbors(grafo);
	}

	/**
	 * Metodo que vai gerar o grafo com base nas posiçoes informadas de cada nó
	 * 
	 * @param positionX
	 *            posicão horizontal do nó
	 * @param positionY
	 *            posição vertical do nó
	 * @return
	 */
	public static ArrayList<Node> generateGraph(int[] positionX, int[] positionY) {

		grafo = new ArrayList<Node>();

		System.out.println("gerando os nós...");
		int nodeNumbers = positionX.length;

		System.out.println("numero de nós: " + nodeNumbers);

		for (int i = 0; i < nodeNumbers; i++) {

			Node nodo = new Node();
			nodo.setId(i);
			nodo.setName("nodo_" + i);

			int[] position = { positionX[i], positionY[i] };
			nodo.setPosition(position);
			grafo.add(nodo);

		}
		return grafo;
	}

	public static ArrayList<Node> generateGraph(String seed) {

		char[] seedChars = seed.toCharArray();
		grafo = new ArrayList<Node>();

		ArrayList<Character> cList = new ArrayList<Character>();
		char[] cArray = seed.toCharArray();
		for (char c : cArray) {
			if (!cList.contains(c))
				cList.add(c);
		}

		int[] positionX = new int[cList.size()];
		int[] positionY = new int[cList.size()];
		for (int i = 0; i < cList.size(); i++) {
			positionX[i] = GrAPI.seedRefX[cList.get(i)];
			positionY[i] = GrAPI.seedRefY[cList.get(i)];
		}

		System.out.println("gerando os nós...");
		int nodeNumbers = positionX.length;

		System.out.println("numero de nós: " + nodeNumbers);

		for (int i = 0; i < nodeNumbers; i++) {

			Node nodo = new Node();
			nodo.setId(i);
			nodo.setName(cList.get(i) + "");

			int[] position = { positionX[i], positionY[i] };
			nodo.setPosition(position);
			grafo.add(nodo);

		}
		associateNeighbors(grafo, seed);
		return grafo;
	}

	/**
	 * método exclusivo da classe Utility que verifica as posições informadas
	 * para não ter conflitos e não permitir que dois nós sejam criados na mesma
	 * posição
	 * 
	 * @param position
	 *            recebe um array com duas posições, x e y
	 */
	private static int[] verifyPosition(int[] position) {
		if (grafo.size() >= 1) {
			for (Node nodo : grafo) {
				if (!(nodo.getX() == position[0] && nodo.getY() == position[1])) {
					break;
				} else {
					position[0] = (int) (Math.random() * (Main.img.getWidth() - 80)) + 10;
					position[1] = (int) (Math.random() * (Main.img.getHeight() - 80)) + 10;
					verifyPosition(position);
				}
			}
		}
		return position;
	}

	public static void drawNodos(Node nodo) {
		for (int i = -5; i <= 5; i++) {
			for (int j = -5; j <= 5; j++) {
				try {
					Main.img.setRGB(nodo.getX() + i, nodo.getY() + j,
							new Color(0, 0, 0).getRGB());
				} catch (Exception e) {
					System.out.println("out");
				}
			}
		}
		
		Main.img.setRGB(nodo.getX(), nodo.getY(), new Color(255, 0, 0).getRGB());

	}

	public static ArrayList<Node> associateNeighbors(ArrayList<Node> grafo,
			String seed) {

		for (Node nodo : grafo) {

			connectToNear(grafo, nodo);
			if (!(nodo.getId() + 1 > grafo.size() - 1)) {
				// nodo.addNeighbor(grafo.get(nodo.getId() + 1));
				// grafo.get(nodo.getId() + 1).addNeighbor(nodo);
			}
		}

		return grafo;
	}

	public static ArrayList<Node> associateNeighbors(ArrayList<Node> grafo) {
		for (Node nodo : grafo) {
			int randomNumber = (int) (Math.random() * Math
					.round(grafo.size() / 2));
			for (int i = 0; i < randomNumber; i++) {
				do {

					int randomId = (int) (Math.random() * grafo.size());

					if (!nodo.getNeighbors().contains(grafo.get(randomId))
							&& nodo.getId() != randomId) {
						nodo.addNeighbor(grafo.get(randomId));
						grafo.get(randomId).addNeighbor(nodo);

					}
				} while (nodo.getNeighbors().size() == 0);
			}
		}
		for (Node nodo : grafo) {
			while (nodo.getNeighbors() == null || nodo.getNeighbors().isEmpty()) {
				int randomId = (int) (Math.random() * grafo.size());
				if (!nodo.getNeighbors().contains(grafo.get(randomId))
						&& nodo.getId() != randomId) {
					nodo.addNeighbor(grafo.get(randomId));
					grafo.get(randomId).addNeighbor(nodo);

				}
			}
		}
		return grafo;
	}

	public static void drawConnectionLines(ArrayList<Node> n) {

		for (Node nodo : n) {
			for (Node neighbor : nodo.getNeighbors()) {
				Graphics g = Main.img.createGraphics();
				g.setFont(new Font("default", Font.BOLD, 16));

				String str = nodo.getName() + ": " + nodo.getNeighbors().size();
				g.setColor(Color.RED);
				g.drawString(str, nodo.getX() - 5, nodo.getY() - 10);

				float base = nodo.getX() - neighbor.getX();
				if (base < 0)
					base = base * -1;

				float altura = nodo.getY() - neighbor.getY();
				if (altura < 0)
					altura = altura * -1;

				long distance = Math.round(Math.sqrt(Math.pow(base, 2)
						+ Math.pow(altura, 2)));

				int pointx = Math.round((nodo.getX() + neighbor.getX()) / 2);
				int pointy = Math.round((nodo.getY() + neighbor.getY()) / 2);
				// Color[] colors = {Color.RED, Color.GREEN, Color.BLUE,
				// Color.DARK_GRAY, Color.MAGENTA, Color.ORANGE, Color.CYAN};
				int red = (int) (Math.random() * 255);
				int green = (int) (Math.random() * 255);
				int blue = (int) (Math.random() * 255);
				g.setColor(new Color(red, green, blue));
				g.drawLine(nodo.getX(), nodo.getY(), neighbor.getX(),
						neighbor.getY());
				g.drawString(distance + "", pointx, pointy);
				g.dispose();

			}
		}

	}

	public static BufferedImage generateNewImage(int width, int height) {
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				img.setRGB(x, y, Color.WHITE.getRGB());
			}
		}

		return img;
	}

	public static int[][] generateSeed(String seed) {
		ArrayList<Character> cList = new ArrayList<Character>();
		char[] cArray = seed.toCharArray();
		for (char c : cArray) {
			if (!cList.contains(c))
				cList.add(c);
		}
		int[] posX = new int[cList.size()];
		int[] posY = new int[cList.size()];
		for (int i = 0; i < cList.size(); i++) {
			posX[i] = GrAPI.seedRefX[cList.get(i)];
			posY[i] = GrAPI.seedRefY[cList.get(i)];
		}

		int[][] r = { posX, posY };
		return r;
	}

	public static Node connectToNear(ArrayList<Node> grafo, Node nodo) {
		int value = 500;
		for (Node node : grafo) {
			
			if (node.getX() + value >= nodo.getX()
					&& node.getX() - value <= nodo.getX()
					&& node.getY() + value >= nodo.getY()
					&& node.getY() - value <= nodo.getY()) {
				if (node.getNeighbors().size() <= 1
						&& nodo.getNeighbors().size() <= 1
						&& node.getId() != nodo.getId()) {
					if (!node.getNeighbors().contains(nodo)) {
						nodo.addNeighbor(node);
						node.addNeighbor(nodo);
					}
				}
			}
		}
		for (Node nodo2 : grafo) {
			if (!nodo2.getNeighbors().isEmpty())

				nodo2.getNeighbors().get(0).addNeighbor(nodo2);
		}

		return nodo;
	}

}
