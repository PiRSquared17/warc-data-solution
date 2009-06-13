package warcdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

import org.archive.io.ArchiveRecord;
import org.archive.io.warc.WARCReader;
import org.archive.io.warc.WARCReaderFactory;
import org.archive.io.warc.WARCRecord;

public class Test {
	public static void main(String[] main){
//		try{
//			FileInputStream fis=new FileInputStream(new File("E:/trec/01.warc.gz"));
//			GZIPInputStream gis=new GZIPInputStream(fis);
//			//WARCReader reader = WARCReaderFactory.get();
//			byte[] b=new byte[1024];
//			for(int i=0;i<100;i++){
//				gis.read(b);
//				System.out.println(new String(b));
//				Arrays.fill(b, (byte)0);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		try{
			File f=new File("E:/trec/01.warc.gz");
			WARCReader reader = WARCReaderFactory.get(f);
			Iterator<ArchiveRecord> it=reader.iterator();
//			while(it.hasNext()){
//				WARCRecord record=(WARCRecord)it.next();
//				String type=(String)record.getHeader().getHeaderValue("WARC-Type");
//				if(type.equalsIgnoreCase("response")){
//					byte[] b=new byte[1024];
//					while(record.read(b,0,1024)!=-1){
//						System.out.write(b);
//						Arrays.fill(b, (byte)0);
//					}
//					record.close();
//				}
//			}
//			Iterator<ArchiveRecord> it=reader.iterator();
			for(int i=0;i<10;i++){
				if(it.hasNext()){
					WARCRecord record=(WARCRecord)it.next();
					String type=(String)record.getHeader().getHeaderValue("WARC-Type");
					System.out.println(type);
					byte[] b=new byte[1024];
					while(record.read(b,0,1024)!=-1){
						System.out.println(new String(b));
						Arrays.fill(b, (byte)0);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
