import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatUser extends UnicastRemoteObject implements IChatUser {
    private String pseudo;
    private IChatServer chatServer;
    private UserInterface userInterface;

    public ChatUser(IChatServer chatServer) throws RemoteException {
        this.chatServer = chatServer;
        chatServer.addChatUser(this);
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void send(String message) {
        try {
            chatServer.send(new ChatItem(pseudo, message));
        } catch (RemoteException e) {
            display(new ChatItem("erreur", e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public void display(ChatItem chatItem) {
        userInterface.display(chatItem);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        IChatServer chatServer = (IChatServer) registry.lookup("ChatServer");
        ChatUser chatUser = new ChatUser(chatServer);
        new ChatFrame(chatUser);

    }
}
