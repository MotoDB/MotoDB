package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Mechanic;
import com.motodb.model.Member;
import com.motodb.model.Rider;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberManagerImpl implements MemberManager {

    @Override
    public void addRider(int personalCode, String firstName, String lastName, String photo, String birthplace,
            String state, String role, java.sql.Date dateOfBirth, int number, int weigth, int heigth, String acronym) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        java.sql.PreparedStatement statement = null;
        final String insert = "insert into PILOTA(foto, codicePersonale, nomeMembro, cognomeMembro, dataNascita, luogoNascita, nazione, ruolo, altezza, peso, numero, sigla) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, photo);
            statement.setInt(2, personalCode);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setDate(5, dateOfBirth);
            statement.setString(6, birthplace);
            statement.setString(7, state);
            statement.setString(8, role);
            statement.setInt(9, heigth);
            statement.setInt(10, weigth);
            statement.setInt(11, number);
            statement.setString(12, acronym);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }

    }

    @Override
    public void addEngineer(int personalCode, String firstName, String lastName, String photo, String birthplace,
            String state, String role, java.sql.Date dateOfBirth) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        java.sql.PreparedStatement statement = null;
        final String insert = "insert into INGEGNERE(foto, codicePersonale, nomeMembro, cognomeMembro, dataNascita, luogoNascita, nazione, ruolo) values (?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, photo);
            statement.setInt(2, personalCode);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setDate(5, dateOfBirth);
            statement.setString(6, birthplace);
            statement.setString(7, state);
            statement.setString(8, role);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }

    }

    @Override
    public void addMechanic(int personalCode, String firstName, String lastName, String photo, String birthplace,
            String state, String role, java.sql.Date dateOfBirth) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        java.sql.PreparedStatement statement = null;
        final String insert = "insert into MECCANICO(foto, codicePersonale, nomeMembro, cognomeMembro, dataNascita, luogoNascita, nazione, ruolo) values (?,?,?,?,?,?,?,?)";
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, photo);
            statement.setInt(2, personalCode);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setDate(5, dateOfBirth);
            statement.setString(6, birthplace);
            statement.setString(7, state);
            statement.setString(8, role);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            }
        }

    }

    @Override
    public ObservableList<Rider> getRiders() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Rider> listRider = FXCollections.observableArrayList();
        final String retrieve = "select * from PILOTA";
        try {
            final PreparedStatement statement = conn.prepareStatement(retrieve);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final Rider rider = new Rider();
                rider.setPhoto(result.getString("foto"));
                rider.setPersonalCode(result.getInt("codicePersonale"));
                rider.setFirstName(result.getString("nomeMembro"));
                rider.setLastName(result.getString("cognomeMembro"));
                rider.setDate(result.getDate("dataNascita"));
                rider.setBirthplace(result.getString("luogoNascita"));
                rider.setState(result.getString("nazione"));
                rider.setRole(result.getString("ruolo"));
                rider.setHeight(result.getInt("altezza"));
                rider.setWeight(result.getInt("peso"));
                rider.setNumber(result.getInt("numero"));
                rider.setAcronym(result.getString("sigla"));
                listRider.add(rider);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        return listRider;
    }

    @Override
    public ObservableList<Member> getOtherMembers() {

        ObservableList<Member> listMember = FXCollections.observableArrayList();
        listMember.addAll(this.getEngineers());
        listMember.addAll(this.getMechanics());

        return listMember;
    }

    @Override
    public ObservableList<Member> getMechanics() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Member> listMember = FXCollections.observableArrayList();

        final String retrieve2 = "select * from MECCANICO";
        try {
            final PreparedStatement statement2 = conn.prepareStatement(retrieve2);
            final ResultSet result = statement2.executeQuery();
            while (result.next()) {
                final Member meccanico = new Mechanic();
                meccanico.setPhoto(result.getString("foto"));
                meccanico.setPersonalCode(result.getInt("codicePersonale"));
                meccanico.setFirstName(result.getString("nomeMembro"));
                meccanico.setLastName(result.getString("cognomeMembro"));
                meccanico.setDate(result.getDate("dataNascita"));
                meccanico.setBirthplace(result.getString("luogoNascita"));
                meccanico.setState(result.getString("nazione"));
                meccanico.setRole(result.getString("ruolo"));
                listMember.add(meccanico);
            }
            result.close();
            statement2.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        return listMember;
    }

    @Override
    public ObservableList<Member> getEngineers() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Member> listMember = FXCollections.observableArrayList();

        final String retrieve2 = "select * from INGEGNERE";
        try {
            final PreparedStatement statement2 = conn.prepareStatement(retrieve2);
            final ResultSet result = statement2.executeQuery();
            while (result.next()) {
                final Member ingegnere = new Mechanic();
                ingegnere.setPhoto(result.getString("foto"));
                ingegnere.setPersonalCode(result.getInt("codicePersonale"));
                ingegnere.setFirstName(result.getString("nomeMembro"));
                ingegnere.setLastName(result.getString("cognomeMembro"));
                ingegnere.setDate(result.getDate("dataNascita"));
                ingegnere.setBirthplace(result.getString("luogoNascita"));
                ingegnere.setState(result.getString("nazione"));
                ingegnere.setRole(result.getString("ruolo"));
                listMember.add(ingegnere);
            }
            result.close();
            statement2.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        return listMember;
    }

    @Override
    public ObservableList<Rider> getRidersFromYear(int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Rider> list = FXCollections.observableArrayList();
        final String retrieve = "select p.* from PILOTA p, CONTRATTO_PILOTA c where p.codicePersonale = c.codicePersonale && c.annocampionato = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setInt(1, year);
            result = statement.executeQuery();

            while (result.next()) {

                final Rider rider = new Rider();
                rider.setPhoto(result.getString("foto"));
                rider.setPersonalCode(result.getInt("codicePersonale"));
                rider.setFirstName(result.getString("nomeMembro"));
                rider.setLastName(result.getString("cognomeMembro"));
                rider.setDate(result.getDate("dataNascita"));
                rider.setBirthplace(result.getString("luogoNascita"));
                rider.setState(result.getString("nazione"));
                rider.setRole(result.getString("ruolo"));
                rider.setHeight(result.getInt("altezza"));
                rider.setWeight(result.getInt("peso"));
                rider.setNumber(result.getInt("numero"));
                rider.setAcronym(result.getString("sigla"));
                list.add(rider);

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
    public ObservableList<Rider> getRidersFromClassAndYear(String className, int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Rider> list = FXCollections.observableArrayList();
        final String retrieve = "select p.* from PILOTA p, CONTRATTO_PILOTA c where p.codicePersonale = c.codicePersonale AND c.nomeClasse = ? AND c.annoCampionato = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setString(1, className);
            statement.setInt(2, year);
            result = statement.executeQuery();

            while (result.next()) {

                final Rider rider = new Rider();
                rider.setPhoto(result.getString("foto"));
                rider.setPersonalCode(result.getInt("codicePersonale"));
                rider.setFirstName(result.getString("nomeMembro"));
                rider.setLastName(result.getString("cognomeMembro"));
                rider.setDate(result.getDate("dataNascita"));
                rider.setBirthplace(result.getString("luogoNascita"));
                rider.setState(result.getString("nazione"));
                rider.setRole(result.getString("ruolo"));
                rider.setHeight(result.getInt("altezza"));
                rider.setWeight(result.getInt("peso"));
                rider.setNumber(result.getInt("numero"));
                rider.setAcronym(result.getString("sigla"));
                list.add(rider);

            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        return list;
    }

    public ObservableList<Member> getMembersFromTeam(String team, int year) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Member> list = FXCollections.observableArrayList();

        final String retrieve = "select m.* from MECCANICO m, CONTRATTO_MECCANICO c where m.codicePersonale=c.codicePersonale && c.annoCampionato = ? && c.nomeTeam = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setInt(1, year);
            statement.setString(2, team);
            result = statement.executeQuery();

            while (result.next()) {
                final Member member = new Mechanic();
                member.setPhoto(result.getString("foto"));
                member.setPersonalCode(result.getInt("codicePersonale"));
                member.setFirstName(result.getString("nomeMembro"));
                member.setLastName(result.getString("cognomeMembro"));
                member.setDate(result.getDate("dataNascita"));
                member.setBirthplace(result.getString("luogoNascita"));
                member.setState(result.getString("nazione"));
                member.setRole(result.getString("ruolo"));
                list.add(member);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        final String ret = "select m.* from INGEGNERE m, CONTRATTO_INGEGNERE c where m.codicePersonale=c.codicePersonale && c.annoCampionato = ? && c.nomeTeam = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(ret);
            statement.setInt(1, year);
            statement.setString(2, team);
            result = statement.executeQuery();

            while (result.next()) {
                final Member member = new Mechanic();
                member.setPhoto(result.getString("foto"));
                member.setPersonalCode(result.getInt("codicePersonale"));
                member.setFirstName(result.getString("nomeMembro"));
                member.setLastName(result.getString("cognomeMembro"));
                member.setDate(result.getDate("dataNascita"));
                member.setBirthplace(result.getString("luogoNascita"));
                member.setState(result.getString("nazione"));
                member.setRole(result.getString("ruolo"));
                list.add(member);
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
    public ObservableList<Rider> getRidersFromTeamAndYear(String teamName, int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Rider> list = FXCollections.observableArrayList();
        final String retrieve = "select p.* from PILOTA p, CONTRATTO_PILOTA c where p.codicePersonale = c.codicePersonale AND c.nomeTeam = ? AND c.annoCampionato = ?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setString(1, teamName);
            statement.setInt(2, year);
            result = statement.executeQuery();

            while (result.next()) {

                final Rider rider = new Rider();
                rider.setPhoto(result.getString("foto"));
                rider.setPersonalCode(result.getInt("codicePersonale"));
                rider.setFirstName(result.getString("nomeMembro"));
                rider.setLastName(result.getString("cognomeMembro"));
                rider.setDate(result.getDate("dataNascita"));
                rider.setBirthplace(result.getString("luogoNascita"));
                rider.setState(result.getString("nazione"));
                rider.setRole(result.getString("ruolo"));
                rider.setHeight(result.getInt("altezza"));
                rider.setWeight(result.getInt("peso"));
                rider.setNumber(result.getInt("numero"));
                rider.setAcronym(result.getString("sigla"));
                list.add(rider);

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
    public ObservableList<Rider> getRidersFromTeamAndYearAndClass(String teamName, int year, String clax) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Rider> list = FXCollections.observableArrayList();
        final String retrieve = "select p.* from PILOTA p, CONTRATTO_PILOTA c where p.codicePersonale = c.codicePersonale AND c.nomeTeam = ? AND c.annoCampionato = ? AND c.nomeClasse=?";

        try {
            PreparedStatement statement = null;
            ResultSet result = null;
            statement = conn.prepareStatement(retrieve);
            statement.setString(1, teamName);
            statement.setInt(2, year);
            statement.setString(3, clax);
            result = statement.executeQuery();

            while (result.next()) {

                final Rider rider = new Rider();
                rider.setPhoto(result.getString("foto"));
                rider.setPersonalCode(result.getInt("codicePersonale"));
                rider.setFirstName(result.getString("nomeMembro"));
                rider.setLastName(result.getString("cognomeMembro"));
                rider.setDate(result.getDate("dataNascita"));
                rider.setBirthplace(result.getString("luogoNascita"));
                rider.setState(result.getString("nazione"));
                rider.setRole(result.getString("ruolo"));
                rider.setHeight(result.getInt("altezza"));
                rider.setWeight(result.getInt("peso"));
                rider.setNumber(result.getInt("numero"));
                rider.setAcronym(result.getString("sigla"));
                list.add(rider);

            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            AlertTypes alert = new AlertTypesImpl();
            alert.showError(e);
        }

        return list;
    }
}
