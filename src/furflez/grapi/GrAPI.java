package furflez.grapi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GrAPI {
	private static ArrayList<Node> grafo;
	private static BufferedImage img;

	private static int[] seedRefX = { 131, 494, 669, 442, 235, 486, 230, 665,
			354, 668, 283, 79, 593, 623, 705, 335, 331, 221, 527, 503, 587,
			339, 621, 138, 498, 663, 412, 363, 687, 517, 10, 441, 530, 405,
			409, 439, 53, 693, 235, 208, 271, 598, 662, 71, 299, 688, 200, 346,
			241, 705, 325, 315, 545, 270, 274, 404, 145, 636, 271, 270, 656,
			443, 82, 12, 478, 514, 113, 572, 204, 61, 266, 42, 288, 153, 71,
			130, 339, 339, 481, 128, 126, 474, 409, 458, 66, 536, 483, 124, 10,
			641, 714, 445, 220, 630, 543, 326, 362, 12, 11, 600, 526, 347, 484,
			492, 407, 473, 345, 681, 522, 679, 409, 33, 208, 599, 73, 29, 161,
			484, 620, 502, 463, 628, 7, 7, 431, 403, 558, 140 };

	private static int[] seedRefY = { 617, 652, 92, 191, 708, 720, 512, 433,
			613, 347, 395, 38, 175, 290, 385, 38, 622, 489, 32, 360, 453, 257,
			12, 519, 418, 597, 134, 517, 509, 684, 355, 241, 198, 703, 561,
			573, 135, 115, 111, 238, 418, 479, 645, 149, 61, 145, 447, 419,
			108, 256, 9, 27, 296, 144, 128, 349, 221, 103, 484, 335, 79, 280,
			236, 348, 8, 414, 210, 103, 146, 254, 51, 222, 516, 533, 19, 312,
			232, 24, 277, 357, 700, 687, 598, 480, 94, 362, 509, 711, 585, 479,
			73, 600, 211, 523, 298, 610, 225, 326, 403, 296, 711, 586, 413,
			298, 615, 158, 191, 168, 107, 358, 363, 311, 691, 679, 646, 411,
			648, 589, 633, 690, 542, 443, 508, 637, 642, 191, 314, 76 };

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

			Node node = new Node();
			node.setId(i);
			node.setName("nodo_" + i);
			int x = (int) (Math.random() * (img.getWidth() - 80)) + 10;
			int y = (int) (Math.random() * (img.getHeight() - 80)) + 10;
			int[] position = { x, y };
			node.setPosition(verifyPosition(position));
			grafo.add(node);
		}

		return GrAPI.associateNeighbors(grafo);
	}

	/**
	 * Metodo que vai gerar o grafo com base nas posiçoes informadas de cada nó,
	 * o numero de nós é definido pelo menor numero de posições obtidas
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

	/**
	 * Método que gera um grafo a partir de uma string, caracteres repetidos
	 * serão ignorados, o posicionamento dos nós do grafo é definido pelo valor
	 * inteiro do caracter nos arrays seedRefX e seedRefY.
	 * 
	 * @param seed
	 *            string que será utilizada para gerar um grafo
	 * @return retorna o grafo completo baseado na seed informada
	 */
	public static ArrayList<Node> generateGraph(String seed) {

		grafo = new ArrayList<Node>();

		ArrayList<Character> cList = new ArrayList<Character>();
		char[] seedChars = seed.toCharArray();
		for (char c : seedChars) {
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
	 * método exclusivo da classe GrAPI que verifica as posições informadas para
	 * não ter conflitos e não permitir que dois nós sejam criados na mesma
	 * posição
	 * 
	 * @param position
	 *            recebe um array com duas posições, x e y
	 */
	private static int[] verifyPosition(int[] position) {
		if (grafo.size() >= 1) {
			for (Node node : grafo) {
				if (!(node.getX() == position[0] && node.getY() == position[1])) {
					break;
				} else {
					position[0] = (int) (Math.random() * (img.getWidth() - 80)) + 10;
					position[1] = (int) (Math.random() * (img.getHeight() - 80)) + 10;
					verifyPosition(position);
				}
			}
		}
		return position;
	}

	/**
	 * Método responsável por desenhar os nós do grafo em uma imagem.
	 * 
	 * @param node
	 *            nó a ser desenhado
	 * @param img
	 *            imagem que receberá o nó
	 * 
	 * @return imagem com o nó desenhado
	 * 
	 */
	public static BufferedImage drawNodos(Node node, BufferedImage img) {
		for (int i = -5; i <= 5; i++) {
			for (int j = -5; j <= 5; j++) {
				try {
					img.setRGB(node.getX() + i, node.getY() + j, new Color(0,
							0, 0).getRGB());
				} catch (Exception e) {
					System.out.println("out");
				}
			}
		}

		img.setRGB(node.getX(), node.getY(), new Color(255, 0, 0).getRGB());
		return img;

	}

	/**
	 * Método incompleto que se baseia em conectar os caracteres na sequencia
	 * definida na seed. Necessita implementar o caso do caracter repetido usar
	 * o mesmo nó e a partir desse nó conectar no seguinte.
	 * 
	 * @param grafo
	 *            grafo sem as conexões
	 * @param seed
	 * @return retorna o grafo completo com as conexões definidas
	 */
	public static ArrayList<Node> associateNeighbors(ArrayList<Node> grafo,
			String seed) {

		for (Node node : grafo) {

			connectToNear(grafo, node);
			if (!(node.getId() + 1 > grafo.size() - 1)) {
				// nodo.addNeighbor(grafo.get(nodo.getId() + 1));
				// grafo.get(nodo.getId() + 1).addNeighbor(nodo);
			}
		}

		return grafo;
	}

	/**
	 * Método que associa os nós com valor aleatório de conexões, o valor gerado
	 * será o numero maximo de conexões que um nó pode ter. O valor é a metade
	 * do numero de nós arredondado.
	 * 
	 * @param graph
	 *            recebe o grafo sem as conexões
	 * 
	 * @return retorna o grafo completo com as conexões definidas
	 */
	public static ArrayList<Node> associateNeighbors(ArrayList<Node> graph) {
		for (Node node : graph) {
			int randomNumber = (int) (Math.random() * Math
					.round(grafo.size() / 2));
			for (int i = 0; i < randomNumber; i++) {
				do {

					int randomId = (int) (Math.random() * graph.size());

					if (!node.getNeighbors().contains(graph.get(randomId))
							&& node.getId() != randomId) {
						node.addNeighbor(graph.get(randomId));
						graph.get(randomId).addNeighbor(node);

					}
				} while (node.getNeighbors().size() == 0);
			}
		}
		for (Node node : graph) {
			while (node.getNeighbors() == null || node.getNeighbors().isEmpty()) {
				int randomId = (int) (Math.random() * graph.size());
				if (!node.getNeighbors().contains(graph.get(randomId))
						&& node.getId() != randomId) {
					node.addNeighbor(graph.get(randomId));
					graph.get(randomId).addNeighbor(node);

				}
			}
		}
		return graph;
	}

	/**
	 * Método que associa os nós aleatóriamente com limite de conexões por nó.
	 * 
	 * @param graph
	 *            recebe o grafo sem as conexões
	 * @param maxNumberOfConnections
	 *            limite de conexões por nó.
	 * 
	 * @return retorna o grafo completo com as conexões definidas
	 * 
	 */
	public static ArrayList<Node> associateNeighbors(ArrayList<Node> graph,
			int maxNumberOfConnections) {
		for (Node node : graph) {
			for (int i = 0; i < maxNumberOfConnections; i++) {
				do {
					int randomId = (int) (Math.random() * graph.size());

					if (!node.getNeighbors().contains(graph.get(randomId))
							&& node.getId() != randomId) {
						node.addNeighbor(graph.get(randomId));
						graph.get(randomId).addNeighbor(node);

					}
				} while (node.getNeighbors().size() == 0);
			}
		}
		for (Node node : graph) {
			while (node.getNeighbors() == null || node.getNeighbors().isEmpty()) {
				int randomId = (int) (Math.random() * graph.size());
				if (!node.getNeighbors().contains(graph.get(randomId))
						&& node.getId() != randomId) {
					node.addNeighbor(graph.get(randomId));
					graph.get(randomId).addNeighbor(node);

				}
			}
		}
		return graph;
	}

	/**
	 * Metodo que associa os nós manualmente com a seguinte sintaxe: "1-2" irá
	 * criar uma conexão dupla entre o nó 1 e o 2, "1>2" irá criar uma conexão
	 * apenas de ida do 1 para o 2, "1<2" conexão apenas do 2 para o 1
	 * 
	 * @param graph
	 *            recebe o grafo sem as conexões
	 * @param connections
	 *            array informando na forma "x-y" , "x>y" ou "x<y", deve-se
	 *            passar a id do nó para realizar a conexão
	 * @return
	 */
	public static ArrayList<Node> associateNeighbors(ArrayList<Node> graph,
			String[] connections) {

		for (String string : connections) {
			try {
				if (string.contains("-")) {
					String[] strs = string.split("-");

					graph.get(Integer.parseInt(strs[0])).addNeighbor(
							graph.get(Integer.parseInt(strs[1])));
					graph.get(Integer.parseInt(strs[1])).addNeighbor(
							graph.get(Integer.parseInt(strs[0])));
				} else if (string.contains(">")) {
					String[] strs = string.split(">");

					graph.get(Integer.parseInt(strs[0])).addNeighbor(
							graph.get(Integer.parseInt(strs[1])));
				} else if (string.contains("<")) {
					String[] strs = string.split("<");

					graph.get(Integer.parseInt(strs[1])).addNeighbor(
							graph.get(Integer.parseInt(strs[0])));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		return graph;
	}

	/**
	 * Método responsavel por desenhar as linhas e informações contidas no
	 * grafo, tanto nome do nó quanto a distância de um nó ao outro
	 * 
	 * @param graph
	 *            recebe o grafo completo num arrayList que é usado para
	 *            verificar os nós contidos nele
	 * @param drawName
	 *            recebe um valor booleano que define se escreve ou não o nome
	 *            do nó sobre ele
	 * @param drawDistance
	 *            recebe um valor booleano que define se escreve ou não a
	 *            distância nas linhas
	 */
	public static void drawConnectionLines(ArrayList<Node> graph,
			boolean drawName, boolean drawDistance) {

		if (drawName) {
			for (Node node : graph) {
				Graphics g = img.createGraphics();
				g.setFont(new Font("default", Font.BOLD, 16));
				String str = node.getName() + ": " + node.getNeighbors().size();
				g.setColor(Color.RED);
				g.drawString(str, node.getX() - 5, node.getY() - 10);
				g.dispose();
			}
		}

		for (Node node : graph) {
			for (Node neighbor : node.getNeighbors()) {
				Graphics g = img.createGraphics();

				int red = (int) (Math.random() * 255);
				int green = (int) (Math.random() * 255);
				int blue = (int) (Math.random() * 255);
				g.setColor(new Color(red, green, blue));
				if (drawDistance) {
					float base = node.getX() - neighbor.getX();
					if (base < 0)
						base = base * -1;

					float altura = node.getY() - neighbor.getY();
					if (altura < 0)
						altura = altura * -1;

					long distance = Math.round(Math.sqrt(Math.pow(base, 2)
							+ Math.pow(altura, 2)));

					int pointx = Math
							.round((node.getX() + neighbor.getX()) / 2);
					int pointy = Math
							.round((node.getY() + neighbor.getY()) / 2);

					g.drawString(distance + "", pointx, pointy);
				}
				g.drawLine(node.getX(), node.getY(), neighbor.getX(),
						neighbor.getY());
				g.dispose();

			}
		}

	}

	/**
	 * Cria uma imagem vazia de fundo branco com altura e largura definidas por
	 * parâmetro
	 * 
	 * @param width
	 *            largura
	 * @param height
	 *            altura
	 * @return imagem gerada branca
	 */
	public static BufferedImage generateNewImage(int width, int height) {
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				img.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		GrAPI.img = img;
		return img;
	}

	/**
	 * Baseado na string passada como parâmetro, os arrays seedRefX e seedRefY
	 * são consultados pelo valor inteiro de cada caracter da string, dessa
	 * forma cada caracter possui uma posição unica para gerar um grafo
	 * 
	 * @param seed
	 *            string utilizada para a criação do grafo
	 * @return retorna o grafo completo com as conexões definidas
	 */
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

	/**
	 * Este metodo ainda não está funcionando como esperado, a ideia é detectar
	 * e conectar o nó em um nó próximo a ele sem voltar ao nó anterior.
	 * (necessita de revisão)
	 * 
	 * @param graph
	 *            grafo sem as conexões
	 * @param nodo
	 *            nó a encadear no próximo (a ideia era ser recursivo)
	 * @return retorna o nó para caso da função ser recursiva
	 */
	public static Node connectToNear(ArrayList<Node> graph, Node nodo) {
//		int value = 500;
//		for (Node node : graph) {
//
//			if (node.getX() + value >= nodo.getX()
//					&& node.getX() - value <= nodo.getX()
//					&& node.getY() + value >= nodo.getY()
//					&& node.getY() - value <= nodo.getY()) {
//				if (node.getNeighbors().size() <= 1
//						&& nodo.getNeighbors().size() <= 1
//						&& node.getId() != nodo.getId()) {
//					if (!node.getNeighbors().contains(nodo)) {
//						nodo.addNeighbor(node);
//						node.addNeighbor(nodo);
//					}
//				}
//			}
//		}
//		for (Node nodo2 : graph) {
//			if (!nodo2.getNeighbors().isEmpty())
//
//				nodo2.getNeighbors().get(0).addNeighbor(nodo2);
//		}
//
		connect(nodo);
		return nodo;
	}
	
	private static Node connect(Node node){
		return node;
	}

}
