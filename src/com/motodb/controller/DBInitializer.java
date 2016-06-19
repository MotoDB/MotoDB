package com.motodb.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import com.motodb.model.Rider;
import com.motodb.view.AddContractControl.MemberType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBInitializer {
	
	private final static Random random = new Random();
	
	private final static ObservableList<Integer> points = FXCollections.observableArrayList(Arrays.asList(25,20,16,13,11,10,9,8,7,6,5,4,3,2,1,0));
	
	private static final ClaxManager clax = new ClaxManagerImpl();
	private static final SponsorManager sponsor = new SponsorManagerImpl();
	private static final ChampionshipManager championship = new ChampionshipManagerImpl();
	private static final ManufacturerManager manufacturer = new ManufacturerManagerImpl();
	private static final TeamManager team = new TeamManagerImpl();
	private static final CircuitManager circuit = new CircuitManagerImpl();
	private static final MemberManager member = new MemberManagerImpl();
	private static final ContractManager contract = new ContractManagerImpl();
	private static final TyreManager tyre = new TyreManagerImpl();
	private static final BikeManager bike = new BikeManagerImpl();
	private static final WeekendManager weekend = new WeekendManagerImpl();
	private static final SessionManager session = new SessionManagerImpl();
	private static final RacingRiderManager racingRider = new RacingRiderManagerImpl();
	
	public ObservableList<Integer> getPoints() {
		return FXCollections.observableArrayList(points);
	}
	
	private static void initPoints(){
		
        final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();
        
        for(Integer i:points){
	        final String insert = "insert into PUNTEGGIO(valore) values (?)";
	        
	        try (final PreparedStatement statement = conn.prepareStatement(insert)) {
	            statement.setInt(1, i);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	                e.printStackTrace();
            }
        }  
	}

	private static void initClasses(){
		clax.addClass("Moto3", "https://it.wikipedia.org/wiki/Moto3#Regolamento_tecnico", 3);
		clax.addClass("Moto2", "https://it.wikipedia.org/wiki/Moto2#Regolamento_tecnico", 2);
		clax.addClass("MotoGP", "https://it.wikipedia.org/wiki/MotoGP#Regolamento_tecnico", 1);
		clax.addClass("125", "https://it.wikipedia.org/wiki/Classe_125#Regolamento", 3);
		clax.addClass("250", "https://it.wikipedia.org/wiki/Classe_125#Regolamento", 2);
		clax.addClass("500", "https://it.wikipedia.org/wiki/Classe_125#Regolamento", 1);
	}
	
	private static void initSponsors(){
		sponsor.addSponsor("Michelin", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_michelin.jpg");
		sponsor.addSponsor("TIM", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_05.png");
		sponsor.addSponsor("Monster Energy", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_04.png");
		sponsor.addSponsor("GoPro", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_09.png");
		sponsor.addSponsor("Movistar", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_10.png");
		sponsor.addSponsor("Red Bull", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_02.png");
		sponsor.addSponsor("Motul", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_07.png");
		sponsor.addSponsor("Shell", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_11.png");
		sponsor.addSponsor("Octo", "http://css.motogp.com/w2015/img/spon_sors/2016/title_sponsor_06.png");
		sponsor.addSponsor("Bridgestone", "http://i.imgur.com/VOmP4zm.png");
		sponsor.addSponsor("Abarth", "http://i.imgur.com/Gv0r6Ge.png");
		sponsor.addSponsor("Magneti Marelli", "http://i.imgur.com/jkRTob3.png");
		sponsor.addSponsor("Repsol", "http://i.imgur.com/65RlZtH.png");
		sponsor.addSponsor("Dunlop", "http://i.imgur.com/LlPb4Ss.png");
		sponsor.addSponsor("Akrapovic", "http://i.imgur.com/6gSPEEz.png");
	}
	
	private static void initChampionships(){
		championship.addChampionship(2016, 68, FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2", "Moto3")), FXCollections.observableArrayList(Arrays.asList("Michelin")));
		championship.addChampionship(2015, 67, FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2", "Moto3")), FXCollections.observableArrayList(Arrays.asList("Bridgestone")));
		championship.addChampionship(2014, 66, FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2", "Moto3")), FXCollections.observableArrayList(Arrays.asList("Bridgestone")));
		championship.addChampionship(2013, 65, FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2", "Moto3")), FXCollections.observableArrayList(Arrays.asList("Bridgestone")));
		championship.addChampionship(2001, 53, FXCollections.observableArrayList(Arrays.asList("500", "250", "125")), FXCollections.observableArrayList(Arrays.asList("Michelin")));
		championship.addChampionship(2002, 54, FXCollections.observableArrayList(Arrays.asList("MotoGP", "250", "125")), FXCollections.observableArrayList(Arrays.asList("Michelin")));
	}
	
	private static void initManufacturers() {
		manufacturer.addManufacturer("Ducati", "http://i.imgur.com/3IEWTDv.png");
		manufacturer.addManufacturer("Honda", "http://i.imgur.com/gpgmssX.png");
		manufacturer.addManufacturer("Suzuki", "http://i.imgur.com/mvdTzMG.png");
		manufacturer.addManufacturer("Yamaha", "http://i.imgur.com/dsKjxrP.png");
		manufacturer.addManufacturer("KTM", "http://i.imgur.com/1pLLecR.png");
		manufacturer.addManufacturer("Kalex", "http://i.imgur.com/faPucgQ.png");
	}
	
	private static void initTeams(){
		int i=2016;
		team.addTeam(i, "Yamaha Factory Racing" , "Gerno di Lesmo, Italy", "https://www.yamahamotogp.com/assets/img/logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Movistar", "Monster Energy", "Abarth", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Yamaha")));
		team.addTeam(i, "Ducati Team" , "Borgo Panigale, Italy", "http://i.imgur.com/r8GLh1q.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "TIM", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Honda Racing Team", "Saitama, Japan", "https://www.supersprox.com/wp-content/uploads/2011/07/46cf4b4a17c095136bf56aeb4d4b0d96.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Repsol", "Red Bull")), FXCollections.observableArrayList(Arrays.asList("Honda")));
		team.addTeam(i, "Team Suzuki Racing", "Hamamatsu, Japan", "http://i.imgur.com/4Zp2MPi.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Motul")), FXCollections.observableArrayList(Arrays.asList("Suzuki")));
		team.addTeam(i, "Pons HP40", "Barcelona, Spain", "http://i.imgur.com/jvMgddG.png", FXCollections.observableArrayList(Arrays.asList("Moto2")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Kalex")));
		team.addTeam(i, "Forward Racing", "Agno, Switzerland", "http://www.petracubonova.com/wp-content/uploads/2015/10/forward_logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Pramac Racing", "Casole d'Elsa, Italy", "https://pbs.twimg.com/profile_images/689396677329838080/yc34t1an.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Michelin", "Motul")), FXCollections.observableArrayList(Arrays.asList("Yamaha", "Kalex")));
		// TODO team moto2
		// TODO team moto3
		i=2015;
		team.addTeam(i, "Ducati Team" , "Borgo Panigale, Italy", "http://i.imgur.com/r8GLh1q.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "TIM", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Honda Racing Team", "Saitama, Japan", "https://www.supersprox.com/wp-content/uploads/2011/07/46cf4b4a17c095136bf56aeb4d4b0d96.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Repsol", "Red Bull")), FXCollections.observableArrayList(Arrays.asList("Honda")));
		team.addTeam(i, "Team Suzuki Racing", "Hamamatsu, Japan", "http://i.imgur.com/4Zp2MPi.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Motul")), FXCollections.observableArrayList(Arrays.asList("Suzuki")));
		team.addTeam(i, "Yamaha Factory Racing" , "Gerno di Lesmo, Italy", "https://www.yamahamotogp.com/assets/img/logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Movistar", "Monster Energy", "Abarth", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Yamaha")));
		team.addTeam(i, "Pons HP40", "Barcelona, Spain", "http://i.imgur.com/jvMgddG.png", FXCollections.observableArrayList(Arrays.asList("Moto2")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Kalex")));
		team.addTeam(i, "Forward Racing", "Agno, Switzerland", "http://www.petracubonova.com/wp-content/uploads/2015/10/forward_logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Pramac Racing", "Casole d'Elsa, Italy", "https://pbs.twimg.com/profile_images/689396677329838080/yc34t1an.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Bridgestone", "Motul")), FXCollections.observableArrayList(Arrays.asList("Yamaha", "Kalex")));
		i=2014;
		team.addTeam(i, "Ducati Team" , "Borgo Panigale, Italy", "http://i.imgur.com/r8GLh1q.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "TIM", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Honda Racing Team", "Saitama, Japan", "https://www.supersprox.com/wp-content/uploads/2011/07/46cf4b4a17c095136bf56aeb4d4b0d96.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Repsol", "Red Bull")), FXCollections.observableArrayList(Arrays.asList("Honda")));
		team.addTeam(i, "Yamaha Factory Racing" , "Gerno di Lesmo, Italy", "https://www.yamahamotogp.com/assets/img/logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Monster Energy", "Abarth", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Yamaha")));
		team.addTeam(i, "Pons HP40", "Barcelona, Spain", "http://i.imgur.com/jvMgddG.png", FXCollections.observableArrayList(Arrays.asList("Moto2")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Kalex")));
		team.addTeam(i, "Forward Racing", "Agno, Switzerland", "http://www.petracubonova.com/wp-content/uploads/2015/10/forward_logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP", "Moto2")), FXCollections.observableArrayList(Arrays.asList("Bridgestone", "Shell", "Akrapovic")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(i, "Pramac Racing", "Casole d'Elsa, Italy", "https://pbs.twimg.com/profile_images/689396677329838080/yc34t1an.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Dunlop", "Bridgestone", "Motul")), FXCollections.observableArrayList(Arrays.asList("Yamaha", "Kalex")));
	}

	private static void initCircuits(){
		circuit.addCircuit("Autodromo del Mugello", "Italy", "Scarperia, Tuscany", 9, 6, 5245, 1141, "http://css.motogp.com/w2015/img/circuits/circuits_6.svg", Optional.of("1'47.639"), Optional.of(1), Optional.of(2013));
		circuit.addCircuit("Circuit de Barcelona", "Spain", "Montmelo, Catalunya", 8, 5, 4655, 1047, "http://css.motogp.com/w2015/img/circuits/circuits_116.svg", Optional.of("1'45.971"), Optional.of(4), Optional.of(2016));
		circuit.addCircuit("Misano World Circuit", "Italy", "Misano Adriatico, Emilia-Romagna", 10, 6, 4266, 565, "http://css.motogp.com/w2015/img/circuits/circuits_102.svg", Optional.of("1'33.273"), Optional.of(2), Optional.of(2015));
		circuit.addCircuit("Twin Ring Motegi", "Japan", "Tochigi, Haga", 8, 6, 4801, 762, "http://css.motogp.com/w2015/img/circuits/circuits_76.svg", Optional.of("1'45.350"), Optional.of(2), Optional.of(2014));
		circuit.addCircuit("Circuit of the Americas", "United States", "Austin, Texas", 9, 11, 5513, 1200, "http://css.motogp.com/w2015/img/circuits/circuits_116.svg", Optional.of("2'03.575"), Optional.of(1), Optional.of(2014));
		// TODO add more
	}
	
	private static void initMembers(){
		int i=1;
		//MotoGP
		member.addRider(i++, "Marc", "Marquez", "http://www.motogp.com/en/api/rider/photo/grid/old/7444.jpg", "Cervera, Catalunya", "Spain", "Official Rider", new Date(729962150000l), 93, 58, 168, "Mar");
		member.addRider(i++, "Jorge", "Lorenzo", "http://www.motogp.com/en/api/rider/photo/grid/old/6060.jpg", "Palma de Mallorca, Catalunya", "Spain", "Official Rider", new Date(547139750000l), 99, 64, 173, "Lor");
		member.addRider(i++, "Andrea", "Dovizioso", "http://www.motogp.com/en/api/rider/photo/grid/old/5885.jpg", "Forlimpopoli, Emilia-Romagna", "Italy", "Official Rider", new Date(511974950000l), 4, 67, 167, "Dov");
		member.addRider(i++, "Maverick", "Vinales", "http://www.motogp.com/en/api/rider/photo/grid/old/7409.jpg", "Figueres, Catalunya", "Spain", "Official Rider", new Date(789923750000l), 25, 64, 171, "Vin");
		member.addRider(i++, "Michele", "Pirro", "http://www.motogp.com/en/api/rider/photo/grid/old/6231.jpg", "San Giovanni Rotondo, Puglia", "Italy", "Test Rider", new Date(520935287000l), 51, 69, 177, "Pir");
		member.addRider(i++, "Dani", "Pedrosa", "http://www.motogp.com/en/api/rider/photo/grid/old/5515.jpg", "Sabadell, Catalunya", "Spain", "Official Rider", Date.valueOf("1985-9-29"), 26, 51, 160, "Ped");
		member.addRider(i++, "Valentino", "Rossi", "http://www.motogp.com/en/api/rider/photo/grid/old/158.jpg", "Tavullia, Marche", "Italy", "Official Rider", Date.valueOf("1979-2-16"), 46, 65, 182, "Ros");
		member.addRider(i++, "Andrea", "Iannone", "http://www.motogp.com/en/api/rider/photo/grid/old/6848.jpg", "Vasto, Abruzzo", "Italy", "Official Rider", Date.valueOf("1989-8-9"), 29, 67, 178, "Ian");
		member.addRider(i++, "Aleix", "Espargaro", "http://www.motogp.com/en/api/rider/photo/grid/old/6854.jpg", "Granollers, Catalunya", "Spain", "Official Rider", Date.valueOf("1989-7-30"), 41, 71, 180, "EsA");
		//Moto2
		member.addRider(i++, "Johann", "Zarco", "http://www.motogp.com/en/api/rider/photo/grid/old/7236.jpg", "Cannes, Cote D'Azur", "France", "Official Rider", new Date(648144409000l), 5, 65, 171, "Zar");
		member.addRider(i++, "Lorenzo", "Baldassarri", "http://www.motogp.com/en/api/rider/photo/grid/old/8030.jpg", "San Severino, Marche", "Italy", "Official Rider", new Date(847296409000l), 7, 68, 183, "Bal");
		member.addRider(i++, "Danny", "Kent", "http://www.motogp.com/en/api/rider/photo/grid/old/7461.jpg", "Chippenham, Wiltshire", "United Kingdom", "Official Rider", new Date(754243609000l), 52, 65, 172, "Ken");
		member.addRider(i++, "Hafizh", "Syarin", "http://www.motogp.com/en/api/rider/photo/grid/old/8132.jpg", "Ampang, Kuala Lumpur", "Malaysia", "Official Rider", new Date(768154009000l), 55, 65, 180, "Sya");
		//Moto3
		member.addRider(i++, "Enea", "Bastianini", "http://www.motogp.com/en/api/rider/photo/grid/old/8295.jpg", "Rimini, Emilia-Romagna", "Italy", "Official Rider", new Date(883496074000l), 33, 60, 169, "Bas");
		member.addRider(i++, "Brad", "Binder", "http://www.motogp.com/en/api/rider/photo/grid/old/7646.jpg", "Potchefstroom, North-West", "South Africa", "Official Rider", new Date(808155274000l), 41, 61, 170, "BiB");
		member.addRider(i++, "Nicolo", "Bulega", "http://www.motogp.com/en/api/rider/photo/grid/old/8756.jpg", "Montecchio, Emilia-Romagna", "Italy", "Official Rider", new Date(940089373000l), 8, 63, 180, "Bul");
		member.addRider(i++, "Francesco", "Bagnaia", "http://www.motogp.com/en/api/rider/photo/grid/old/8273.jpg", "Torino, Piemonte", "Italy", "Official Rider", new Date(940089373000l), 21, 59, 174, "Bag");
		//Engineers
		i=501;
		member.addEngineer(i++, "Cristian", "Gabbarini", "http://media.motoblog.it/8/897/01-Gabbarini-620x413.jpg", "Senigallia, Marche", "Italy", "Chief Engineer", new Date(270045049000l));
		member.addEngineer(i++, "Luigi", "Dall'Igna", "http://media.motoblog.it/7/770/Dall-Igna_Ducati_Test_Sepang.jpg", "Thiene, Veneto", "Italy", "General Manager", new Date(-109580909000l));
		// TODO more engineers
		//Mechanics
		i=1001;
		member.addMechanic(i++, "Mark", "Elder", "http://www.cycleworld.com/sites/cycleworld.com/files/styles/large_1x_/public/import/embedded/wp-content/uploads/2014/07/Mark-Elder-590x393.jpg", "Los Angeles, California", "United States", "On Track Mechanic", new Date(142102291000l));
		// TODO more mechanics
	}
	
	private static void initContracts(){
		int i =2016;
		contract.addContract(i, MemberType.Rider, 1, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 2, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 3, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 4, "Team Suzuki Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 5, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 6, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 7, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 8, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 9, "Team Suzuki Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 11, "Forward Racing", "Moto2");
		contract.addContract(i, MemberType.Engineer, 501, "Honda Racing Team");
		contract.addContract(i, MemberType.Engineer, 502, "Honda Racing Team");
		contract.addContract(i, MemberType.Mechanic, 1001, "Ducati Team");
		i=2015;
		contract.addContract(i, MemberType.Rider, 1, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 2, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 3, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 4, "Team Suzuki Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 5, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 6, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 7, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 8, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 9, "Team Suzuki Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 11, "Forward Racing", "Moto2");
		contract.addContract(i, MemberType.Engineer, 501, "Honda Racing Team");
		contract.addContract(i, MemberType.Engineer, 502, "Honda Racing Team");
		contract.addContract(i, MemberType.Mechanic, 1001, "Ducati Team");
		i=2014;
		contract.addContract(i, MemberType.Rider, 1, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 2, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 3, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 4, "Pons HP40", "Moto2");
		contract.addContract(i, MemberType.Rider, 5, "Ducati Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 6, "Honda Racing Team", "MotoGP");
		contract.addContract(i, MemberType.Rider, 7, "Yamaha Factory Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 8, "Pramac Racing", "MotoGP");
		contract.addContract(i, MemberType.Rider, 9, "Forward Racing", "MotoGP");
		contract.addContract(i, MemberType.Engineer, 501, "Honda Racing Team");
		contract.addContract(i, MemberType.Engineer, 502, "Honda Racing Team");
		contract.addContract(i, MemberType.Mechanic, 1001, "Ducati Team");
	}
	
	private static void initTyres(){
		tyre.addTyre("Michelin", "Power Slick", "R17", "Hard");
		tyre.addTyre("Michelin", "Power Slick", "R17", "Medium");
		tyre.addTyre("Michelin", "Power Slick", "R17", "Soft");
		tyre.addTyre("Michelin", "Power Inter", "R17", "Intermediate");
		tyre.addTyre("Michelin", "Power Rain", "R17", "Soft");
		tyre.addTyre("Michelin", "Power Rain", "R17", "Hard");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Extra-Hard");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Hard");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Medium");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Soft");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Extra-Soft");
		tyre.addTyre("Bridgestone", "Battlax Slick", "R16.5", "Asymmetric");
		tyre.addTyre("Bridgestone", "Battlax Rain", "R16.5", "Soft");
		tyre.addTyre("Bridgestone", "Battlax Rain", "R16.5", "Hard");
		tyre.addTyre("Dunlop", "067", "R17", "Soft 1");
		tyre.addTyre("Dunlop", "067", "R17", "Medium 2");
		tyre.addTyre("Dunlop", "067", "R17", "Hard 3");
		tyre.addTyre("Dunlop", "067", "R17", "Special Hard 4");
		tyre.addTyre("Dunlop", "067", "R17", "Rain");
	}
	
	private static void initBikes(){
		int i=2016;
		bike.addBike("Honda", "RC213V", "http://www.motogp.com/en/api/rider/photo/bike/old/7444.jpg", 157, i, "Honda Racing Team");
		bike.addBike("Yamaha", "YZR-M1", "http://www.motogp.com/en/api/rider/photo/bike/old/6060.jpg", 157, i, "Yamaha Factory Racing");
		bike.addBike("Ducati", "Desmosedici GP", "http://www.motogp.com/en/api/rider/photo/bike/old/5885.jpg", 157, i, "Ducati Team");
		bike.addBike("Suzuki", "GSX-RR", "http://www.motogp.com/en/api/rider/photo/bike/old/7409.jpg", 157, i, "Team Suzuki Racing");
		bike.addBike("Yamaha", "YZR-M1 Open", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 157, i, "Forward Racing");
		bike.addBike("Kalex", "Pons Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Forward Racing");
		bike.addBike("Kalex", "Forward Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Pons HP40");
		i=2015;
		bike.addBike("Honda", "RC213V", "http://www.motogp.com/en/api/rider/photo/bike/old/7444.jpg", 157, i, "Honda Racing Team");
		bike.addBike("Yamaha", "YZR-M1", "http://www.motogp.com/en/api/rider/photo/bike/old/6060.jpg", 157, i, "Yamaha Factory Racing");
		bike.addBike("Ducati", "Desmosedici GP", "http://www.motogp.com/en/api/rider/photo/bike/old/5885.jpg", 157, i, "Ducati Team");
		bike.addBike("Suzuki", "GSX-RR", "http://www.motogp.com/en/api/rider/photo/bike/old/7409.jpg", 157, i, "Team Suzuki Racing");
		bike.addBike("Yamaha", "YZR-M1 Open", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 157, i, "Forward Racing");
		bike.addBike("Kalex", "Pons Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Forward Racing");
		bike.addBike("Kalex", "Forward Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Pons HP40");
		i=2014;
		bike.addBike("Honda", "RC213V", "http://www.motogp.com/en/api/rider/photo/bike/old/7444.jpg", 157, i, "Honda Racing Team");
		bike.addBike("Yamaha", "YZR-M1", "http://www.motogp.com/en/api/rider/photo/bike/old/6060.jpg", 157, i, "Yamaha Factory Racing");
		bike.addBike("Ducati", "Desmosedici GP", "http://www.motogp.com/en/api/rider/photo/bike/old/5885.jpg", 157, i, "Ducati Team");
		bike.addBike("Yamaha", "YZR-M1 Open", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 157, i, "Forward Racing");
		bike.addBike("Kalex", "Pons Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Forward Racing");
		bike.addBike("Kalex", "Forward Kalex", "http://www.motogp.com/en/api/rider/photo/bike/old/8150.jpg", 140, i, "Pons HP40");
		
	}
	
	private static void initWeekends(){
		int i=2016;
		weekend.addWeekend(i, "Circuit of the Americas", Date.valueOf("2016-4-7"), Date.valueOf("2016-4-10"));
		weekend.addWeekend(i, "Autodromo del Mugello", Date.valueOf("2016-5-19"), Date.valueOf("2016-5-22"));
		weekend.addWeekend(i, "Circuit de Barcelona", Date.valueOf("2016-6-2"), Date.valueOf("2016-6-5"));
		weekend.addWeekend(i, "Misano World Circuit", Date.valueOf("2016-9-8"), Date.valueOf("2016-9-11"));
		weekend.addWeekend(i, "Twin Ring Motegi", Date.valueOf("2016-10-13"), Date.valueOf("2016-10-16"));
		i=2015;
		weekend.addWeekend(i, "Circuit of the Americas", Date.valueOf("2015-4-09"), Date.valueOf("2015-4-12"));
		weekend.addWeekend(i, "Autodromo del Mugello", Date.valueOf("2015-5-28"), Date.valueOf("2015-5-31"));
		weekend.addWeekend(i, "Circuit de Barcelona", Date.valueOf("2015-6-11"), Date.valueOf("2015-6-14"));
		weekend.addWeekend(i, "Misano World Circuit", Date.valueOf("2015-9-10"), Date.valueOf("2015-9-13"));
		weekend.addWeekend(i, "Twin Ring Motegi", Date.valueOf("2015-10-8"), Date.valueOf("2015-10-11"));
		i=2014;
		weekend.addWeekend(i, "Circuit of the Americas", Date.valueOf("2014-4-10"), Date.valueOf("2014-4-13"));
		weekend.addWeekend(i, "Autodromo del Mugello", Date.valueOf("2014-5-29"), Date.valueOf("2014-6-1"));
		weekend.addWeekend(i, "Circuit de Barcelona", Date.valueOf("2014-6-12"), Date.valueOf("2014-6-15"));
		weekend.addWeekend(i, "Misano World Circuit", Date.valueOf("2014-9-11"), Date.valueOf("2014-9-14"));
		weekend.addWeekend(i, "Twin Ring Motegi", Date.valueOf("2014-10-9"), Date.valueOf("2014-10-12"));
	}
	
	private static void initSessions(){
		weekend.getWeekends().forEach(w->{
			clax.getClassesFromYear(w.getChampionshipYear()).forEach(c->{
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Wet", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)), "FP1", "1:00", "Free Practice", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Wet", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)), "FP2", "1:00", "Free Practice", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l*2)), "FP3", "0:50", "Free Practice", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)*2), "FP4", "0:20", "Qualification", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)*2), "Q1", "0:20", "Qualification", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry-Wet", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)*2), "Q2", "0:20", "Qualification", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)*3), "WUP", "0:40", "Warm Up", 0);
				session.addSession(c.getName(), w.getChampionshipYear(), w.getStartDate(), "Dry", random.nextInt(15)+15, random.nextInt(10)+35, random.nextInt(80), new Date(w.getStartDate().getTime()+(86400000l)*3), "RACE", "2:00", "Race", 25);
				System.out.println("------ "+c.getName()+ " -> Done");
			});
			System.out.println("---- " + w.getCircuitName() + " -- " + w.getChampionshipYear() + " -> Done");
		});
	}
	
	private static void initRacingRiders(){
		
		session.getSessions().forEach(s->{
			ObservableList<Rider> list = member.getRidersFromClassAndYear(s.getClassName(),s.getYear());
			Collections.shuffle(list);
			int i=1;
			while(i<=list.size()){
				for(Rider r:list){
					//System.out.println(s.getYear()+" - "+ s.getWeekendDate()+" - "+ s.getClassName()+" - "+ s.getCode()+" - "+ (random.nextInt(1)+1) +"'"+random.nextInt(60)+"."+random.nextInt(999)+" - "+ i +" - "+ r.getPersonalCode()+" - "+ bike.getBikeByTeamAndYearAndRider(s.getYear(), r.getPersonalCode()).getManufacturerName()+" - "+ bike.getBikeByTeamAndYearAndRider(s.getYear() , r.getPersonalCode()).getModel() +" - "+ racingRider.getRiderNameByCode(r.getPersonalCode()) +" - "+ FXCollections.observableArrayList(Arrays.asList(tyre.getTyres().get(random.nextInt( tyre.getTyres().size())))));
					//racingRider.addRacingRider(s.getYear(), s.getWeekendDate(), s.getClassName(), s.getCode(), (random.nextInt(1)+1) +"'"+random.nextInt(60)+"."+random.nextInt(999), i, i<list.size()-random.nextInt(3)? true:false, r.getPersonalCode(), bike.getBikeByTeamAndYearAndRider(s.getYear(), r.getPersonalCode()).getManufacturerName(), bike.getBikeByTeamAndYearAndRider(s.getYear(), r.getPersonalCode()).getModel(), racingRider.getRiderNameByCode(r.getPersonalCode()), i<points.size()&&s.getCode().equals("RACE")?points.get(i-1):0, FXCollections.observableArrayList(Arrays.asList(tyre.getTyres().get(random.nextInt( tyre.getTyres().size())))));
					racingRider.addRacingRider(s.getYear(), s.getWeekendDate(), s.getClassName(), s.getCode(), (random.nextInt(1)+1) +"'"+random.nextInt(60)+"."+random.nextInt(999), i, i<list.size()-random.nextInt(3)? true:false, r.getPersonalCode(), bike.getBikeByTeamAndYearAndRider(s.getYear(), r.getPersonalCode()).getManufacturerName(), bike.getBikeByTeamAndYearAndRider(s.getYear(), r.getPersonalCode()).getModel(), racingRider.getTeamByRiderAndYear(r.getPersonalCode(), s.getYear()).getName(), i<points.size()&&s.getCode().equals("RACE")?points.get(i-1):0, FXCollections.observableArrayList(Arrays.asList(tyre.getTyres().get(random.nextInt( tyre.getTyres().size())))));;
					i++;
					System.out.println("------ "+r.getFirstName()+" "+r.getLastName()+" -- "+r.getPersonalCode() + " -> Done");
				}
			}
			System.out.println("---- "+ s.getClassName() + " -- " + s.getCode() + " -- " + s.getWeekendDate() + " -> Done");
		});
	}
	
	private static void emptyTables(){
		
		final DBManager db = DBManager.getDB();
        final Connection conn = db.getConnection();

        	ObservableList<String> list = FXCollections.observableArrayList(Arrays.asList("SET FOREIGN_KEY_CHECKS = 0",
        			"TRUNCATE CAMPIONATO", "TRUNCATE CIRCUITO", "TRUNCATE CLASSE", "TRUNCATE CLASSE_IN_CAMPIONATO", 
        			"TRUNCATE CONTRATTO_INGEGNERE", "TRUNCATE CONTRATTO_PILOTA", "TRUNCATE CONTRATTO_MARCA",
        			"TRUNCATE CONTRATTO_MECCANICO","TRUNCATE INGEGNERE","TRUNCATE FINANZIAMENTO",
        			"TRUNCATE ISCRIZIONE_CLASSE","TRUNCATE PNEUMATICO","TRUNCATE MARCA", "TRUNCATE PUNTEGGIO",
        			"TRUNCATE MECCANICO","TRUNCATE MOTO","TRUNCATE PILOTA","TRUNCATE PILOTA_IN_SESSIONE",
        			"TRUNCATE SESSIONE","TRUNCATE SPONSOR","TRUNCATE SPONSORIZZAZIONE","TRUNCATE TEAM",
        			"TRUNCATE UTILIZZO_PNEUMATICO","TRUNCATE WEEKEND",
        			"SET FOREIGN_KEY_CHECKS = 1 "));
        	list.forEach(s->{
		        try (final PreparedStatement statement = conn.prepareStatement(s);) {
		            statement.executeUpdate();
		        } catch (SQLException e) {
		                e.printStackTrace();
		        }
		    });
        
	}
	
	public static void main(String[] args) {
		final DBManager db = DBManager.getDB();
        db.createConnection();
		
        emptyTables();
        System.out.println("TABLES EMPTIED");
        
        initPoints();
		System.out.println("Points Done");
        initClasses();
        System.out.println("Classes Done");
		initSponsors();
		System.out.println("Sponsors Done");
		initChampionships();
		System.out.println("Championships Done");
		initManufacturers();
		System.out.println("Manufacturers Done");
		initTeams();
		System.out.println("Teams Done");
		initMembers();
		System.out.println("Members Done");
		initCircuits();
		System.out.println("Contracts Done");
		initContracts();
		System.out.println("Circuits Done");
		initTyres();
		System.out.println("Tyres Done");
		initBikes();
		System.out.println("Bikes Done");
		initWeekends();
		System.out.println("Weekends Done");
		
		System.out.println("-- Initializating Sessions");
		initSessions();
		System.out.println("Sessions Done");
		
		System.out.println("-- Initializating Racing Riders");
		initRacingRiders();
		System.out.println("Riders Done");
		
		System.out.println("DATABASE INIT DONE");
    }	
	
}
