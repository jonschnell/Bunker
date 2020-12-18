package bunker.chat;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import bunker.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vamsi Krishna Calpakkam
 * @modified by jonathon Schnell
 *
 */

@Component
@ServerEndpoint("/chat/{username}/{password}")
public class ChatServer {

	//userService us = new userService();
	userService us = (userService) MyApplicationContextAware.getApplicationContext().getBean("userService");


	// Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("password") String password)
						throws IOException
    {
		logger.info(username + " is loging in");
		if (us.userLogin(username, password)) {

			logger.info("Entered into Open");

			sessionUsernameMap.put(session, username);
			usernameSessionMap.put(username, session);
			String message="User:" + username + " has Joined the Chat";
			broadcast(message);
		}
		else{
			logger.info(username + " failed to log in");
			//TODO tell the client that login has failed
		}


    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException
    {
        // Handle new messages
    	logger.info("Entered into Message: Got Message:"+message);
    	String username = sessionUsernameMap.get(session);

    	if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
    	{
    		String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
    		sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
    		sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
    	}
    	else // Message to whole chat
    	{
	    	broadcast(username + ": " + message);
    	}
    }

    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");

		try {
			String username = sessionUsernameMap.get(session);
			sessionUsernameMap.remove(session);
			usernameSessionMap.remove(username);
			String message= username + " disconnected";
			broadcast(message);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    }

    @OnError
    public void onError(Session session, Throwable throwable)
    {
        // Do error handling here
    	logger.info("Entered into Error");
	}

	  private void sendMessageToPArticularUser(String username, String message)
    {
    	  try {
    		  usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
          e.printStackTrace();
        }
    }

    private void broadcast(String message)
		{
			  sessionUsernameMap.forEach((session, username)->{
					try {
								session.getBasicRemote().sendText(message);
					} catch (IOException e) {
								logger.info("Exception: " + e.getMessage().toString());
								e.printStackTrace();
					}

				});

    }
}
