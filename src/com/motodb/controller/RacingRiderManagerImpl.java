package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.RacingRider;
import com.motodb.model.Team;
import com.motodb.model.Tyre;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RacingRiderManagerImpl implements RacingRiderManager {

	@Override
	public void addRacingRider(int year, Date weekendDate, String className, String sessionCode, String fastestTime,
			Integer position, boolean finished, int personalCode, String manufacturer, String bikeModel, int points, ObservableList<Tyre> tyres) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		final String insert = "insert into PILOTA_IN_SESSIONE(annoCampionato, dataInizioWeekend, nomeClasse, codiceSessione, tempoVeloce, indicePosizione, posizionato, codicePersonalePilota, nomeMarcaMoto, modelloMoto, valorePunteggio) values (?,?,?,?,?,?,?,?,?,?,?)";
		try (final PreparedStatement statement = conn.prepareStatement(insert)) {
			statement.setInt(1, year);
			statement.setDate(2, weekendDate);
			statement.setString(3, className);
			statement.setString(4, sessionCode);
			statement.setString(5, fastestTime);
			statement.setInt(6, position);
			statement.setBoolean(7, finished);
			statement.setInt(8, personalCode);
			statement.setString(9, manufacturer);
			statement.setString(10, bikeModel);
			statement.setInt(11, points);
			statement.executeUpdate();
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}
		
		final String insert2 = "insert into UTILIZZO_PNEUMATICO(annoCampionato, dataInizioWeekend, nomeClasse, codiceSessione, indicePosizione, marca, modello, mescola) values (?,?,?,?,?,?,?,?)";
		
		tyres.forEach(e -> {
			try (final PreparedStatement statement2 = conn.prepareStatement(insert2)) {
				statement2.setInt(1, year);
	            statement2.setDate(2, weekendDate);
	            statement2.setString(3, className);
	            statement2.setString(4, sessionCode);
	            statement2.setInt(5, position);
	            statement2.setString(6, e.getMake());
	            statement2.setString(7, e.getModel());
	            statement2.setString(8, e.getCompound());
				statement2.executeUpdate();
			} catch (SQLException ex) {
				try {
					AlertTypes alert = new AlertTypesImpl();
					alert.showError(ex);
				} catch (ExceptionInInitializerError ei) {
					ex.printStackTrace();
				}
			}
		});
		

	}

	@Override
	public ObservableList<RacingRider> getRacingRiders() {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<RacingRider> list = FXCollections.observableArrayList();
		final String retrieve = "select * from PILOTA_IN_SESSIONE";
		try (final PreparedStatement statement = conn.prepareStatement(retrieve);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				list.add(new RacingRider(result.getInt("annoCampionato"), result.getDate("dataInizioWeekend"),
						result.getString("nomeClasse"), result.getString("codiceSessione"),
						result.getString("tempoVeloce"), result.getInt("indicePosizione"),
						result.getBoolean("posizionato"), result.getInt("codicePersonalePilota"),
						result.getString("nomeMarcaMoto"), result.getString("modelloMoto"),
						result.getInt("valorePunteggio")));
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ObservableList<RacingRider> getRidersFromYearWeekSess(int year, Date weekend, String session) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<RacingRider> list = FXCollections.observableArrayList();
		final String retrieve = "select * from PILOTA_IN_SESSIONE where annoCampionato = ? && dataInizioWeekend = ? && codiceSessione = ?";

		try {
			PreparedStatement statement = null;
			ResultSet result = null;
			statement = conn.prepareStatement(retrieve);
			statement.setInt(1, year);
			statement.setDate(2, weekend);
			statement.setString(3, session);
			result = statement.executeQuery();

			while (result.next()) {

				list.add(new RacingRider(result.getInt("annoCampionato"), result.getDate("dataInizioWeekend"),
						result.getString("nomeClasse"), result.getString("codiceSessione"),
						result.getString("tempoVeloce"), result.getInt("indicePosizione"),
						result.getBoolean("posizionato"), result.getInt("codicePersonalePilota"),
						result.getString("nomeMarcaMoto"), result.getString("modelloMoto"),
						result.getInt("valorePunteggio")));

			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			AlertTypes alert = new AlertTypesImpl();
			alert.showError(e);
		}

		return list;
	}

	@Override
	public Team getTeamByRiderAndYear(int rider, int year) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		Team team = new Team();
		final String retrieve = "select nomeTeam from CONTRATTO_PILOTA c, PILOTA ps where c.codicePersonale = ? && ps.codicePersonale = ? && c.annoCampionato = ?";
		try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
			statement.setInt(1, rider);
			statement.setInt(2, rider);
			statement.setInt(3, year);
			try (final ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					
					team.setName(result.getString(1));
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
		
		return team;
	}

	@Override
	public String getRiderNameByCode(int code) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		String name = null;
		final String retrieve = "select p.sigla from PILOTA p where p.codicePersonale = ?";
		try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
			statement.setInt(1, code);
			try (final ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					name = result.getString(1);
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
		
		return name;
	}

}
