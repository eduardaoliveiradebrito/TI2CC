package maven.demo2;

public class Ator {
	private int codigo;
	private String nome;
	private String filme;
	private char sexo;
	
	public Ator() {
		this.codigo = -1;
		this.nome = "";
		this.filme = "";
		this.sexo = '*';
	}
	public Ator(int codigo, String nome, String filme, char sexo) {
		this.codigo = codigo;
		this.nome = nome;
		this.filme = filme;
		this.sexo = sexo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", filme=" + filme + ", sexo=" + sexo + "]";
	}
}
