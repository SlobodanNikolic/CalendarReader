package reader;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class ViewController {

	public static ViewController instance = null;
	
	public static ViewController getInstance() {
      if(instance == null) {
         instance = new ViewController();
      }
      return instance;
	}
	
	protected ViewController() {
		
	}
	   
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
