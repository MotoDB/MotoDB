package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RankingImpl implements Ranking {

	@Override
	public ObservableList<com.motodb.model.Ranking> getRankingByYearAndClass(int year, String className) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<com.motodb.model.Ranking> listRankings = FXCollections.observableArrayList();

		final String retrieve = "SELECT p.nomeMembro, p.cognomeMembro, p.nazione, p.numero, "
				+ "SUM(ps.valorePunteggio) as punteggio, ps.nomeMarcaMoto, c.nomeTeam " 
				+ "from PILOTA_IN_SESSIONE ps inner join PILOTA p "
				+ "on ps.codicePersonalePilota = p.codicePersonale "
				+ "inner join CONTRATTO_MARCA c on c.nomeMarca = ps.nomeMarcaMoto "
				+ "where ps.codiceSessione = ? && ps.annoCampionato = ? "
				+ "&& ps.nomeClasse = ? && c.annoCampionato = ? group by p.codicePersonale order by punteggio desc";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
			statement.setString(1, "RACE");
			statement.setInt(2, year);
			statement.setString(3, className);
			statement.setInt(4, year);
			try(final ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					com.motodb.model.Ranking ranking = new com.motodb.model.Ranking();
					ranking.setName(result.getString(1));
					ranking.setSurname(result.getString(2));
					ranking.setNation(result.getString(3));
					ranking.setNumber(result.getInt(4));
					ranking.setPoints(result.getInt(5));
					ranking.setManufacturer(result.getString(6));
					ranking.setTeam(result.getString(7));
					listRankings.add(ranking);
				}
			} catch (SQLException e) {
				try {
					AlertTypes alert = new AlertTypesImpl();
					alert.showError(e);
				} catch (ExceptionInInitializerError ei) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return listRankings;
	}

}
