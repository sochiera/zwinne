package mulletsoft.greed.gui;

import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.launcher.ApplicationLauncher;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OpenCloseTest  extends FestSwingTestCaseTemplate{

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
    mFrame = WindowFinder.findFrame("main-frame").using( robot() );
  }

  @After
  public void tearDown(){
    cleanUp();
  }

  @Test
  public void openDownloadDialog() {
    System.out.println( "testopenDownloadDialog" );
    mFrame.button("btnDownloadData").click().requireEnabled();
  }


  @Test
  public void quitTest(){
    System.out.println( "testAppQuit" );
    mFrame.menuItem("mntmQuit").click();
    mFrame.requireNotVisible();
  }
}