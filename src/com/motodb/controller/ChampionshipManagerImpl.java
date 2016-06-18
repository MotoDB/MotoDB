package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.motodb.model.Championship;
import com.motodb.model.ChampionshipsView;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChampionshipManagerImpl implements ChampionshipManager {

	@Override
	public void addChampionship(final int year, final int edition, final ObservableList<String> classes,
			final ObservableList<String> sponsors) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();
		boolean ok = true;

		final String insert = "insert into CAMPIONATO(anno, edizione) values (?,?)";
		try (final PreparedStatement statement = conn.prepareStatement(insert)) {
			statement.setInt(1, year);
			statement.setInt(2, edition);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		if (ok) {
			final String insert2 = "insert into CLASSE_IN_CAMPIONATO(annoCampionato, nomeClasse) values (?,?)";
			classes.forEach(e -> {
				try (final PreparedStatement statement2 = conn.prepareStatement(insert2)) {
					statement2.setInt(1, year);
					statement2.setString(2, e);
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

			final String insert3 = "insert into SPONSORIZZAZIONE(annoCampionato, nomeSponsor) values (?,?)";
			sponsors.forEach(e -> {
				try (final PreparedStatement statement3 = conn.prepareStatement(insert3)) {
					statement3.setInt(1, year);
					statement3.setString(2, e);
					statement3.executeUpdate();
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

	}

	@Override
	public ObservableList<Championship> getChampionships() {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<Championship> listChampionship = FXCollections.observableArrayList();
		final String retrieve = "select * from CAMPIONATO order by anno desc ";
		try (final PreparedStatement statement = conn.prepareStatement(retrieve);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				Championship championship = new Championship();
				championship.setYear(result.getInt("anno"));
				championship.setEdition(result.getInt("edizione"));
				listChampionship.add(championship);
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return listChampionship;

	}

	public ObservableList<String> getClassesNames(int year) {
		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<String> list = FXCollections.observableArrayList();
		final String retrieve = "SELECT cla.nomeClasse " + "from CLASSE_IN_CAMPIONATO c, CAMPIONATO ca, CLASSE cla "
				+ "WHERE c.annoCampionato=? " + "AND ca.anno=? " + "AND cla.nomeClasse = c.nomeClasse "
				+ "order by indiceImportanza";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
			statement.setInt(1, year);
			statement.setInt(2, year);
			try (final ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					list.add(result.getString("nomeClasse"));
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

		return list;

	}

	public ObservableList<String> getSponsorsNames(int year) {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<String> listSponsor = FXCollections.observableArrayList();
		final String retrieve = "SELECT sponsor.nomeSponsor "
				+ "from SPONSOR sponsor, SPONSORIZZAZIONE s, CAMPIONATO cam " + "where s.annoCampionato = ? "
				+ "AND cam.anno = ? " + "and s.nomeSponsor = sponsor.nomeSponsor ";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
			statement.setInt(1, year);
			statement.setInt(2, year);
			try (final ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					listSponsor.add(result.getString("nomeSponsor"));
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

		return listSponsor;
	}

	public ObservableList<ChampionshipsView> getChampionshipViews() {

		ObservableList<ChampionshipsView> listCh = FXCollections.observableArrayList();

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		final String insert = "SELECT anno, edizione, group_concat(distinct c.nomeClasse SEPARATOR ', ') as 'classi', group_concat(distinct spo.nomeSponsor SEPARATOR ', ') as 'sponsor' "
				+ "from CLASSE_IN_CAMPIONATO c, CAMPIONATO ca, SPONSORIZZAZIONE spo "
				+ "WHERE c.annoCampionato=ca.anno " + "AND c.annoCampionato=spo.annoCampionato " + "group by anno";

		try (final PreparedStatement statement = conn.prepareStatement(insert);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				ChampionshipsView view = new ChampionshipsView();
				view.setYear(result.getInt("anno"));
				view.setEdition(result.getInt("edizione"));
				view.setClasses(result.getString(3));
				view.setSponsors(result.getString(4));
				listCh.add(view);
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}
		return listCh;
	}

}
