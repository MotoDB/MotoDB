package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Contract;
import com.motodb.view.AddContractControl.MemberType;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContractManagerImpl implements ContractManager {

	@Override
	public ObservableList<Contract> getContracts() {

		ObservableList<Contract> contracts = FXCollections.observableArrayList();

		contracts.addAll(this.getRiderContracts());
		contracts.addAll(this.getMechanicContracts());
		contracts.addAll(this.getEngineerContracts());

		return contracts;
	}

	@Override
	public ObservableList<Contract> getRiderContracts() {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<Contract> contracts = FXCollections.observableArrayList();

		final String retrieve = "select * from CONTRATTO_PILOTA";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Rider.toString(),
						result.getInt("codicePersonale"), result.getString("nomeTeam")));
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return contracts;

	}

	@Override
	public ObservableList<Contract> getMechanicContracts() {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<Contract> contracts = FXCollections.observableArrayList();

		final String retrieve = "select * from CONTRATTO_MECCANICO";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Mechanic.toString(),
						result.getInt("codicePersonale"), result.getString("nomeTeam")));
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return contracts;

	}

	@Override
	public ObservableList<Contract> getEngineerContracts() {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		ObservableList<Contract> contracts = FXCollections.observableArrayList();

		final String retrieve = "select * from CONTRATTO_INGEGNERE";

		try (final PreparedStatement statement = conn.prepareStatement(retrieve);
				final ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				contracts.add(new Contract(result.getInt("annoCampionato"), MemberType.Engineer.toString(),
						result.getInt("codicePersonale"), result.getString("nomeTeam")));
			}
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}

		return contracts;

	}

	@Override
	public void addRiderContract(Integer year, MemberType memberType, Integer member, String team, String className) {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		final String insert;

		insert = "insert into CONTRATTO_PILOTA(annoCampionato, codicePersonale, nomeTeam, nomeClasse) values (?,?,?,?)";
		
		try (final PreparedStatement statement = conn.prepareStatement(insert)) {
			statement.setInt(1, year);
			statement.setInt(2, member);
			statement.setString(3, team);
			statement.setString(4, className);
			statement.executeUpdate();
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addContract(Integer year, MemberType memberType, Integer member, String team) {

		final DBManager db = DBManager.getDB();
		final Connection conn = db.getConnection();

		final String insert;

		if (memberType.equals(MemberType.Engineer)) {
			insert = "insert into CONTRATTO_INGEGNERE(annoCampionato, codicePersonale, nomeTeam) values (?,?,?)";
		} else {
			insert = "insert into CONTRATTO_MECCANICO(annoCampionato, codicePersonale, nomeTeam) values (?,?,?)";
		}

		try (final PreparedStatement statement = conn.prepareStatement(insert)) {
			statement.setInt(1, year);
			statement.setInt(2, member);
			statement.setString(3, team);
			statement.executeUpdate();
		} catch (SQLException e) {
			try {
				AlertTypes alert = new AlertTypesImpl();
				alert.showError(e);
			} catch (ExceptionInInitializerError ei) {
				e.printStackTrace();
			}
		}
	}
	
	
}

