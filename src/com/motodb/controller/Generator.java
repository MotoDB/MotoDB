package com.motodb.controller;

import com.motodb.controller.ChampionshipManager;
import com.motodb.controller.ChampionshipManagerImpl;
import com.motodb.controller.CircuitManager;
import com.motodb.controller.CircuitManagerImpl;
import com.motodb.controller.ClaxManager;
import com.motodb.controller.ClaxManagerImpl;
import com.motodb.controller.MemberManager;
import com.motodb.controller.MemberManagerImpl;
import com.motodb.controller.SponsorManager;
import com.motodb.controller.SponsorManagerImpl;
import com.motodb.controller.TeamManager;
import com.motodb.controller.TeamManagerImpl;

public class Generator {

	ChampionshipManager championship = new ChampionshipManagerImpl();
	TeamManager team = new TeamManagerImpl();
	ClaxManager clax = new ClaxManagerImpl();
	SponsorManager sponsor = new SponsorManagerImpl();
	CircuitManager circuit = new CircuitManagerImpl();
	MemberManager member = new MemberManagerImpl();
	
	public void populateChampionshipTable(){
		championship.
	}
	
	public void populateClaxTable(){
		clax.addClass("Moto 3", rules, 3);
		clax.addClass("Moto 2", rules, 2);
		clax.addClass("Moto GP", rules, 1);
	}
	
	public void populateTeamTable(){
		
	}

	public void populateCircuitTable(){
		
	}
	
	public void populateSponsorTable(){
		sponsor.addSponsor("Michelin", urlLogo);
	}
	
	public void populateMemberTable(){
		member.addEngineer(3451, , lastName, photo, birthplace, state, role, dateOfBirth);
	}
	
	public void populateRiderTable(){
		
	}
	
	public void populateAllTables(){
		this.populateSponsorTable();
		this.populateClaxTable();
		this.populateSponsorTable();
		this.populateMemberTable();
		this.populateRiderTable();
		this.populateCircuitTable();
	}
}
