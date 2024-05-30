import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConferenceRegistration extends Remote {
    void registerParticipant(Participant participant) throws RemoteException;
    void exportParticipantsToXML(String filePath) throws RemoteException;
    void importParticipantsFromXML(String filePath) throws RemoteException;
}
