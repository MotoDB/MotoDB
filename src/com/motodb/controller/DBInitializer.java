package com.motodb.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import com.motodb.view.AddContractControl.MemberType;

import javafx.collections.FXCollections;

public class DBInitializer {

	private static final ClaxManager clax = new ClaxManagerImpl();
	private static final SponsorManager sponsor = new SponsorManagerImpl();
	private static final ChampionshipManager championship = new ChampionshipManagerImpl();
	private static final ManufacturerManager manufacturer = new ManufacturerManagerImpl();
	private static final TeamManager team = new TeamManagerImpl();
	private static final CircuitManager circuit = new CircuitManagerImpl();
	private static final MemberManager member = new MemberManagerImpl();
	private static final ContractManager contract = new ContractManagerImpl();
	private static final TyreManager tyre = new TyreManagerImpl();
	
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
		// TODO moto2
	}
	
	private static void initTeams(){
		team.addTeam(2016, "Yamaha Factory Racing" , "Gerno di Lesmo, Italy", "https://www.yamahamotogp.com/assets/img/logo.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Movistar", "Monster Energy", "Abarth")), FXCollections.observableArrayList(Arrays.asList("Yamaha")));
		team.addTeam(2016, "Ducati Team" , "Borgo Panigale, Italy", "http://i.imgur.com/r8GLh1q.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "TIM", "Shell")), FXCollections.observableArrayList(Arrays.asList("Ducati")));
		team.addTeam(2016, "Honda Racing Team", "Saitama, Japan", "https://www.supersprox.com/wp-content/uploads/2011/07/46cf4b4a17c095136bf56aeb4d4b0d96.png", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Repsol", "Red Bull")), FXCollections.observableArrayList(Arrays.asList("Honda")));
		team.addTeam(2016, "Team Suzuki Racing", "Hamamatsu, Japan", "http://i.imgur.com/4Zp2MPi.jpg", FXCollections.observableArrayList(Arrays.asList("MotoGP")), FXCollections.observableArrayList(Arrays.asList("Michelin", "Motul")), FXCollections.observableArrayList(Arrays.asList("Suzuki")));
		// TODO team moto2
		// TODO team moto3
	}

	private static void initCircuits(){
		circuit.addCircuit("Autodromo del Mugello", "Italy", "Scarperia, Tuscany", 9, 6, 5245, 1141, "http://css.motogp.com/w2015/img/circuits/circuits_6.svg", Optional.of("1'47.639"), Optional.of(1), Optional.of(2013));
		circuit.addCircuit("Circuit de Barcelona-Catalunya", "Spain", "Montmelo, Catalunya", 8, 5, 4655, 1047, "http://css.motogp.com/w2015/img/circuits/circuits_116.svg", Optional.of("1'45.971"), Optional.of(4), Optional.of(2016));
		circuit.addCircuit("Misano World Circuit", "Italy", "Misano Adriatico, Emilia-Romagna", 10, 6, 4266, 565, "http://css.motogp.com/w2015/img/circuits/circuits_102.svg", Optional.of("1'33.273"), Optional.of(2), Optional.of(2015));
		circuit.addCircuit("Twin Ring Motegi", "Japan", "Tochigi, Haga", 8, 6, 4801, 762, "http://css.motogp.com/w2015/img/circuits/circuits_76.svg", Optional.of("1'45.350"), Optional.of(2), Optional.of(2014));
		circuit.addCircuit("Circuit of the Americas", "United States", "Austin, Texas", 9, 11, 5513, 1200, "http://css.motogp.com/w2015/img/circuits/circuits_116.svg", Optional.of("2'03.575"), Optional.of(1), Optional.of(2014));
		// TODO add more
	}
	
	private static void initMembers(){
		int i=0;
		//MotoGP
		member.addRider(i++, "Marc", "Marquez", "http://www.motogp.com/en/api/rider/photo/grid/old/7444.jpg", "Cervera, Catalunya", "Spain", "Official Rider", new Date(729962150000l), 93, 58, 168, "Mar");
		member.addRider(i++, "Jorge", "Lorenzo", "http://www.motogp.com/en/api/rider/photo/grid/old/6060.jpg", "Palma de Mallorca, Catalunya", "Spain", "Official Rider", new Date(547139750000l), 99, 64, 173, "Lor");
		member.addRider(i++, "Andrea", "Dovizioso", "http://www.motogp.com/en/api/rider/photo/grid/old/5885.jpg", "Forlimpopoli, Emilia-Romagna", "Italy", "Official Rider", new Date(511974950000l), 4, 67, 167, "Dov");
		member.addRider(i++, "Maverick", "Vinales", "http://www.motogp.com/en/api/rider/photo/grid/old/7409.jpg", "Figueres, Catalunya", "Spain", "Official Rider", new Date(789923750000l), 25, 64, 171, "Vin");
		member.addRider(i++, "Michele", "Pirro", "http://i.imgur.com/hbMKy6S.jpg", "San Giovanni Rotondo, Puglia", "Italy", "Test Rider", new Date(520935287000l), 51, 69, 177, "Pir");
		//Moto2
		member.addRider(i++, "Johann", "Zarco", "http://www.motogp.com/en/api/rider/photo/grid/old/7236.jpg", "Cannes, Cote D'Azur", "France", "Official Rider", new Date(648144409000l), 5, 65, 171, "Zar");
		member.addRider(i++, "Lorenzo", "Baldassarri", "http://www.motogp.com/en/api/rider/photo/grid/old/8030.jpg", "San Severino, Marche", "Italy", "Official Rider", new Date(847296409000l), 7, 68, 183, "Bal");
		member.addRider(i++, "Danny", "Kent", "http://www.motogp.com/en/api/rider/photo/grid/old/7461.jpg", "Chippenham, Wiltshire", "United Kingdom", "Official Rider", new Date(754243609000l), 52, 65, 172, "Ken");
		member.addRider(i++, "Hafizh", "Malaysia", "http://www.motogp.com/en/api/rider/photo/grid/old/8132.jpg", "Ampang, Kuala Lumpur", "Malaysia", "Official Rider", new Date(768154009000l), 55, 65, 180, "Sya");
		//Moto3
		member.addRider(i++, "Enea", "Bastianini", "http://www.motogp.com/en/api/rider/photo/grid/old/8295.jpg", "Rimini, Emilia-Romagna", "Italy", "Official Rider", new Date(883496074000l), 33, 60, 169, "Bas");
		member.addRider(i++, "Brad", "Binder", "http://www.motogp.com/en/api/rider/photo/grid/old/7646.jpg", "Potchefstroom, North-West", "South Africa", "Official Rider", new Date(808155274000l), 41, 61, 170, "BiB");
		member.addRider(i++, "Nicolo", "Bulega", "http://www.motogp.com/en/api/rider/photo/grid/old/8756.jpg", "Montecchio, Emilia-Romagna", "Italy", "Official Rider", new Date(940089373000l), 8, 63, 180, "Bul");
		member.addRider(i++, "Francesco", "Bagnaia", "http://www.motogp.com/en/api/rider/photo/grid/old/8273.jpg", "Torino, Piemonte", "Italy", "Official Rider", new Date(940089373000l), 21, 59, 174, "Bag");
		//Engineers
		member.addEngineer(i++, "Cristian", "Gabbarini", "http://media.motoblog.it/8/897/01-Gabbarini-620x413.jpg", "Senigallia, Marche", "Italy", "Chief Engineer", new Date(270045049000l));
		member.addEngineer(i++, "Luigi", "Dall'Igna", "http://media.motoblog.it/7/770/Dall-Igna_Ducati_Test_Sepang.jpg", "Thiene, Veneto", "Italy", "General Manager", new Date(-109580909000l));
		// TODO more engineers
		//Mechanics
		member.addMechanic(i++, "Mark", "Elder", "http://www.cycleworld.com/sites/cycleworld.com/files/styles/large_1x_/public/import/embedded/wp-content/uploads/2014/07/Mark-Elder-590x393.jpg", "Los Angeles, California", "United States", "On Track Mechanic", new Date(142102291000l));
		// TODO more mechanics
	}
	
	private static void initContracts(){
		contract.addContract(2016, MemberType.Rider, 1, team);
		contract.addContract(2015, MemberType.Rider, 1, team);
		contract.addContract(2014, MemberType.Rider, 1, team);
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
	
	public static void main(String[] args) {
		final DBManager db = DBManager.getDB();
        db.createConnection();
		
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
		System.out.println("Circuits Done");
		initTyres();
		System.out.println("Tyres Done");
    }	
	
}
