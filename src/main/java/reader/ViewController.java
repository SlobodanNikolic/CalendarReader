package reader;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class ViewController {

	 /** A public instance of the ViewController
    */
	public static ViewController instance = null;
	
	 /** Returns the ViewController instance
    */
	public static ViewController getInstance() {
      if(instance == null) {
         instance = new ViewController();
      }
      return instance;
	}
	
	protected ViewController() {
		
	}
	
	 /** Opens the file chooser, so the file containing the events could be read.
	  * @return selectedFile The selected file, containing the events, or null
    */
	public File OpenFileChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			return selectedFile;
		}
		return null;
	}
	
	
}
