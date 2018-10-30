import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatServer extends Remote {
    void addChatUser(IChatUser chatUser) throws RemoteException;

    void send(ChatItem chatItem) throws RemoteException;
}
