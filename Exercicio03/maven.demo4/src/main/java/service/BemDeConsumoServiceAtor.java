package service;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AtorDAO;
import dao.ProdutoDAO;
import model.BemDeConsumo;//////////////////////
import model.Produto;
import model.Ator;
import spark.Request;
import spark.Response;

public class BemDeConsumoServiceAtor {
	
	private AtorDAO bemDeConsumoDAO;
	
	public BemDeConsumoServiceAtor() {
		try {
			bemDeConsumoDAO = new AtorDAO("bemdeconsumo.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String filme = request.queryParams("filme");
		String sexo = request.queryParams("sexo");
		int id = bemDeConsumoDAO.getMaxId() + 1;

		BemDeConsumo bemDeConsumo = new BemDeConsumo(id, descricao, preco, quantidade, dataFabricacao, dataValidade);

		bemDeConsumoDAO.add(bemDeConsumo);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);
		
		if (bemDeConsumo != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<bemdeconsumo>\n" + 
            		"\t<id> " + bemDeConsumo.getId() + "</id>\n" +
            		"\t<descricao> " + bemDeConsumo.getDescricao() + "</descricao>\n" +
            		"\t<preco> " + bemDeConsumo.getPreco() + "</preco>\n" +
            		"\t<quantidade> " + bemDeConsumo.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao> " + bemDeConsumo.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade> " + bemDeConsumo.getDataValidade() + "</validade>\n" +
            		"</bemdeconsumo>\n";
        } else {
            response.status(404); // 404 Not found
            return "Produto " + id + " n�o encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);

        if (bemDeConsumo != null) {
        	bemDeConsumo.setDescricao(request.queryParams("descricao"));
        	bemDeConsumo.setPreco(Float.parseFloat(request.queryParams("preco")));
        	bemDeConsumo.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	bemDeConsumo.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	bemDeConsumo.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	bemDeConsumoDAO.update(bemDeConsumo);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Bem de consumo n�o encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);

        if (bemDeConsumo != null) {

            bemDeConsumoDAO.remove(bemDeConsumo);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Bem de consumo n�o encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<bensdeconsumo type=\"array\">");
		for (Produto produto : bemDeConsumoDAO.getAll()) {
			BemDeConsumo bemDeConsumo = (BemDeConsumo) produto;
			returnValue.append("\n<bemdeconsumo>\n" + 
            		"\t<id> " + bemDeConsumo.getId() + "</id>\n" +
            		"\t<descricao> " + bemDeConsumo.getDescricao() + "</descricao>\n" +
            		"\t<preco> " + bemDeConsumo.getPreco() + "</preco>\n" +
            		"\t<quantidade> " + bemDeConsumo.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao> " + bemDeConsumo.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade> " + bemDeConsumo.getDataValidade() + "</validade>\n" +
            		"</bemdeconsumo>\n");
		}
		returnValue.append("</bensdeconsumo>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();

	}

}
