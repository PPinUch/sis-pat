package br.com.navita.commons.excecoes;

public class ExcecaoGeral extends Exception {

	private ExcecaoNivel nivel = ExcecaoNivel.GRAVE;
	
	public ExcecaoGeral(String msg) {
		super(msg);
	}

	public ExcecaoGeral(Integer nivel, String msg) {
		super(msg);
		this.nivel = ExcecaoNivel.getPorNivelErro(nivel);
	}

	public ExcecaoGeral(ExcecaoNivel codigo, String msg) {
		super(msg);
		this.nivel = codigo;
	}

	public ExcecaoGeral(String msg, Throwable e) {
		super(msg, e);
	}

	public ExcecaoGeral(Integer nivel, String msg, Throwable e) {
		super(msg, e);
		this.nivel = ExcecaoNivel.getPorNivelErro(nivel);
	}

	public ExcecaoGeral(ExcecaoNivel nivel, String msg, Throwable e) {
		super(msg, e);
		this.nivel = nivel;
	}

	public ExcecaoNivel getExcecaoNivel() {
		return nivel;
	}

	public Integer getNivel() {
		return nivel.getNivel();
	}

	public String getDescricaoNivel() {
		return nivel.getDescricao();
	}

	public boolean isGrave() {
		return ExcecaoNivel.GRAVE.equals(nivel);
	}
}
