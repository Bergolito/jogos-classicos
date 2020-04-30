package br.com.mvbos.lgj.lines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JogoLines extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int FPS = 1000 / 20;
	private static final int JANELA_ALTURA = 680;
	private static final int JANELA_LARGURA = 540;

	private static final int NUM_LINHAS = 9;
	private static final int NUM_COLUNAS = 9;
	private static final int LARGURA = 50;
	private static final int ALTURA = 50;

	private static final int TABULEIRO_COORD_X_INICIAL = 30;
	private static final int TABULEIRO_COORD_Y_INICIAL = 80;

	private static final int PROX_BOLAS_COORD_X_INICIAL = 30 + 3 * LARGURA;
	private static final int PROX_BOLAS_COORD_Y_INICIAL = 20;

	public static final Point pxyMouse = new Point();
	
	//private CoordenadasTabuleiro[][] coordenadasTabuleiro = new CoordenadasTabuleiro[NUM_LINHAS][NUM_COLUNAS];
	
	private JPanel tela;
	private Graphics2D g2d;
	private BufferedImage buffer;
	private boolean[] controleTecla = new boolean[5];

	//
//	private int linhaBase = 60;
	// Controle do espacamento entre os inimigos e outros elementos
	private int espacamento = 15;
	// Contador de inimigos destruidos
	//private int destruidos = 0;
//	private int dir;
//	private int totalInimigos;
	private int contadorEspera;
	boolean novaLinha;
	boolean moverInimigos;
	private int contador;
	private int pontos;
	private int level = 1;
	private Random rand = new Random();

	private LinhaColuna linhaColunaMouse;
	
	public JogoLines() {
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse released!");
				pxyMouse.x = e.getX();
				pxyMouse.y = e.getY();
				//System.out.println(pxyMouse.x+","+pxyMouse.y);	
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse pressed!");
				pxyMouse.x = e.getX();
				pxyMouse.y = e.getY();
				//System.out.println(pxyMouse.x+","+pxyMouse.y);	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse exited!");
				pxyMouse.x = e.getX();
				pxyMouse.y = e.getY();
				//System.out.println(pxyMouse.x+","+pxyMouse.y);	
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse entered!");
				pxyMouse.x = e.getX();
				pxyMouse.y = e.getY();
				//System.out.println(pxyMouse.x+","+pxyMouse.y);	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse clicked!");
				pxyMouse.x = e.getX();
				pxyMouse.y = e.getY();
				System.out.println("X= "+pxyMouse.x+", Y= "+pxyMouse.y);	
				
				linhaColunaMouse = retornaLinhaColunaClique(pxyMouse.x, pxyMouse.y);
				System.out.println("Linha = "+linhaColunaMouse.getLinha()+" | Coluna = "+linhaColunaMouse.getColuna());
			}
		});

		buffer = new BufferedImage(JANELA_LARGURA, JANELA_ALTURA, BufferedImage.TYPE_INT_RGB);

		g2d = buffer.createGraphics();

		tela = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(buffer, 0, 0, null);

				//desenhaTabuleiro(g);
			}
		};

		getContentPane().add(tela);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(JANELA_LARGURA, JANELA_ALTURA);
		setVisible(true);

	}

	/**
	 * 
	 * Coord X = 30 Coord Y = 236
		Coord X = 82 Coord Y = 236
		Coord X = 134 Coord Y = 236
		Coord X = 186 Coord Y = 236
		Coord X = 238 Coord Y = 236
		Coord X = 290 Coord Y = 236
		Coord X = 342 Coord Y = 236
		Coord X = 394 Coord Y = 236
		Coord X = 446 Coord Y = 236
	 *  Coord X = 30  Coord Y = 80
		Coord X = 82  Coord Y = 80
		Coord X = 134 Coord Y = 80
		Coord X = 186 Coord Y = 80
		Coord X = 238 Coord Y = 80
		Coord X = 290 Coord Y = 80
		Coord X = 342 Coord Y = 80
		Coord X = 394 Coord Y = 80
		Coord X = 446 Coord Y = 80
	 * @param x
	 * @param y
	 */
	private LinhaColuna retornaLinhaColunaClique(int x, int y) {
		int linha = -1;
		int coluna = -1;
		
		//X= 154, Y= 449
		// linhas = 80, 132, 184, 236, 288, 340, 392, 444, 496
		// colunas = 30, 82, 134, 186, 238, 290, 342, 394, 446
		int[] linhas =  {132, 184, 236, 288, 340, 392, 444, 496, 546};
		int[] colunas = {30, 82, 134, 186, 238, 290, 342, 394, 446};
		
		if(x >= colunas[0] && x <= colunas[1]-1) {
			coluna = 1;
		} 
		else if(x >= colunas[1] && x <= colunas[2]-1) {
			coluna = 2;
		} 
		else if(x >= colunas[2] && x <= colunas[3]-1) {
			coluna = 3;
		} 
		else if(x >= colunas[3] && x <= colunas[4]-1) {
			coluna = 4;
		} 
		else if(x >= colunas[4] && x <= colunas[5]-1) {
			coluna = 5;
		} 
		else if(x >= colunas[5] && x <= colunas[6]-1) {
			coluna = 6;
		} 
		else if(x >= colunas[6] && x <= colunas[7]-1) {
			coluna = 7;
		} 
		else if(x >= colunas[7] && x <= colunas[8]-1) {
			coluna = 8;
		} 
		else if(x >= colunas[8] && x <= colunas[8]+LARGURA-1) {
			coluna = 9;
		} 
		
		if(y >= linhas[0] && y <= linhas[1]-1 ) {
			linha = 1;
		}
		else if(y >= linhas[1] && y <= linhas[2]-1 ) {
			linha = 2;
		}
		else if(y >= linhas[2] && y <= linhas[3]-1 ) {
			linha = 3;
		}
		else if(y >= linhas[3] && y <= linhas[4]-1 ) {
			linha = 4;
		}
		else if(y >= linhas[4] && y <= linhas[5]-1 ) {
			linha = 5;
		}
		else if(y >= linhas[5] && y <= linhas[6]-1 ) {
			linha = 6;
		}
		else if(y >= linhas[6] && y <= linhas[7]-1 ) {
			linha = 7;
		}
		else if(y >= linhas[7] && y <= linhas[8]-1 ) {
			linha = 8;
		}
		else if(y >= linhas[8] && y <= linhas[8]+ALTURA-1 ) {
			linha = 9;
		}
		
		return new LinhaColuna(linha, coluna);
	}
	
	private void desenhaProximasBolas(Graphics g) {
		g.setColor(Color.GRAY);

		int coordX = PROX_BOLAS_COORD_X_INICIAL + 6;
		int coordY = PROX_BOLAS_COORD_Y_INICIAL;

		g.fillRect(coordX, coordY, LARGURA, ALTURA);
		g.fillRect(coordX + 2 + 1 * LARGURA, coordY, LARGURA, ALTURA);
		g.fillRect(coordX + 4 + 2 * LARGURA, coordY, LARGURA, ALTURA);
	}

	public void desenhaCirculo(Graphics g, int x, int y, int raio, Color color) {
		int diameter = raio * 2;
		g.setColor(color);
		
		// shift x and y by the radius of the circle in order to correctly center it
		g.fillOval(x - raio, y - raio, diameter, diameter);
	}

	private void desenhaTabuleiro(Graphics g) {
		desenhaProximasBolas(g);

		g.setColor(Color.GRAY);

		int coordX = TABULEIRO_COORD_X_INICIAL;
		int coordY = TABULEIRO_COORD_Y_INICIAL;
		
		for (int i = 0; i < NUM_LINHAS; i++) {
			for (int j = 0; j < NUM_COLUNAS; j++) {
				//System.out.println("Coord X = "+coordX+" Coord Y = "+coordY);
				//coordenadasTabuleiro[i][j] = new CoordenadasTabuleiro(coordX, coordY);
				
//				if(j == 0) {
//					System.out.println("X= "+coordX+" Y="+coordY);
//				}
				g.fillRect(coordX, coordY, LARGURA, ALTURA);
				coordX += (LARGURA + 2);
				if (j == (NUM_COLUNAS - 1)) {
					coordX = TABULEIRO_COORD_X_INICIAL;
					coordY += (ALTURA + 2);
				}
				
				
			}
		}

		//
		desenhaProximas03Bolas(g);
		
		// 
		try {
//			desenhaBolaTabuleiro(g, 1, 3, Color.BLUE);
//			desenhaBolaTabuleiro(g, 2, 3, Color.GREEN);
//			desenhaBolaTabuleiro(g, 3, 3, Color.CYAN);
//			desenhaBolaTabuleiro(g, 4, 3, Color.RED);
//			desenhaBolaTabuleiro(g, 5, 3, Color.YELLOW);
//			
//			desenhaBolaTabuleiro(g, 1, 5, Color.BLUE);
//			desenhaBolaTabuleiro(g, 2, 5, Color.GREEN);
//			desenhaBolaTabuleiro(g, 3, 5, Color.CYAN);
//			desenhaBolaTabuleiro(g, 4, 5, Color.RED);
//			desenhaBolaTabuleiro(g, 5, 5, Color.YELLOW);
//			
//			desenhaBolaTabuleiro(g, 3, 8, Color.BLUE);
//			desenhaBolaTabuleiro(g, 4, 8, Color.GREEN);
//			desenhaBolaTabuleiro(g, 5, 8, Color.CYAN);
//			desenhaBolaTabuleiro(g, 6, 8, Color.RED);
//			desenhaBolaTabuleiro(g, 7, 8, Color.YELLOW);
			
		} catch (Exception e) {
			System.out.println("As linhas e as colunas devem ser entre 1 e 9");
		}

	}

	private void desenhaProximas03Bolas(Graphics g) {
		int coordX = PROX_BOLAS_COORD_X_INICIAL + 25 + 6 ;
		int coordY = PROX_BOLAS_COORD_Y_INICIAL + 25;
		
		Color corCiano = new Color(0, 255, 255, 255);
		desenhaCirculo(g, coordX, coordY, 20, corCiano);
		desenhaCirculo(g, coordX+ 1*LARGURA, coordY, 20, Color.GREEN);
		desenhaCirculo(g, coordX+ 2*LARGURA, coordY, 20, Color.RED);
	}
	
	private void desenhaBolaTabuleiro(Graphics g, int linha, int coluna, Color cor) throws Exception {
		
		if(linha >= 1 && linha <= NUM_LINHAS && 
			coluna >= 1 && coluna <= NUM_COLUNAS) {
			
			g.setColor(cor);
			
			desenhaCirculo(g, 
					TABULEIRO_COORD_X_INICIAL + coluna*LARGURA - LARGURA/2 + (coluna*2), 
					TABULEIRO_COORD_Y_INICIAL + linha*ALTURA - ALTURA/2 + (linha*2), 20, cor);
		} else {
			throw new Exception("As linhas e as colunas devem ser entre 1 e 9");
		}

	}
	
	private void carregarJogo() {
		//
	}

	public void iniciarJogo() {
		long prxAtualizacao = 0;

		while (true) {
			if (System.currentTimeMillis() >= prxAtualizacao) {

				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, JANELA_LARGURA, JANELA_ALTURA);
				desenhaTabuleiro(g2d);
				
				try {
					desenhaBolaTabuleiro(g2d, linhaColunaMouse.getLinha(), linhaColunaMouse.getColuna(), Color.YELLOW);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tela.repaint();

				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}

	}

	public static void main(String[] args) {
		JogoLines jogo = new JogoLines();
		jogo.carregarJogo();
		jogo.iniciarJogo();
	}

}
