//package com.unifi.fattureApp.UI;
//
//import org.assertj.swing.edt.GuiActionRunner;
//import org.assertj.swing.fixture.FrameFixture;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import javafx.scene.paint.Color;
//
//
//public class MainWindowsUITest {
//
//	private FrameFixture window;
//	
//	@Before
//	public void setUp() {
//		MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
//		window = new FrameFixture(frame.getFrame());
//		window.show();
//	}
//	
//	@After
//	public void tearDown() {
//	  window.cleanUp();
//	}
//	
//	@Test
//	public void testApplicationName() {
//		window.requireTitle("Fatture App");
//	}
//	
//	@Test
//	public void testWindowIsEnable() {
//		window.requireVisible();
//	}
//	
//	@Test
//	public void testWindowIsVisible() {
//		window.isEnabled();
//	}
//	
//
//
//}
