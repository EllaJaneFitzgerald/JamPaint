import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface RemoteDrawingServer extends Remote {
    /*public void allClientsDrawLine(int x, int y, int startX, int startY, int thickness, Color color) throws  RemoteException;
    public void allClientsDrawSquare(int x, int y, int startX, int startY, int thickness, Color color) throws  RemoteException;
    public void allClientsDrawBrush(int x, int y, int startX, int startY, int thickness, Color color) throws  RemoteException;
    public void allClientsDrawPoint(int x, int y, int thickness, Color color) throws  RemoteException; */
    public void allClientsDrawIt(int x, int y, int startX, int startY, int thickness, Color color, int id) throws  RemoteException;;
    public void register () throws RemoteException;
}

