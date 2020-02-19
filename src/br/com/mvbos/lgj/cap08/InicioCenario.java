package br.com.mvbos.lgj.cap08;

import java.awt.Graphics2D;

import br.com.mvbos.lgj.cap08.base.CenarioPadrao;
import br.com.mvbos.lgj.cap08.base.Menu;
import br.com.mvbos.lgj.cap08.base.Util;

public class InicioCenario extends CenarioPadrao {

	public InicioCenario(int largura, int altura) {
		super(largura, altura);
	}

	private Menu menuJogo;

	@Override
	public void carregar() {

		menuJogo = new Menu("Modo");
		menuJogo.addOpcoes("Sem radar", "Com radar", "Teleguiado");

		Util.centraliza(menuJogo, largura, altura);

		menuJogo.setPy(menuJogo.getPy() + menuJogo.getAltura());

		menuJogo.setAtivo(true);
		menuJogo.setSelecionado(true);
	}

	@Override
	public void descarregar() {
		Jogo.nivel = menuJogo.getOpcaoId();
	}

	@Override
	public void atualizar() {
		if (Jogo.controleTecla[Jogo.Tecla.CIMA.ordinal()] || Jogo.controleTecla[Jogo.Tecla.BAIXO.ordinal()]) {

		} else if (Jogo.controleTecla[Jogo.Tecla.ESQUERDA.ordinal()] || Jogo.controleTecla[Jogo.Tecla.DIREITA.ordinal()]) {
			menuJogo.setTrocaOpcao(Jogo.controleTecla[Jogo.Tecla.ESQUERDA.ordinal()]);

		}

		Jogo.liberaTeclas();

	}

	@Override
	public void desenhar(Graphics2D g) {
		menuJogo.desenha(g);
	}

}