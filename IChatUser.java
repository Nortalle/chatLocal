import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatUser extends Remote {
    void display(ChatItem chatItem) throws RemoteException;
}
