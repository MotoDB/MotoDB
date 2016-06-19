package com.motodb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.motodb.model.Sponsor;
import com.motodb.model.Team;
import com.motodb.view.alert.AlertTypes;
import com.motodb.view.alert.AlertTypesImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamManagerImpl implements TeamManager {

    @Override
    public void addTeam(int year, String name, String location, String logo, ObservableList<String> classes,
            ObservableList<String> sponsors, ObservableList<String> manufacturers) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();
        boolean ok = true;

        final String insert = "insert into TEAM(annoCampionato, nomeTeam, sede, logo) values (?,?,?,?)";
        try (final PreparedStatement statement = conn.prepareStatement(insert)) {
            statement.setInt(1, year);
            statement.setString(2, name);
            statement.setString(3, location);
            statement.setString(4, logo);
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        if (ok) {
            final String insert2 = "insert into FINANZIAMENTO(annoCampionato, nomeTeam, nomeSponsor) values (?,?,?)";
            sponsors.forEach(e -> {
                try (final PreparedStatement statement2 = conn.prepareStatement(insert2)) {
                    statement2.setInt(1, year);
                    statement2.setString(2, name);
                    statement2.setString(3, e);
                    statement2.executeUpdate();
                    statement2.close();
                } catch (SQLException exe) {
                    try {
                        AlertTypes alert = new AlertTypesImpl();
                        alert.showError(exe);
                    } catch (ExceptionInInitializerError ei) {
                        exe.printStackTrace();
                    }
                }
            });

            final String insert3 = "insert into ISCRIZIONE_CLASSE(annoCampionato, nomeTeam, nomeClasse) values (?,?,?)";
            classes.forEach(e -> {
                try (final PreparedStatement statement3 = conn.prepareStatement(insert3)) {
                    statement3.setInt(1, year);
                    statement3.setString(2, name);
                    statement3.setString(3, e);
                    statement3.executeUpdate();
                    statement3.close();
                } catch (SQLException ex) {
                    try {
                        AlertTypes alert = new AlertTypesImpl();
                        alert.showError(ex);
                    } catch (ExceptionInInitializerError ei) {
                        ex.printStackTrace();
                    }
                }
            });

            final String insert4 = "insert into CONTRATTO_MARCA(nomeMarca, annoCampionato, nomeTeam) values (?,?,?)";
            manufacturers.forEach(e -> {
                try (final PreparedStatement statement4 = conn.prepareStatement(insert4)) {
                    statement4.setString(1, e);
                    statement4.setInt(2, year);
                    statement4.setString(3, name);
                    statement4.executeUpdate();
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
    public ObservableList<Team> getTeams() {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Team> listTeam = FXCollections.observableArrayList();
        final String retrieve = "select * from TEAM order by annoCampionato";
        try (final PreparedStatement statement = conn.prepareStatement(retrieve);
                final ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Team team = new Team();
                team.setYear(result.getInt("annoCampionato"));
                team.setName(result.getString("nomeTeam"));
                team.setLocation(result.getString("sede"));
                team.setLogo(result.getString("logo"));
                listTeam.add(team);
            }

        } catch (SQLException e) {
            try {
                AlertTypes alert = new AlertTypesImpl();
                alert.showError(e);
            } catch (ExceptionInInitializerError ei) {
                e.printStackTrace();
            }
        }

        return listTeam;
    }

    @Override
    public ObservableList<Team> getTeamsFromYear(int year) {

        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<Team> listTeam = FXCollections.observableArrayList();
        final String retrieve = "select * from TEAM where annocampionato = ?";
        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, year);
            try (final ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    Team team = new Team();
                    team.setYear(result.getInt("annoCampionato"));
                    team.setName(result.getString("nomeTeam"));
                    team.setLocation(result.getString("sede"));
                    team.setLogo(result.getString("logo"));
                    listTeam.add(team);
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

        return listTeam;
    }

    @Override
    public ObservableList<String> getTeamsByYearAndClass(int year, String clax) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        ObservableList<String> list = FXCollections.observableArrayList();
        final String retrieve = "SELECT i.nomeTeam " + "FROM ISCRIZIONE_CLASSE i " + "WHERE i.annoCampionato = ? "
                + "AND i.nomeClasse = ? ";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, year);
            statement.setString(2, clax);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    list.add(result.getString("nomeTeam"));
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

    @Override
    public Team getTeamByName(final String name) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        final Team team = new Team();
        final String retrieve = "select * from TEAM where TEAM.nomeTeam = ?";
        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setString(1, name);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    team.setYear(result.getInt("annoCampionato"));
                    team.setName(result.getString("nomeTeam"));
                    team.setLocation(result.getString("sede"));
                    team.setLogo(result.getString("logo"));
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
    public ObservableList<Sponsor> getSponsorsByTeamAndYear(String name, int year) {
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();
        
        ObservableList<Sponsor> list = FXCollections.observableArrayList();
        final String retrieve = "select s.* from FINANZIAMENTO f, SPONSOR s where s.nomeSponsor=f.nomeSponsor && f.nomeTeam = ? && f.annoCampionato = ?";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setString(1, name);
            statement.setInt(2, year);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    list.add(new Sponsor(result.getString("nomeSponsor"), result.getString("logo")));
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
    
    @Override
    public String getTeamByYearAndRider(int year, int rider){
    	final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        final String retrieve = "SELECT i.nomeTeam " + "FROM CONTRATTO_PILOTA i " + "WHERE i.annoCampionato = ? "
                + "AND i.codicePersonale = ? ";

        try (final PreparedStatement statement = conn.prepareStatement(retrieve)) {
            statement.setInt(1, year);
            statement.setInt(2, rider);
            try (final ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    return result.getString("nomeTeam");
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

        return null;
    }
}
