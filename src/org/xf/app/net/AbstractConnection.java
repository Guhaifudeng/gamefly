package org.xf.app.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * 定义客户端和服务器之间的所通信
 * @author Feng Xie
 * 
 */
public class AbstractConnection {
	/**
	 * 用来分割消息头和消息的字符串
	 */
	public final String MSG_DELIM = "||";//可能出异常
	
	/**
	 * 会话结束的命令
	 */
	public static final String END_OF_TRANSMISSION = "EOT";
	//
	/**
	 * 终端通信Socket
	 */
	protected Socket socket;
	
	/**
	 * 收到的消息，包括头、主体和分割字符串
	 */
	protected String message;
	
	/**
	 * socket I/O的reader和writer
	 */
	protected BufferedReader in;
	protected PrintStream out;
	/**
	 * 不指定Socket创建一个AbstractConnection
	 */
	protected AbstractConnection(){
		socket = null;
	}
	//
	/**指定socket创建一个AbstractConnection
	 * @param clientSocket
	 */
	public AbstractConnection(Socket clientSocket){
		socket = clientSocket;
	}
	//
	/**尝试为I/O打开Socket
	 * @return
	 */
	protected boolean open(){
		try{//尝试打开输入输出流
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
		}catch(IOException e){
			System.err.println("Could not create socket streams; closing connection.");
			//尝试干净地关掉Socket
			close(true);
			return false;
		}
		return true;
	}
	//
	/**
	 * 尝试关掉 Socket 
	 * @param ioError
	 */
	protected final void close(	boolean ioError) {//如果连接因为I/O错误而关闭则为真
		//如果有错误发生，先发送一个传送终止信号
		//这种操作应该终止另一端的服务
		if(!ioError){
			send(END_OF_TRANSMISSION,"");
		}
		
		try{
			socket.close();
		}catch(IOException e){
			System.err.println("Could not close socket connection.");
		}
		
	}
	
	/**发送一个命令和消息
	 * @param header  消息头
	 * @param msg	消息部分
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
	 * 从消息中解析消息头
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
	 * 从整个消息中解析消息部分
	 * @return
	 */
	protected String parseMessage (){
		if("".equals(message)) return "";
		//用消息和分割符号创建一个StringTokenizer
		StringTokenizer st = new StringTokenizer(message,MSG_DELIM);
		//先处理一个标记，然后返回第二个
		if(st.hasMoreTokens()){
			st.nextToken();
			
			if(st.hasMoreTokens()){
				return st.nextToken();
			}
		}
		return "";
	}
	/**
	 *尝试从输入流接收消息 
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
