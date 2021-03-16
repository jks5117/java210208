package athread.talk1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class TalkServerThread extends Thread {
	TalkServer ts = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	//닉네임
	String nickName = null;
	public TalkServerThread(TalkServer talkServer) {
		this.ts = talkServer;
		this.client = ts.client;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject();
			ts.jta_log.append(msg+"\n");
			StringTokenizer st = new StringTokenizer(msg,"#");
			st.nextToken();//100
			nickName = st.nextToken();
			ts.jta_log.append(nickName+"님이 입장하였습니다.\n");
			for(TalkServerThread tst:ts.globalList) {
				//기설이 입장하기 전에 있는 친구들의 정보를 받기
				this.send(100+"#"+tst.nickName);
			}
			ts.globalList.add(this);//현재 스레드는 기설 스레드
			this.broadCasting(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	//glabalList에는 서버에 접속해온 클라이언트에 대한 스레드 객체가 담겨 있음
	public void broadCasting(String msg) {
		for(TalkServerThread tst:ts.globalList) {
			//그 스레드에 send메소드 호출하여 메시지 전송함.
			tst.send(msg);
		}
	}
	
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		boolean isStop = false;
		try {
			run_start:
				while(!isStop) {
					try {
						String msg = "";
						msg = (String)ois.readObject();
						ts.jta_log.append(msg+"\n");
						StringTokenizer st = null;
						int protocol = 0;
						if(msg!=null) {
							st = new StringTokenizer(msg,"#");
							protocol = Integer.parseInt(st.nextToken());
						}
						switch(protocol) {
							case 200:{
								String nickName = st.nextToken();
								String msg2 = st.nextToken();
								broadCasting(200+"#"+nickName+"#"+msg2);
							};
						}
					} catch (Exception e) {
						System.out.println("while inner ===> "+e.toString());
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
