package org.xf.gamefly.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class AppendObjectOutputStream extends ObjectOutputStream {

	/**
	 * 追加模式的对象流
	 * @param	File
	 * @return	ObjectOutputStream
	 * @throws	IOException
	 */
	public static ObjectOutputStream newInstance(File file) throws IOException {
		long length = file.length();
		FileOutputStream fos = new FileOutputStream(file,true);
		ObjectOutputStream oos = null;
		if(length>0){
			oos = new AppendObjectOutputStream(fos);
		}else{
			oos = new ObjectOutputStream(fos);
		}
		return oos ;
	}

	private AppendObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	
	@Override
	protected void writeStreamHeader() throws IOException {
		super.reset();
	}
}