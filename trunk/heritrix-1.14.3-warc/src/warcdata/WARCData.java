package warcdata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import org.archive.io.ArchiveRecord;
import org.archive.io.warc.WARCReader;
import org.archive.io.warc.WARCReaderFactory;
import org.archive.io.warc.WARCRecord;


public class WARCData{

	public static void main(String[] args) throws IOException{
		String path="E:/trec/ClueWeb09_Chinese_1/zh0000/00";
		OutputStream out=null;
		File f=new File("E:/trec/00.warc");
		WARCReader reader = WARCReaderFactory.get(f);
		Iterator<ArchiveRecord> it=reader.iterator();
		long i=0;
		while(it.hasNext()){
			WARCRecord record=(WARCRecord)it.next();
			String type=(String)record.getHeader().getHeaderValue("WARC-Type");
			if(type.equalsIgnoreCase("response")){
				File f1=new File(path+"page"+i++);
				if(!f1.exists())f1.createNewFile();
				out=new FileOutputStream(f1);
				String url=record.getHeader().getUrl()+"\n";
				out.write(url.getBytes());
				byte[] b=new byte[1024];
				while(record.read(b,0,1024)!=-1){
					out.write(b);
					Arrays.fill(b, (byte)0);
				}
				out.flush();
				out.close();
				record.close();
			}
		}
	}
}