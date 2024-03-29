package basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import com.ice.tar.TarInputStream;

/**
 * Used to read a data source that is in the form of a single (.nt).tar.gz file
 * @author Linyun Fu
 *
 */
public class TarGzReader implements IDataSourceReader {

	private String fileName;
	private BufferedReader br;
	private TarInputStream targzinput;
	
	
	public TarGzReader(String fn) {
		fileName = fn;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws Exception {
		br.close();
	}

	public void init() throws Exception {
		if (IOFactory.isURL(fileName)) 
			targzinput = new TarInputStream(new GZIPInputStream(new URL(fileName).openStream()));
		else targzinput = new TarInputStream(new GZIPInputStream(new FileInputStream(fileName)));
		br = new BufferedReader(new InputStreamReader(targzinput, "UTF-8"));
	}

	public String readLine() throws Exception {
		String ret = br.readLine();
		while (ret == null) {
			if (targzinput.getNextEntry() == null) return null;
			ret = br.readLine();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		String testFile = "\\\\poseidon\\team\\semantic search\\BillionTripleData\\" +
				"uscensus.nt.tar.gz";
		TarGzReader tgr = new TarGzReader(testFile);
		for (int i = 0; i < 10; i++) System.out.println(tgr.readLine());
	}
}
