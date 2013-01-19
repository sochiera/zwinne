package mulletsoft.greed.gui;

import javax.swing.JButton;
import org.junit.Assert;
import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.finder.DialogFinder;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.launcher.ApplicationLauncher;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.fest.swing.timing.Condition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.fest.swing.core.matcher.*;
import org.fest.assertions.AssertExtension;
import org.fest.swing.finder.*;

public class ApplicationWindowTest  extends FestSwingTestCaseTemplate{

    private static EmergencyAbortListener mEmergencyAbortListener;
    private FrameFixture mFrame;

    @BeforeClass
    public static void setUpClass() throws Exception{
        mEmergencyAbortListener = EmergencyAbortListener.registerInToolkit();
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
        mEmergencyAbortListener.unregister();
    }

    @Before
    public void setUp(){
        setUpRobot();
        robot().settings().eventPostingDelay( 200 );
        robot().settings().delayBetweenEvents( 200 );
        ApplicationLauncher.application( ApplicationWindow.class ).start();
        //org.fest.swing.core.matcher.FrameMatcher.withTitle("Greed");
        mFrame = WindowFinder.findFrame(FrameMatcher.withTitle("Greed")).using( robot() );// findFrame("asda").using( robot() );
    }

    @After
    public void tearDown(){
        cleanUp();
    }
    
    @Test
    public void openDownloadDialog() {
        System.out.println( "testopenDownloadDialog" );
        mFrame.button("btnDownloadData").click().requireEnabled();

//        DialogFixture dialog = WindowFinder.findDialog("dsDialog").using(robot());
  //      dialog.requireVisible();
	}

 	@Test
    public void downloadOkButton() {
        System.out.println( "testopenDownloadDialog" );
        mFrame.button("btnDownloadData").click().requireEnabled();
        mFrame.dialog("dsDialog").requireVisible();
        mFrame.dialog("dsDialog").button("okButton").click().requireEnabled();
        mFrame.dialog("dsDialog").optionPane().okButton().requireVisible();
        mFrame.dialog("dsDialog").optionPane().okButton().click().requireEnabled();
        
//        DialogFixture dialog = WindowFinder.findDialog("dsDialog").using(robot());
  //      dialog.requireVisible();
	}
	
	@Test
    public void downloadCancelButton() {
        System.out.println( "testopenDownloadDialog" );
        mFrame.button("btnDownloadData").click().requireEnabled();
        mFrame.dialog("dsDialog").button("cancelButton").click().requireEnabled();
//        DialogFixture dialog = WindowFinder.findDialog("dsDialog").using(robot());
  //      dialog.requireVisible();
	}

    @Test
    public void quitTest(){
        System.out.println( "testAppQuit" );
        mFrame.menuItem("mntmQuit").click();
        mFrame.requireNotVisible();
    }
    

    
}