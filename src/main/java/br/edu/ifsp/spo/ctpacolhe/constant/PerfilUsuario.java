package br.edu.ifsp.spo.ctpacolhe.constant;

public enum PerfilUsuario {
	ALUNO("ALUNO"),
	ADMIN("ADMIN");
	
	private final String codigo;

	private PerfilUsuario(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static PerfilUsuario getEnum(String codigo) {
		for (PerfilUsuario perfil : PerfilUsuario.values()) {
			if (perfil.getCodigo().equals(codigo)) {
				return perfil;
			}
		}
		return null;
	}
}