import java.io.PrintWriter;
import java.util.Date;

import org.ateam.xxplore.core.service.mappingA.IDataSourceReader;
import org.ateam.xxplore.core.service.mappingA.IOFactory;


public class Formatter {
	
	// read everything out from input, and write them back to gz file
	public static void clean(String input, String gzfile) throws Exception {
		System.out.println("converting " + input + " into " + gzfile);
		PrintWriter pw = IOFactory.getGzPrintWriter(gzfile);
		IDataSourceReader dsr = IOFactory.getReader(input);
		int count = 0;
		try {
			for (String line = dsr.readLine(); line != null; line = dsr.readLine()) {
				pw.println(line);
				count++;
				if (count%3000000 == 0) System.out.println(new Date().toString() + " : " + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.close();
		dsr.close();
		System.out.println(count + " lines in all");
	}

	public static void main(String[] args) throws Exception {
		String workingDir = "\\\\poseidon\\team\\semantic search\\BillionTripleData\\";
		String dbpedia = "dbpedia-v3.nt.tar.gz";
		String dblp = "swetodblp_noblank.gz";
		String uscensus = "uscensus.nt.tar.gz";
		String geonames = "geonames.warc";
		clean(workingDir+dbpedia, workingDir+"gz\\dbpedia.gz");
		clean(workingDir+dblp, workingDir+"gz\\dblp.gz");
		clean(workingDir+uscensus, workingDir+"gz\\uscensus.gz");
		clean(workingDir+geonames, workingDir+"gz\\geonames.gz");
	}
}
