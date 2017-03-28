package org.xf.app.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * ����ͻ��˺ͷ�����֮�����ͨ��
 * @author Feng Xie
 * 
 */
public class AbstractConnection {
	/**
	 * �����ָ���Ϣͷ����Ϣ���ַ���
	 */
	public final String MSG_DELIM = "||";//���ܳ��쳣
	
	/**
	 * �Ự����������
	 */
	public static final String END_OF_TRANSMISSION = "EOT";
	//
	/**
	 * �ն�ͨ��Socket
	 */
	protected Socket socket;
	
	/**
	 * �յ�����Ϣ������ͷ������ͷָ��ַ���
	 */
	protected String message;
	
	/**
	 * socket I/O��reader��writer
	 */
	protected BufferedReader in;
	protected PrintStream out;
	/**
	 * ��ָ��Socket����һ��AbstractConnection
	 */
	protected AbstractConnection(){
		socket = null;
	}
	//
	/**ָ��socket����һ��AbstractConnection
	 * @param clientSocket
	 */
	public AbstractConnection(Socket clientSocket){
		socket = clientSocket;
	}
	//
	/**����ΪI/O��Socket
	 * @return
	 */
	protected boolean open(){
		try{//���Դ����������
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
		}catch(IOException e){
			System.err.println("Could not create socket streams; closing connection.");
			//���Ըɾ��عص�Socket
			close(true);
			return false;
		}
		return true;
	}
	//
	/**
	 * ���Թص� Socket 
	 * @param ioError
	 */
	protected final void close(	boolean ioError) {//���������ΪI/O������ر���Ϊ��
		//����д��������ȷ���һ��������ֹ�ź�
		//���ֲ���Ӧ����ֹ��һ�˵ķ���
		if(!ioError){
			send(END_OF_TRANSMISSION,"");
		}
		
		try{
			socket.close();
		}catch(IOException e){
			System.err.println("Could not close socket connection.");
		}
		
	}
	
	/**����һ���������Ϣ
	 * @param header  ��Ϣͷ
	 * @param msg	��Ϣ����
	 */
	protected void send(String header, String msg) {
		//
		if(socket.isConnected()){
			out.println(header+MSG_DELIM+msg);
		}else{
			System.err.println("Socket not connected ; termination thread.");
		}
		
	}
	/**
	 * ����Ϣ�н�����Ϣͷ
	 * @return
	 */
	protected String parseHeader(){
		if("".equals(message)) return "";
		
		StringTokenizer st = new StringTokenizer(message,MSG_DELIM);
		
		if(st.hasMoreTokens()){
		
			return st.nextToken();
		}
		return "";
 	}
	/**
	 * ��������Ϣ�н�����Ϣ����
	 * @return
	 */
	protected String parseMessage (){
		if("".equals(message)) return "";
		//����Ϣ�ͷָ���Ŵ���һ��StringTokenizer
		StringTokenizer st = new StringTokenizer(message,MSG_DELIM);
		//�ȴ���һ����ǣ�Ȼ�󷵻صڶ���
		if(st.hasMoreTokens()){
			st.nextToken();
			
			if(st.hasMoreTokens()){
				return st.nextToken();
			}
		}
		return "";
	}
	/**
	 *���Դ�������������Ϣ 
	 */
	protected void recv(){
		message = "";
		try{
			message = in.readLine();
		}catch(IOException e){
			System.err.println("Unable to receive message; terminating conection");
			close(true);
		}
	}	
}//AbstractConnection
