package com.unifi.fattureApp.UI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainWindowUITest {
	private FrameFixture window;
	
	private JButtonFixture addCompanyButton;
	private JPanelFixture addCompanyPanel;
	
	private JPanelFixture clientPanel;	
	private JButtonFixture addClient_Button;

	
	@Before
	public void setUp() {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame=new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		
		addCompanyButton = window.panel("CompanyPanel").button("AddCompanyButton");
		
		addClient_Button = window.panel("ClientPanel").button("AddClientButton");
		
		
	}
	
	@After
	public void tearDown() {
	  window.cleanUp();
	}
	
	@Test
	public void testApplicationName() {
		window.requireTitle("Fatture App");
	}
	
	@Test
	public void testWindowIsEnable() {
		window.requireVisible();
	}
	
	@Test
	public void testWindowIsVisible() {
		window.isEnabled();
		
	}
	
	@Test
	public void testWindowBckgColor() {
		window.background().requireEqualTo(java.awt.Color.LIGHT_GRAY);
	}
	//Company panel
	
	@Test
	public void testAddButtonText() {
		addCompanyButton.requireText("Add");
	}
	
	@Test 
	public void testAddButtonAction() {
		showAddCompanyPanel();
		addCompanyPanel.requireVisible();
	}
	
	@Test
	public void testEditButton() {
		
		JButtonFixture editButton=window.button("EditCompanyButton");
		
	}
	
	//Client panel

	
	private void showAddCompanyPanel() {
		addCompanyButton.click();
		addCompanyPanel = window.panel("AddCompanyPanel");
	}
	
}