

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ChatServer extends UnicastRemoteObject implements IChatServer {

    private ArrayList<IChatUser> chatUsers;

    public ChatServer() throws RemoteException {
        chatUsers = new ArrayList<IChatUser>();
    }

    @Override
    public void addChatUser(IChatUser chatUser) {
        chatUsers.add(chatUser);
    }

    @Override
    public void send(ChatItem chatItem) {
        for (IChatUser chatUser : chatUsers) {
            try {
                chatUser.display(chatItem);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("ChatServer", new ChatServer());
    }
}
