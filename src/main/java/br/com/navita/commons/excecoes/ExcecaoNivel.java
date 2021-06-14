package br.com.navita.commons.excecoes;

/**
 * Níveis de exceção do sistema:
 * <ol>
 * <li><b>INTERACAO</b>: Parâmetro com campos vazios ou envio de registro duplicado</li>
 * <li><b>NEGOCIAL</b>: Infração de regra de negócio</li>
 * <li><b>GRAVE</b>: Erro de sistema, uma exceção inexperada</li>
 * </ol>
 */
public enum ExcecaoNivel {

	INTERACAO(1, "Erro de dados vazios ou inválidos"),
	NEGOCIAL(2, "Erro Negocial"),
	GRAVE(3, "Erro geral de sistema");

	/**
	 * 1 - INTERACAO (Campo obrigatório vazio, registro duplicado, etc.)
	 * 2 - NEGOCIAL (Houve uma infração a uma regra de negócio), 
	 * 3 - GRAVE (Exceções inexperadas), 
	 */
	private Integer nivel;
	private String descricao;

	private ExcecaoNivel(Integer nivel, String descricao) {
		this.nivel = nivel;
		this.descricao = descricao;
	}

	public Integer getNivel() {
		return nivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public static ExcecaoNivel getPorNivelErro(Integer nivel) {

		for(ExcecaoNivel codEx : values()) {
			if(codEx.getNivel().equals(nivel)) {
				return codEx;
			}
		}
		return GRAVE;
	}

}
